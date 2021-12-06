package cz.muni.fi.pv217.barewbeer;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import cz.muni.fi.pv217.barewbeer.client.Order;
import cz.muni.fi.pv217.barewbeer.client.OrderItem;
import cz.muni.fi.pv217.barewbeer.client.OrderServiceClient;
import cz.muni.fi.pv217.barewbeer.client.Product;
import cz.muni.fi.pv217.barewbeer.client.ProductServiceClient;
import cz.muni.fi.pv217.barewbeer.dto.AddItemToCartDTO;
import cz.muni.fi.pv217.barewbeer.dto.UpdateItemCountDTO;
import cz.muni.fi.pv217.barewbeer.service.ShoppingCartService;
import cz.muni.fi.pv217.barewbeer.utils.ShoppingCart;
import cz.muni.fi.pv217.barewbeer.utils.ShoppingCartItem;

@Path("/cart")
public class ShoppingCartResource {
    
    @Inject
    ShoppingCartService shoppingCartService;

    @Inject
    @RestClient
    OrderServiceClient orderClient;

    @Inject
    @RestClient
    ProductServiceClient productClient;

    @PUT
    @Path("/{customerId}/add")
    public Response addItemToCart(@PathParam long customerId, AddItemToCartDTO addItemToCartDTO) {
        Response productResponse = productClient.getProduct(addItemToCartDTO.productId);
        Product product = productResponse.readEntity(Product.class);

        ShoppingCartItem cartItem = new ShoppingCartItem(
            addItemToCartDTO.productId,
            product.name,
            product.description,
            product.price,
            addItemToCartDTO.count
        );
        shoppingCartService.addItemToShoppingCart(customerId, cartItem);
        return Response.ok().build();
    }

    @PUT
    @Path("/{customerId}/remove")
    public Response removeItemFromCart(@PathParam long customerId, long productId) {
        shoppingCartService.removeItemFromShoppingCart(customerId, productId);

        return Response.ok().build();
    }

    @PUT
    @Path("/{customerId}/count")
    public Response UpdateItemCountInCart(@PathParam long customerId, UpdateItemCountDTO updateItemCountDTO) {
        shoppingCartService.updateItemCountInShoppingCart(customerId, updateItemCountDTO.productId, updateItemCountDTO.count);

        return Response.ok().build();
    }

    @PUT
    @Retry(maxRetries = 2)
    @Fallback(fallbackMethod = "fallbackOrderShoppingCart")
    @Path("/{customerId}/order")
    public Response orderShoppingCart(@PathParam long customerId) {
        List<OrderItem> orderItems = new ArrayList<OrderItem>();

        ShoppingCart cart = shoppingCartService.getShoppingCartForCustomer(customerId);

        for (var entry : cart.getItems()) {
            OrderItem orderItem = new OrderItem();
            orderItem.productId = entry.getProductId();
            orderItem.pricePerUnit = entry.getPrice();
            orderItem.count = entry.getCount();

            orderItems.add(orderItem);
        }

        Order newOrder = new Order();
        newOrder.items = orderItems;
        newOrder.creationTimestamp = LocalDateTime.now();
        newOrder.confirmed = false;
        newOrder.shippedOut = false;

        orderClient.createOrder(newOrder);

        shoppingCartService.clearShoppingCart(customerId);

        return Response.ok(newOrder).build();
    }

    public Response fallbackOrderShoppingCart(long customerId) {
        return Response.status(400, "Bad request, Order was not created successfully").build();
    }

    @PUT
    @Path("/{customerId}/clear")
    public Response clearShoppingCart(@PathParam long customerId) {
        shoppingCartService.clearShoppingCart(customerId);

        return Response.ok().build();
    }

    @GET
    @Path("/{customerId}")
    public ShoppingCart getShoppingCart(@PathParam long customerId) {
        return shoppingCartService.getShoppingCartForCustomer(customerId);
    }
}
