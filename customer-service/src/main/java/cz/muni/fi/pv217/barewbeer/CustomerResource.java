package cz.muni.fi.pv217.barewbeer;

import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;


import cz.muni.fi.pv217.barewbeer.entity.Customer;
import cz.muni.fi.pv217.barewbeer.service.CustomerService;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

@Path("/customers")
public class CustomerResource {
    @Inject
    CustomerService customerService;

    @PUT
    @Path("/create")
    public Response createCustomer(Customer customer) {
        Customer created = customerService.createCustomer(customer);

        return Response.status(Response.Status.CREATED).entity(created).build();
    }

    @POST
    @Path("/{id}/update")
    public Response updateCustomer(@PathParam long id, Customer update) {
        Customer updated = customerService.updateCustomer(id, update);

        if (updated == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(String.format("Customer with id %d not found.", id)).build();
        }
        return Response.ok(updated).build();
    }

    @POST
    @Path("/{id}/delete")
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
    @Path("/list")
    public Response listAllCustomers() {
        List<Customer> customers = customerService.listAll();

        return Response.ok(customers).build();
    }

}
