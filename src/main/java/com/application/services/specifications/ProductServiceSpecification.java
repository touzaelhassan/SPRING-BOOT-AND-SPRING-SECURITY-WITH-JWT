package com.application.services.specifications;

import com.application.entities.Product;

import java.util.List;

public interface ProductServiceSpecification {

    Product addProduct(Product product);
    List<Product> getProducts();

}
