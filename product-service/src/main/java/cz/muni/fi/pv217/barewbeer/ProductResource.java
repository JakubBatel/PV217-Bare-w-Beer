package cz.muni.fi.pv217.barewbeer;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.annotations.jaxrs.PathParam;

import cz.muni.fi.pv217.barewbeer.entity.Product;
import cz.muni.fi.pv217.barewbeer.service.ProductService;

@Path("/products")
public class ProductResource {

    @Inject
    private ProductService productService;

    @PUT
    @Path("/create")
    public Response createProduct(Product product) {
        Product created = productService.createProduct(product);

        return Response.status(Response.Status.CREATED).entity(created).build();
    }

    @POST
    @Path("/{id}/update")
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
        Product product = Product.findById(id);

        if (product == null) {
            return Response
                .status(Response.Status.NOT_FOUND)
                .entity(String.format("Product for id %d not found.", id))
                .build();
        }

        return Response.ok(product).build();
    }

    @GET
    @Path("/list")
    public Response listProducts() {
        List<Product> products = Product.listAll();
        
        return Response.ok(products).build();
    }
}