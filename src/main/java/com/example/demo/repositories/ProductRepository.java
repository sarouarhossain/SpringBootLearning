package com.example.demo.repositories;

import com.example.demo.models.database.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

  @Query(
      value = "SELECT * FROM product p where p.quantity >= :minimum_quantity",
      nativeQuery = true)
  List<Product> getProductListByMinimumQuantity(@Param("minimum_quantity") Integer minimumQuantity);

  @Query(
      value = "SELECT * FROM product p where p.quantity >= :minimum_quantity",
      nativeQuery = true)
  Page<Product> getProductPageByMinimumQuantity(
      @Param("minimum_quantity") Integer minimumQuantity, Pageable pageable);
}
