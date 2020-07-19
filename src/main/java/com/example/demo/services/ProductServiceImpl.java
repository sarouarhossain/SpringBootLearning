package com.example.demo.services;

import com.example.demo.models.database.Product;
import com.example.demo.models.dto.CreatedResponse;
import com.example.demo.models.dto.ProductListResponse;
import com.example.demo.models.dto.ProductResponse;
import com.example.demo.models.form.NewProductForm;
import com.example.demo.models.form.UpdateProductForm;
import com.example.demo.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
  private ProductRepository productRepository;

  ProductServiceImpl(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  @Override
  public CreatedResponse createNewProduct(NewProductForm productForm) {
    Product product = new Product();
    product.setName(productForm.getName());
    product.setQuantity(productForm.getQuantity());
    product.setPrice(productForm.getPrice());

    productRepository.save(product);

    CreatedResponse response = CreatedResponse.builder().created("OK").build();
    return response;
  }

  @Override
  public Object updateProduct(UpdateProductForm productForm) {
    return null;
  }

  @Override
  public ProductResponse getProduct(Long id) {
    Optional<Product> optionalRes = productRepository.findById(id);
    if (optionalRes.isPresent()) {
      Product product = optionalRes.get();
      return convertProductToProductResponse(product);
    } else {
      return null;
    }
  }

  @Override
  public ProductListResponse getProducts(Integer pageNumber, Integer limit) {
    if (pageNumber == null && limit == null) {
      List<Product> productList = productRepository.findAll();
      return convertProductListEntityToResponse(productList);
    }
    return null;
  }

  @Override
  public Object deleteProduct(Long id) {
    return null;
  }

  /**
   * convert entity product list to response product list
   *
   * @param productList entity product list
   * @return product list response [ProductListResponse]
   */
  private ProductListResponse convertProductListEntityToResponse(List<Product> productList) {

    List<ProductResponse> responseProductList = new ArrayList<>();
    for (Product product : productList) {
      responseProductList.add(convertProductToProductResponse(product));
    }

    return ProductListResponse.builder().products(responseProductList).build();
  }

  /**
   * convert product entity to product response
   *
   * @param product entity product
   * @return product response [ProductResponse]
   */
  private ProductResponse convertProductToProductResponse(Product product) {
    return ProductResponse.builder()
        .id(product.getId())
        .name(product.getName())
        .quantity(product.getQuantity())
        .price(product.getPrice())
        .build();
  }
}
