package com.test.service.dto;

import java.util.ArrayList;
import java.util.List;

public class CalculatedPlaceOrderDTO {

  private Long customerId;

  private Double bonus = 0.0;

  private List<RentMovieResultDTO> rentMovies = new ArrayList<>();

  private Double sumPrices = 0.0;

  private boolean useCustomerBonus;

  public boolean isUseCustomerBonus() {
    return useCustomerBonus;
  }

  public void setUseCustomerBonus(boolean useCustomerBonus) {
    this.useCustomerBonus = useCustomerBonus;
  }

  public Double getTotal() {
    return sumPrices - bonus;
  }

  public Long getCustomerId() {
    return customerId;
  }

  public void setCustomerId(Long customerId) {
    this.customerId = customerId;
  }

  public Double getBonus() {
    return bonus;
  }

  public void setBonus(Double bonus) {
    this.bonus = bonus;
  }

  public List<RentMovieResultDTO> getRentMovies() {
    return rentMovies;
  }

  public void setRentMovies(List<RentMovieResultDTO> rentMovieDay) {
    this.rentMovies = rentMovieDay;
  }

  public Double getSumPrices() {
    return sumPrices;
  }

  public void setSumPrices(Double sumPrices) {
    this.sumPrices = sumPrices;
  }

}
