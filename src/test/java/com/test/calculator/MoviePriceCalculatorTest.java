package com.test.calculator;

import com.test.domain.MovieTypePrice;
import com.test.domain.Price;
import com.test.service.dto.PlaceOrderDTO;
import org.junit.Assert;
import org.junit.Test;

public class MoviePriceCalculatorTest {

  @Test
  public void calculate() {
    MoviePriceCalculator calculator = new MoviePriceCalculator();
    MovieTypePrice movieTypePrice = new MovieTypePrice();
    movieTypePrice.setDays(3);
    Price price = new Price();
    price.setPrice(40.0);
    movieTypePrice.setPrice(price);
    PlaceOrderDTO orderDTO = new PlaceOrderDTO();
    orderDTO.setDays(5);
    Double calculatedPrice = calculator.calculate(movieTypePrice, orderDTO);
    Assert.assertEquals(80.0,calculatedPrice,3);
  }
}