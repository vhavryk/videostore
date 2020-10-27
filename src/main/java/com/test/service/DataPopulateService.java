package com.test.service;

import com.test.service.dto.CustomerDTO;
import com.test.service.dto.MovieDTO;
import com.test.service.dto.MovieTypeDTO;
import com.test.service.dto.MovieTypePriceDTO;
import com.test.service.dto.PriceDTO;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DataPopulateService {

  @Autowired
  private MovieService movieService;

  @Autowired
  private MovieTypeService movieTypeService;

  @Autowired
  private MovieTypePriceService movieTypePriceService;

  @Autowired
  private PriceService priceService;

  @Autowired
  private CustomerService customerService;

  @PostConstruct
  public void populate() {
    if (movieService.findAll().isEmpty()) {
      MovieTypeDTO newRelease = saveMovieType("New release", 2);
      MovieTypeDTO oldFilm = saveMovieType("Old film",0 );
      MovieTypeDTO regularRental = saveMovieType("Regular rental", 1);

      MovieDTO matrix11 = saveMovie("Matrix 11", newRelease);
      MovieDTO spiderMan = saveMovie("Spider Man", regularRental);
      MovieDTO spiderMan2 = saveMovie("Spider Man 2", regularRental);
      MovieDTO outOfAfrica = saveMovie("Out of Africa", oldFilm);

      PriceDTO premiumPrice = savePrice(40, "Premium");
      PriceDTO basicPrice = savePrice(30, "Basic");

      saveMovieTypePrice(newRelease, premiumPrice, 1);
      saveMovieTypePrice(regularRental, basicPrice, 3);
      saveMovieTypePrice(oldFilm, basicPrice, 5);

      CustomerDTO customerDTO = new CustomerDTO();
      customerDTO.setBonus(4L);
      customerDTO.setName("Greg");
      customerService.save(customerDTO);
    }
  }

  private void saveMovieTypePrice(MovieTypeDTO movieTypeDTO, PriceDTO priceDTO, int days) {
    MovieTypePriceDTO movieTypePriceDTO = new MovieTypePriceDTO();
    movieTypePriceDTO.setDays(days);
    movieTypePriceDTO.setNextDays(days);
    movieTypePriceDTO.setMovieTypeId(movieTypeDTO.getId());
    movieTypePriceDTO.setPriceId(priceDTO.getId());
    movieTypePriceService.save(movieTypePriceDTO);
  }

  private PriceDTO savePrice(double price, String premium) {
    PriceDTO priceDTO = new PriceDTO();
    priceDTO.setName(premium);
    priceDTO.setPrice(price);
    return priceService.save(priceDTO);
  }

  private MovieDTO saveMovie(String name, MovieTypeDTO newRelease) {
    MovieDTO movieDTO = new MovieDTO();
    movieDTO.setName(name);
    movieDTO.setMovieTypeId(newRelease.getId());
    movieService.save(movieDTO);
    return movieDTO;
  }

  private MovieTypeDTO saveMovieType(String name, Integer bonus) {
    MovieTypeDTO new_release = createMovieType(name,bonus);
    return movieTypeService.save(new_release);
  }

  private MovieTypeDTO createMovieType(String name, Integer bonus) {
    MovieTypeDTO movieTypeDTO = new MovieTypeDTO();
    movieTypeDTO.setName(name);
    movieTypeDTO.setBonus(bonus);
    return movieTypeDTO;
  }
}
