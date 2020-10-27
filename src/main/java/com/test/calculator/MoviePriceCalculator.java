package com.test.calculator;

import com.test.domain.MovieTypePrice;

public class MoviePriceCalculator {

  public Double calculate(MovieTypePrice movieTypePrice, Integer days) {
    double price = movieTypePrice.getPrice().getPrice();
    price = Math.ceil((double)days / movieTypePrice.getDays()) * price;
    return price;
  }
}
