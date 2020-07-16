package com.example.demo.controllers;

import com.example.demo.models.database.Product;
import com.example.demo.models.form.NewProductForm;
import com.example.demo.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/")
public class ProductController {

  @Autowired ProductRepository productRepository;

  @PostMapping("/product")
  public ResponseEntity<Object> create(@RequestBody NewProductForm productForm) {

    Product product = new Product();
    product.setName(productForm.getName());
    product.setPrice(productForm.getPrice());
    product.setQuantity(productForm.getQuantity());

    var productRes = productRepository.save(product);

    return new ResponseEntity<>(productRes, HttpStatus.CREATED);
  }
}
