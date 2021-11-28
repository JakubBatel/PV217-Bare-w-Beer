package cz.muni.fi.pv217.barewbeer.repository;

import cz.muni.fi.pv217.barewbeer.entity.Customer;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

@ApplicationScoped
@Transactional
public class CustomerRepository implements PanacheRepository<Customer> {
}
