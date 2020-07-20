package com.example.demo.services;

import com.example.demo.models.database.Product;
import com.example.demo.models.dto.*;
import com.example.demo.models.form.NewProductForm;
import com.example.demo.models.form.UpdateProductForm;
import com.example.demo.repositories.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
  public UpdatedResponse updateProduct(Long id, UpdateProductForm productForm) {
    Optional<Product> optionalProduct = productRepository.findById(id);

    if (optionalProduct.isPresent()) {
      Product product = optionalProduct.get();

      if (productForm.getName() != null) {
        product.setName(productForm.getName());
      }

      if (productForm.getPrice() != null) {
        product.setPrice(productForm.getPrice());
      }

      //      if (productForm.getQuantity() != null) {
      //        product.setQuantity(productForm.getQuantity());
      //      }
      product.setQuantity(
          productForm.getQuantity() != null ? productForm.getQuantity() : product.getQuantity());

      productRepository.save(product);

      return UpdatedResponse.builder().updated("OK").build();
    } else {
      return null;
    }
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
  public ProductListResponse getProducts(Integer pageNumber, Integer pageLimit) {
    if (pageNumber == null && pageLimit == null) {
      List<Product> productList = productRepository.findAll();
      var productResponseList = convertProductListEntityToResponse(productList);
      return ProductListResponse.builder().products(productResponseList).build();
    } else {
      pageNumber = pageNumber < 1 ? 0 : pageNumber - 1;
      Pageable pageable = PageRequest.of(pageNumber, pageLimit, Sort.by("id").ascending());
      Page<Product> productPage = productRepository.findAll(pageable);
      List<Product> productList = productPage.stream().collect(Collectors.toList());
      long totalData = productPage.getTotalElements();
      long totalPages = productPage.getTotalPages();
      var productResponseList = convertProductListEntityToResponse(productList);

      var paginationResponse =
          PaginationResponse.builder()
              .currentPage(pageNumber.longValue() + 1)
              .pageSize(pageLimit.longValue())
              .totalData(totalData)
              .totalPage(totalPages)
              .build();
      return ProductListResponse.builder()
          .pagination(paginationResponse)
          .products(productResponseList)
          .build();
    }
  }

  @Override
  public void deleteProduct(Long id) {
    Optional<Product> optionalProduct = productRepository.findById(id);
    if (optionalProduct.isPresent()) {
      Product product = optionalProduct.get();
      productRepository.delete(product);
    }

    // productRepository.deleteById(id);
  }

  /**
   * convert entity product list to response product list
   *
   * @param productList entity product list
   * @return product list response [ProductListResponse]
   */
  private List<ProductResponse> convertProductListEntityToResponse(List<Product> productList) {

    List<ProductResponse> responseProductList = new ArrayList<>();
    for (Product product : productList) {
      responseProductList.add(convertProductToProductResponse(product));
    }

    return responseProductList;
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
