package com.test.service;

import com.test.calculator.MoviePriceCalculator;
import com.test.domain.BonusPrice;
import com.test.domain.Customer;
import com.test.domain.Movie;
import com.test.domain.MovieTypePrice;
import com.test.domain.Order;
import com.test.repository.BonusPriceRepository;
import com.test.repository.CustomerRepository;
import com.test.repository.MovieRepository;
import com.test.repository.MovieTypePriceRepository;
import com.test.repository.OrderRepository;
import com.test.service.dto.AcceptOrderReturnResultDTO;
import com.test.service.dto.CalculatedPlaceOrderDTO;
import com.test.service.dto.OrderDTO;
import com.test.service.dto.PlaceOrderDTO;
import com.test.service.dto.PlaceOrderResultDTO;
import com.test.service.dto.RentDTO;
import com.test.service.dto.RentMovieDaysDTO;
import com.test.service.dto.RentMovieResultDTO;
import com.test.service.dto.ReturnOrderDTO;
import com.test.service.mapper.OrderMapper;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Order}.
 */
@Service
@Transactional
public class OrderService {

  public static final int DEFAULT_PER_BONUS_PRICE = 5;
  private final Logger log = LoggerFactory.getLogger(OrderService.class);

  private final OrderRepository orderRepository;

  private final OrderMapper orderMapper;

  private final MovieRepository movieRepository;

  private final RentService rentService;

  private final MovieTypePriceRepository movieTypePriceRepository;

  private final CustomerRepository customerRepository;

  private final BonusPriceRepository bonusPriceRepository;

  private MoviePriceCalculator calculator = new MoviePriceCalculator();

  public OrderService(
      OrderRepository orderRepository,
      OrderMapper orderMapper, MovieRepository movieService,
      MovieTypePriceRepository movieTypePriceRepository,
      CustomerRepository customerRepository,
      BonusPriceRepository bonusPriceRepository,
      RentService rentService) {
    this.orderRepository = orderRepository;
    this.orderMapper = orderMapper;
    this.movieRepository = movieService;
    this.movieTypePriceRepository = movieTypePriceRepository;
    this.customerRepository = customerRepository;
    this.bonusPriceRepository = bonusPriceRepository;
    this.rentService = rentService;
  }

  /**
   * Save a order.
   *
   * @param orderDTO the entity to save.
   * @return the persisted entity.
   */
  public OrderDTO save(OrderDTO orderDTO) {
    log.debug("Request to save Order : {}", orderDTO);
    Order order = orderMapper.toEntity(orderDTO);
    order = orderRepository.save(order);
    return orderMapper.toDto(order);
  }

  /**
   * Get all the orders.
   *
   * @return the list of entities.
   */
  @Transactional(readOnly = true)
  public List<OrderDTO> findAll() {
    log.debug("Request to get all Orders");
    return orderRepository.findAll().stream()
        .map(orderMapper::toDto)
        .collect(Collectors.toCollection(LinkedList::new));
  }


  /**
   * Get one order by id.
   *
   * @param id the id of the entity.
   * @return the entity.
   */
  @Transactional(readOnly = true)
  public Optional<OrderDTO> findOne(Long id) {
    log.debug("Request to get Order : {}", id);
    return orderRepository.findById(id)
        .map(orderMapper::toDto);
  }

  /**
   * Delete the order by id.
   *
   * @param id the id of the entity.
   */
  public void delete(Long id) {
    log.debug("Request to delete Order : {}", id);
    orderRepository.deleteById(id);
  }

  public CalculatedPlaceOrderDTO calculate(PlaceOrderDTO orderDTO) {
    CalculatedPlaceOrderDTO result = new CalculatedPlaceOrderDTO();
    Double bonus = calculateBonus(orderDTO);
    populateMoviePrices(orderDTO, result);
    result.setBonus(bonus);
    result.setUseCustomerBonus(orderDTO.isUseCustomerBonus());
    result.setCustomerId(orderDTO.getCustomerId());
    return result;
  }

  private List<RentMovieResultDTO> populateMoviePrices(PlaceOrderDTO orderDTO, CalculatedPlaceOrderDTO result) {
    Double sumPrices = 0.0;
    List<RentMovieResultDTO> orderMovies = new ArrayList<>();
    for (RentMovieDaysDTO rentMovie : orderDTO.getRentMovies()) {
      Optional<Movie> foundMovie = movieRepository.findById(rentMovie.getMovieId());
      if (foundMovie.isPresent()) {
        Movie movie = foundMovie.get();
        Long movieTypeId = movie.getMovieType().getId();
        Optional<MovieTypePrice> movieTypePriceOptional = movieTypePriceRepository.findByMovieTypeId(movieTypeId);
        if (movieTypePriceOptional.isPresent()) {
          MovieTypePrice movieTypePrice = movieTypePriceOptional.get();
          RentMovieResultDTO orderMovieDTO = createOrderMovieDTO(movie, movieTypePrice);
          orderMovieDTO.setDays(rentMovie.getDays());
          orderMovies.add(orderMovieDTO);
          sumPrices = sumPrices + calculator.calculate(movieTypePrice, rentMovie.getDays());
        }
      }
    }
    result.setSumPrices(sumPrices);
    result.setRentMovies(orderMovies);
    return orderMovies;
  }

  private RentMovieResultDTO createOrderMovieDTO(Movie movie, MovieTypePrice movieTypePrice) {
    RentMovieResultDTO rentMovieResultDTO = new RentMovieResultDTO();
    rentMovieResultDTO.setName(movie.getName());
    rentMovieResultDTO.setMovieId(movie.getId());
    rentMovieResultDTO.setMovieTypeName(movie.getMovieType().getName());
    rentMovieResultDTO.setMoviePrice(movieTypePrice.getPrice().getPrice());
    rentMovieResultDTO.setMoviePriceName(movieTypePrice.getPrice().getName());
    rentMovieResultDTO.setBonus(movie.getMovieType().getBonus());
    return rentMovieResultDTO;
  }

  private double calculateBonus(PlaceOrderDTO orderDTO) {
    double bonus = 0;
    if (orderDTO.isUseCustomerBonus()) {
      Optional<Customer> customer = customerRepository.findById(orderDTO.getCustomerId());
      if (customer.isPresent()) {
        bonus = customer.get().getBonus() * getPricePerBonus();
      }
    }
    return bonus;
  }

  private Double getPricePerBonus() {
    Optional<BonusPrice> bonusPrice = bonusPriceRepository.findByActive(true);
    return bonusPrice.isPresent() ? bonusPrice.get().getPrice() : DEFAULT_PER_BONUS_PRICE;
  }

  @Transactional
  public PlaceOrderResultDTO place(PlaceOrderDTO calculateOrderDTO) {
    Optional<Customer> customer = customerRepository.findById(calculateOrderDTO.getCustomerId());
    if (!customer.isPresent()) {
      throw new CustomerNotFoundException(calculateOrderDTO.getCustomerId());
    }
    CalculatedPlaceOrderDTO calculatedResult = calculate(calculateOrderDTO);
    PlaceOrderResultDTO placeOrderResultDTO = new PlaceOrderResultDTO();
    placeOrderResultDTO.setCalculatedResult(calculatedResult);
    Order order = new Order();
    order.setLocalDate(LocalDate.now());
    order = orderRepository.save(order);
    placeOrderResultDTO.setOrder(order);

    calculatedResult.getRentMovies().stream().forEach(saveRentMovies(calculateOrderDTO, placeOrderResultDTO, order));
    Integer bonus = calculatedResult.getRentMovies().stream().map(rm -> rm.getBonus()).reduce(0, Integer::sum);

    customer.ifPresent(c -> {
      Integer newBonus = calculateOrderDTO.isUseCustomerBonus() ? bonus : c.getBonus() + bonus;
      c.setBonus(newBonus);
      customerRepository.save(c);
    });

    return placeOrderResultDTO;
  }

  private Consumer<RentMovieResultDTO> saveRentMovies(PlaceOrderDTO calculateOrderDTO,
      PlaceOrderResultDTO placeOrderResultDTO, Order order) {
    return rm -> {
      RentDTO rentDTO = new RentDTO();
      rentDTO.setCustomerId(calculateOrderDTO.getCustomerId());
      rentDTO.setDate(LocalDate.now());
      rentDTO.setDays(rm.getDays());
      rentDTO.setMovieId(rm.getMovieId());
      rentDTO.setPaid(true);
      rentDTO.setPrice(rm.getMoviePrice());
      rentDTO.setOrderId(order.getId());
      rentDTO = rentService.save(rentDTO);
      placeOrderResultDTO.add(rentDTO);
    };
  }

  public CalculatedPlaceOrderDTO calculateReturn(ReturnOrderDTO calculateOrderDTO) {
    List<RentMovieDaysDTO> rentMovies = calculateOrderDTO.getMovieIds().stream()
        .map(id -> rentService.findByMovieIdAndCustomerId(id, calculateOrderDTO.getCustomerId()).orElse(null))
        .filter(Objects::nonNull)
        .map(rm -> createRentMovieDays(rm))
        .collect(Collectors.toList());

    PlaceOrderDTO orderDTO = new PlaceOrderDTO();
    orderDTO.setCustomerId(calculateOrderDTO.getCustomerId());
    orderDTO.setUseCustomerBonus(calculateOrderDTO.isUseCustomerBonus());
    orderDTO.setRentMovies(rentMovies);
    return calculate(orderDTO);
  }

  private RentMovieDaysDTO createRentMovieDays(RentDTO rm) {
    RentMovieDaysDTO rentMovieDTO = new RentMovieDaysDTO();
    rentMovieDTO.setMovieId(rm.getMovieId());
    LocalDate finishPayedDate = rm.getDate().plusDays(rm.getDays());
    int daysToPay = (int) ChronoUnit.DAYS.between(LocalDate.now(), finishPayedDate) - rm.getDays();
    rentMovieDTO.setDays(daysToPay > 0 ? daysToPay : 0);
    return rentMovieDTO;
  }

  public AcceptOrderReturnResultDTO acceptReturn(ReturnOrderDTO returnOrderDTO) {
    CalculatedPlaceOrderDTO resultDTO = calculateReturn(returnOrderDTO);
    AcceptOrderReturnResultDTO acceptOrderReturnResultDTO = new AcceptOrderReturnResultDTO();
    acceptOrderReturnResultDTO.setCalculatedResult(resultDTO);
    List<RentDTO> rents = resultDTO.getRentMovies().stream()
        .map(id -> acceptReturnMovie(returnOrderDTO, id))
        .filter(Objects::nonNull)
        .collect(Collectors.toList());

    acceptOrderReturnResultDTO.setRents(rents);
    return acceptOrderReturnResultDTO;
  }

  private RentDTO acceptReturnMovie(ReturnOrderDTO returnOrder, RentMovieResultDTO result) {
    Optional<RentDTO> rent = rentService.findByMovieIdAndCustomerId(result.getMovieId(), returnOrder.getCustomerId());
    return rent.map(rm -> acceptReturnRentMovie(rm,result.getMoviePrice())).orElse(null);
  }

  private RentDTO acceptReturnRentMovie(RentDTO rm, Double moviePrice) {
    rm.setReturnedDate(LocalDate.now());
    rm.setReturnedPaid(true);
    rm.setReturnPrice(moviePrice);
    return rentService.save(rm);
  }


}
