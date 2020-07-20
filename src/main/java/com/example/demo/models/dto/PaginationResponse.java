package com.example.demo.models.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaginationResponse {
  private Long currentPage;
  private Long pageSize;
  private Long totalPage;
  private Long totalData;
}
