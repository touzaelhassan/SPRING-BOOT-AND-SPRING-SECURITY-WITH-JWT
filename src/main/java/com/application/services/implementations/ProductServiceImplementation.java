package com.application.services.implementations;

import com.application.entities.Product;
import com.application.repositories.ProductRepository;
import com.application.services.specifications.ProductServiceSpecification;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import javax.transaction.Transactional;

@Service("productServiceBean")
@Transactional
public class ProductServiceImplementation implements ProductServiceSpecification {

    private ProductRepository productRepositoryBean;

    @Autowired
    public ProductServiceImplementation(ProductRepository productRepositoryBean) { this.productRepositoryBean = productRepositoryBean; }
    @Override
    public Product addProduct(Product product) { return productRepositoryBean.save(product); }
    @Override
    public List<Product> getProducts() { return productRepositoryBean.findAll(); }

}
