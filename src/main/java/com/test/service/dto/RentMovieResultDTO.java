package com.test.service.dto;

public class RentMovieResultDTO {

  private String name;
  private String movieTypeName;
  private Double moviePrice;
  private String moviePriceName;
  private Integer days;
  private Long movieId;
  private Integer bonus;

  public void setName(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setMovieTypeName(String movieTypeName) {
    this.movieTypeName = movieTypeName;
  }

  public String getMovieTypeName() {
    return movieTypeName;
  }

  public void setMoviePrice(Double moviePrice) {
    this.moviePrice = moviePrice;
  }

  public Double getMoviePrice() {
    return moviePrice;
  }

  public void setMoviePriceName(String moviePriceName) {
    this.moviePriceName = moviePriceName;
  }

  public String getMoviePriceName() {
    return moviePriceName;
  }

  public void setDays(Integer days) {
    this.days = days;
  }

  public Integer getDays() {
    return days;
  }

  public void setMovieId(Long movieId) {
    this.movieId = movieId;
  }

  public Long getMovieId() {
    return movieId;
  }

  public void setBonus(Integer bonus) {
    this.bonus = bonus;
  }

  public Integer getBonus() {
    return bonus;
  }
}
