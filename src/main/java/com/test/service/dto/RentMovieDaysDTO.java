package com.test.service.dto;

public class RentMovieDaysDTO {

  private Long movieId;

  private Integer days = 1;

  public Long getMovieId() {
    return movieId;
  }

  public void setMovieId(Long movieId) {
    this.movieId = movieId;
  }

  public Integer getDays() {
    return days;
  }

  public void setDays(Integer days) {
    this.days = days;
  }
}
