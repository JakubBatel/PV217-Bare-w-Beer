package cz.muni.fi.pv217.barewbeer.service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.NotFoundException;

// import org.jboss.logging.Logger;

import cz.muni.fi.pv217.barewbeer.entity.Product;
import cz.muni.fi.pv217.barewbeer.repository.ProductRepository;

@ApplicationScoped
@Transactional
public class ProductService {

    // private static Logger log = Logger.getLogger(ProductService.class);

    @Inject
    ProductRepository productRepository;

    public Product createProduct(Product product) {
        productRepository.persist(product);
        return product;
    }

    public Product findById(long id) {
        return productRepository.findById(id);
    }

    public List<Product> listAll() {
        return productRepository.listAll();
    }

    public Product updateProduct(long id, Product update) {
        Product product = productRepository.findById(id);

        if (product == null) {
            throw new NotFoundException(String.format("Product with id %d not found.", id));
        }

        product.merge(update);
        productRepository.persist(product);
        return product;
    }
    
    public Product deleteProduct(long id) {
        Product product = productRepository.findById(id);

        if (product == null) {
            throw new NotFoundException(String.format("Product with id %d not found.", id));
        }

        boolean deleted = productRepository.deleteById(id);
        return deleted ? product : null;
    }
}
