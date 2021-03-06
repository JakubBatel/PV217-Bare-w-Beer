package cz.muni.fi.pv217.barewbeer;

import cz.muni.fi.pv217.barewbeer.entity.Product;
import cz.muni.fi.pv217.barewbeer.service.ProductService;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/products")
public class ProductResource {

    @Inject
    ProductService productService;

    @POST
    @Path("/")
    @Counted(name = "productCount", description = "Number of different products we offer in our e-shop")
    public Response createProduct(Product product) {
        Product created = productService.createProduct(product);
        return Response.status(Response.Status.CREATED).entity(created).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateProduct(@PathParam long id, Product update) {
        Product updated = productService.updateProduct(id, update);

        if (updated == null) {
            return Response
                .status(Response.Status.NOT_FOUND)
                .entity(String.format("Product for id %d not found.", id))
                .build();
        }
        return Response.ok(updated).build();
    }

    @GET
    @Path("/{id}")
    public Response getProduct(@PathParam long id) {
        Product product = productService.findById(id);

        if (product == null) {
            return Response
                .status(Response.Status.NOT_FOUND)
                .entity(String.format("Product for id %d not found.", id))
                .build();
        }

        return Response.ok(product).build();
    }

    @GET
    @Path("/")
    public Response listProducts() {
        List<Product> products = productService.listAll();
        
        return Response.ok(products).build();
    }
}