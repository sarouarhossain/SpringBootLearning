package com.example.demo.controllers;

import com.example.demo.models.dto.CreatedResponse;
import com.example.demo.models.dto.ProductListResponse;
import com.example.demo.models.dto.ProductResponse;
import com.example.demo.models.form.NewProductForm;
import com.example.demo.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/")
public class ProductController {
  private ProductService productService;

  ProductController(ProductService productService) {
    this.productService = productService;
  }

  /**
   * Create product API
   *
   * @param productForm request body
   * @return
   */
  @PostMapping("/product")
  public ResponseEntity<CreatedResponse> create(@RequestBody NewProductForm productForm) {
    var response = productService.createNewProduct(productForm);
    return new ResponseEntity<>(response, HttpStatus.CREATED);
  }

  @GetMapping("/product")
  public ResponseEntity<ProductListResponse> getProducts() {
    var response = productService.getProducts(null, null);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @GetMapping("/product/{id}")
  public ResponseEntity<ProductResponse> getProduct(@PathVariable Long id) {
    var response = productService.getProduct(id);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }
}
