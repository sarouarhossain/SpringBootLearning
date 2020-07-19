package com.example.demo.services;

import com.example.demo.models.dto.CreatedResponse;
import com.example.demo.models.dto.ProductListResponse;
import com.example.demo.models.dto.ProductResponse;
import com.example.demo.models.form.NewProductForm;
import com.example.demo.models.form.UpdateProductForm;

public interface ProductService {
  CreatedResponse createNewProduct(NewProductForm productForm);

  Object updateProduct(UpdateProductForm productForm);

  ProductResponse getProduct(Long id);

  ProductListResponse getProducts(Integer pageNumber, Integer limit);

  Object deleteProduct(Long id);
}
