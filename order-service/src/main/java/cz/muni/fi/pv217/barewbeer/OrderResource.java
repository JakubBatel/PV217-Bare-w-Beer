package cz.muni.fi.pv217.barewbeer;

import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import org.jboss.resteasy.annotations.jaxrs.PathParam;
import cz.muni.fi.pv217.barewbeer.entity.Order;
import cz.muni.fi.pv217.barewbeer.service.OrderService;

@Path("/orders")
public class OrderResource {
    @Inject
    OrderService orderService;

    @POST
    @Path("/")
    public Response createOrder(Order order) {
        Order created = orderService.createOrder(order);

        return Response.status(Response.Status.CREATED).entity(created).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateOrder(@PathParam long id, Order update) {
        Order updated = orderService.updateOrder(id, update);

        if (updated == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(String.format("Order for id %d not found.", id)).build();
        }
        return Response.ok(updated).build();
    }

    @GET
    @Path("/{id}")
    public Response getOrder(@PathParam long id) {
        Order order = orderService.findById(id);

        if (order == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(String.format("Order for id %d not found.", id)).build();
        }

        return Response.ok(order).build();
    }

    @GET
    @Path("/")
    public Response listOrders() {
        List<Order> orders = orderService.listAll();

        return Response.ok(orders).build();
    }

}
