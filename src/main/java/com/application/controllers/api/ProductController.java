package com.application.controllers.api;

import com.application.entities.Product;
import com.application.services.implementations.ProductServiceImplementation;
import com.application.services.specifications.ProductServiceSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {

    private ProductServiceSpecification productServiceBean;

    @Autowired
    public ProductController(ProductServiceImplementation productServiceBean) { this.productServiceBean = productServiceBean; }

    @GetMapping("/products")
    public List<Product> getProducts(){ return productServiceBean.getProducts(); }

    @PostMapping("/product/add")
    public Product addProduct(@RequestBody Product product){ return  productServiceBean.addProduct(product); }

}