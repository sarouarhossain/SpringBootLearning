package com.example.demo.controllers;

import com.example.demo.models.dto.CreatedResponse;
import com.example.demo.models.dto.ProductListResponse;
import com.example.demo.models.dto.ProductResponse;
import com.example.demo.models.dto.UpdatedResponse;
import com.example.demo.models.form.NewProductForm;
import com.example.demo.models.form.UpdateProductForm;
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
  public ResponseEntity<ProductListResponse> getList(
      @RequestParam(value = "pageNo", required = false) Integer pageNo,
      @RequestParam(value = "pageLimit", required = false) Integer pageLimit,
      @RequestParam(value = "quantitityMinimum", required = false) Integer minimumQuantity) {
    var response = productService.getProducts(pageNo, pageLimit, minimumQuantity);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @GetMapping("/product/{id}")
  public ResponseEntity<ProductResponse> get(@PathVariable Long id) {
    var response = productService.getProduct(id);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @PutMapping("/product/{id}")
  public ResponseEntity<UpdatedResponse> update(
      @PathVariable Long id, @RequestBody UpdateProductForm productForm) {
    var response = productService.updateProduct(id, productForm);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @DeleteMapping("/product/{id}")
  public ResponseEntity<Object> delete(@PathVariable Long id) {
    productService.deleteProduct(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
