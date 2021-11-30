package cz.muni.fi.pv217.barewbeer.client;

import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/orders")
@RegisterRestClient(configKey = "order-service-client")
public interface OrderServiceClient {
    
    @PUT //should be POST
    @Path("/create")
    public Response createOrder(Order order);
}
