package cz.muni.fi.pv217.barewbeer.service;


import cz.muni.fi.pv217.barewbeer.entity.Customer;
import cz.muni.fi.pv217.barewbeer.repository.CustomerRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.NotFoundException;
import java.util.List;

@ApplicationScoped
@Transactional
public class CustomerService {
    @Inject
    CustomerRepository customerRepository;

    public Customer createCustomer(Customer customer) {
        customerRepository.persist(customer);
        return customer;
    }

    public Customer findById(long id) {
        return customerRepository.findById(id);
    }

    public List<Customer> listAll() {
        return customerRepository.listAll();
    }

    public Customer updateCustomer(long id, Customer update) {
        Customer customer = customerRepository.findById(id);

        if (customer == null) {
            throw new NotFoundException(String.format("Customer with id %d not found.", id));
        }

        customer.merge(update);
        customerRepository.persist(customer);
        return customer;
    }

    public boolean deleteCustomer(long id) {
        Customer customer = customerRepository.findById(id);

        if (customer == null) {
            throw new NotFoundException(String.format("Customer with id %d not found.", id));
        }

        return customerRepository.deleteById(id);
    }
}
