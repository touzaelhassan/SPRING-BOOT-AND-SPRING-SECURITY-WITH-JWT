package com.application.services.implementations;

import com.application.entities.Product;
import com.application.repositories.ProductRepository;
import com.application.services.specifications.ProductServiceSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("productServiceBean")
public class ProductServiceImplementation implements ProductServiceSpecification {

    private ProductRepository productRepositoryBean;

    @Autowired
    public ProductServiceImplementation(ProductRepository productRepositoryBean) { this.productRepositoryBean = productRepositoryBean; }

    @Override
    public Product addProduct(Product product) { return productRepositoryBean.save(product); }

    @Override
    public List<Product> getProducts() { return productRepositoryBean.findAll(); }

}
