package cz.muni.fi.pv217.barewbeer;

import cz.muni.fi.pv217.barewbeer.entity.Order;
import cz.muni.fi.pv217.barewbeer.service.OrderService;
import org.eclipse.microprofile.metrics.annotation.Gauge;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/orders")
@ApplicationScoped
public class OrderResource {
    @Inject
    OrderService orderService;

    private long activeOrders = 0;

    private long shippedBeers = 0;

    private long earnings = 0;

    @POST
    @Path("/")
    public Response createOrder(Order order) {
        Order created = orderService.createOrder(order);
        activeOrders += created.confirmed && !created.shippedOut ? 1 : 0;
        if (created.shippedOut){
            for (var beer: created.items) {
                shippedBeers += beer.count;
                earnings += beer.pricePerUnit * beer.count;
            }
        }
        return Response.status(Response.Status.CREATED).entity(created).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateOrder(@PathParam long id, Order update) {
        Order updated = orderService.updateOrder(id, update);
        activeOrders += updated.confirmed && !updated.shippedOut ? 1 : 0;
        if (updated.shippedOut){
            for (var beer: update.items) {
                shippedBeers += beer.count;
                earnings += beer.pricePerUnit * beer.count;
            }
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

    @Gauge(name = "earnings", description = "How much money we have made so far", unit = "czk")
    public Long earnings() {
        return earnings;
    }

    @Gauge(name = "shippedBeers", description = "Number of bottles of beer we have sold so far", unit = "amount")
    public Long shippedBeers() {
        return shippedBeers;
    }

    @Gauge(name = "activeOrders", description = "Number of currently active orders", unit = "amount")
    public Long activeOrders() {
        return activeOrders;
    }
}
