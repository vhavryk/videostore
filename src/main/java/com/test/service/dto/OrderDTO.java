package com.test.service.dto;

import java.time.LocalDate;

public class OrderDTO {

  private Long id;

  private LocalDate localDate;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public LocalDate getLocalDate() {
    return localDate;
  }

  public void setLocalDate(LocalDate localDate) {
    this.localDate = localDate;
  }

}
