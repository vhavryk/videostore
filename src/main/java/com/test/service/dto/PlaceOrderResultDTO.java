package com.test.service.dto;

import com.test.domain.Order;
import java.util.ArrayList;
import java.util.List;

public class PlaceOrderResultDTO {

  private CalculatedPlaceOrderDTO calculatedResult;

  private List<RentDTO> rents = new ArrayList<>();
  private Order order;

  public void setCalculatedResult(CalculatedPlaceOrderDTO calculatedResult) {
    this.calculatedResult = calculatedResult;
  }

  public CalculatedPlaceOrderDTO getCalculatedResult() {
    return calculatedResult;
  }

  public void add(RentDTO rentDTO) {
    rents.add(rentDTO);
  }

  public void setOrder(Order order) {
    this.order = order;
  }

  public Order getOrder() {
    return order;
  }

  public List<RentDTO> getRents() {
    return rents;
  }

  public void setRents(List<RentDTO> rents) {
    this.rents = rents;
  }
}
