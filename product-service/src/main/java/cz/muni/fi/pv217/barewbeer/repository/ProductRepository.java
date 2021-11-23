package cz.muni.fi.pv217.barewbeer.repository;

import javax.enterprise.context.ApplicationScoped;

import cz.muni.fi.pv217.barewbeer.entity.Product;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class ProductRepository implements PanacheRepository<Product> {
    
}
