package cz.muni.fi.pv217.barewbeer.client;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

@Path("/products")
@RegisterRestClient(configKey = "product-service-client")
public interface ProductServiceClient {
    
    @GET
    @Path("/{id}")
    public Response getProduct(@PathParam long id);
}
