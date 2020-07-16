package com.example.demo.models.database;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.ZonedDateTime;

@Data
@Entity
public class Product {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String name;

  private Double price;

  private Integer quantity;

  @CreationTimestamp private ZonedDateTime createdAt;

  @CreationTimestamp private ZonedDateTime updatedAt;
}
