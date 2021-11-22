package cz.muni.fi.pv217.barewbeer.service;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.ws.rs.NotFoundException;

// import org.jboss.logging.Logger;

import cz.muni.fi.pv217.barewbeer.entity.Product;

@ApplicationScoped
public class ProductService {

    // private static Logger log = Logger.getLogger(ProductService.class);

    @Transactional
    public Product createProduct(Product product) {
        product.persist();
        return product;
    }

    @Transactional
    public Product updateProduct(long id, Product update) {
        Product product = Product.findById(id);

        if (product == null) {
            throw new NotFoundException(String.format("Product with id %d not found.", id));
        }

        product.merge(update);
        product.persist();
        return product;
    }
    
    @Transactional
    public Product deleteProduct(long id) {
        Product product = Product.findById(id);

        if (product == null) {
            throw new NotFoundException(String.format("Product with id %d not found.", id));
        }

        boolean deleted = Product.deleteById(id);
        return deleted ? product : null;
    }
}
