package cz.muni.fi.pv217.barewbeer;

import cz.muni.fi.pv217.barewbeer.entity.Customer;
import cz.muni.fi.pv217.barewbeer.service.CustomerService;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/customers")
public class CustomerResource {
    @Inject
    CustomerService customerService;

    @POST
    @Path("/")
    @Produces(MediaType.TEXT_PLAIN)
    @Counted(name = "customersCount",
            description = "How many unique customers bought something at our store so far.")
    public Response createCustomer(Customer customer) {
        Customer created = customerService.createCustomer(customer);
        return Response.status(Response.Status.CREATED).entity(created).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateCustomer(@PathParam long id, Customer update) {
        Customer updated = customerService.updateCustomer(id, update);

        if (updated == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(String.format("Customer with id %d not found.", id)).build();
        }
        return Response.ok(updated).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteCustomer(@PathParam long id) {
        boolean success = customerService.deleteCustomer(id);

        if (!success) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(String.format("Customer with id %d not found.", id)).build();
        }
        return Response.ok(true).build();
    }

    @GET
    @Path("/{id}")
    public Response getCustomer(@PathParam long id) {
        Customer customer = customerService.findById(id);

        if (customer == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(String.format("Customer with id %d not found.", id)).build();
        }

        return Response.ok(customer).build();
    }

    @GET
    @Path("/")
    public Response listAllCustomers() {
        List<Customer> customers = customerService.listAll();

        return Response.ok(customers).build();
    }
}
