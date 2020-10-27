package com.test.service;

public class CustomerNotFoundException extends RuntimeException {

  private Long customerId;

  public CustomerNotFoundException(Long customerId) {
    super();
    this.customerId = customerId;
  }
}
