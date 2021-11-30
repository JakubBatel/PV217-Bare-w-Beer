package cz.muni.fi.pv217.barewbeer;

import java.math.BigDecimal;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.annotations.jaxrs.PathParam;

import cz.muni.fi.pv217.barewbeer.service.ShoppingCartService;

@Path("/cart")
public class ShoppingCartResource {
    
    @Inject
    ShoppingCartService shoppingCartService;

    @PUT
    @Path("/{customerId}/add")
    public Response addItemToCart(@PathParam long customerId, long productId, BigDecimal price, int count) {
        // TODO: usage of ShoppingCartService, product = API call of ProductService

        return Response.ok().build();
    }

    @PUT
    @Path("/{customerId}/remove")
    public Response removeItemFromCart(@PathParam long customerId, long productId, BigDecimal price, int count) {
        // TODO: usage of ShoppingCartService, product = API call of ProductService

        return Response.ok().build();
    }

    @PUT
    @Path("/{customerId}/count")
    public Response UpdateItemCountInCart(@PathParam long customerId, long productId, int count) {
        // TODO
        return Response.ok().build();
    }

    @PUT
    @Path("/{customerId}/reset")
    public Response resetShoppingCart(@PathParam long customerId) {
        // TODO
        return Response.ok().build();
    }

    @GET
    @Path("/{customerId}")
    public Response getShoppingCart(@PathParam long customerId) {
        // TODO
        return Response.ok().build();
    }
}
