package com.example.demo.models.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ProductListResponse {
  private PaginationResponse pagination;
  private List<ProductResponse> products;
}
