package cz.muni.fi.pv217.barewbeer.repository;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

import cz.muni.fi.pv217.barewbeer.entity.Product;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
@Transactional
public class ProductRepository implements PanacheRepository<Product> {
    
}
