package com.test.service.dto;

import java.util.ArrayList;
import java.util.List;

public class ReturnOrderDTO {

  private List<Long> movieIds = new ArrayList<>();

  private Long customerId;

  private boolean useCustomerBonus;

  public List<Long> getMovieIds() {
    return movieIds;
  }

  public void setMovieIds(List<Long> movieIds) {
    this.movieIds = movieIds;
  }

  public Long getCustomerId() {
    return customerId;
  }

  public void setCustomerId(Long customerId) {
    this.customerId = customerId;
  }

  public boolean isUseCustomerBonus() {
    return useCustomerBonus;
  }

  public void setUseCustomerBonus(boolean useCustomerBonus) {
    this.useCustomerBonus = useCustomerBonus;
  }

  @Override
  public String toString() {
    return "CalculateOrderDTO{" +
        "movieIds=" + movieIds +
        ", customerId=" + customerId +
        ", useCustomerBonus=" + useCustomerBonus +
        '}';
  }
}
