package com.example.demo.models.form;

import lombok.Data;

@Data
public class UpdateProductForm {
  private String name;
  private Double price;
  private Integer quantity;
}
