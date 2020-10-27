package com.test.service.dto;

import java.util.List;

public class AcceptOrderReturnResultDTO {

  private CalculatedPlaceOrderDTO calculatedResult;
  private List<RentDTO> rents;

  public void setCalculatedResult(CalculatedPlaceOrderDTO calculatedResult) {
    this.calculatedResult = calculatedResult;
  }

  public CalculatedPlaceOrderDTO getCalculatedResult() {
    return calculatedResult;
  }

  public void setRents(List<RentDTO> rents) {
    this.rents = rents;
  }

  public List<RentDTO> getRents() {
    return rents;
  }
}
