package com.test.service;

import com.test.domain.MovieTypePrice;
import com.test.repository.MovieTypePriceRepository;
import com.test.service.dto.MovieTypePriceDTO;
import com.test.service.mapper.MovieTypePriceMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link MovieTypePrice}.
 */
@Service
@Transactional
public class MovieTypePriceService {

  private final Logger log = LoggerFactory.getLogger(MovieTypePriceService.class);

  private final MovieTypePriceRepository movieTypePriceRepository;

  private final MovieTypePriceMapper movieTypePriceMapper;

  public MovieTypePriceService(MovieTypePriceRepository movieTypePriceRepository,
      MovieTypePriceMapper movieTypePriceMapper) {
    this.movieTypePriceRepository = movieTypePriceRepository;
    this.movieTypePriceMapper = movieTypePriceMapper;
  }

  /**
   * Save a movieTypePrice.
   *
   * @param movieTypePriceDTO the entity to save.
   * @return the persisted entity.
   */
  public MovieTypePriceDTO save(MovieTypePriceDTO movieTypePriceDTO) {
    log.debug("Request to save MovieTypePrice : {}", movieTypePriceDTO);
    MovieTypePrice movieTypePrice = movieTypePriceMapper.toEntity(movieTypePriceDTO);
    movieTypePrice = movieTypePriceRepository.save(movieTypePrice);
    return movieTypePriceMapper.toDto(movieTypePrice);
  }

  /**
   * Get all the movieTypePrices.
   *
   * @return the list of entities.
   */
  @Transactional(readOnly = true)
  public List<MovieTypePriceDTO> findAll() {
    log.debug("Request to get all MovieTypePrices");
    return movieTypePriceRepository.findAll().stream()
        .map(movieTypePriceMapper::toDto)
        .collect(Collectors.toCollection(LinkedList::new));
  }


  /**
   * Get one movieTypePrice by id.
   *
   * @param id the id of the entity.
   * @return the entity.
   */
  @Transactional(readOnly = true)
  public Optional<MovieTypePriceDTO> findOne(Long id) {
    log.debug("Request to get MovieTypePrice : {}", id);
    return movieTypePriceRepository.findById(id)
        .map(movieTypePriceMapper::toDto);
  }

  /**
   * Delete the movieTypePrice by id.
   *
   * @param id the id of the entity.
   */
  public void delete(Long id) {
    log.debug("Request to delete MovieTypePrice : {}", id);
    movieTypePriceRepository.deleteById(id);
  }

  public Optional<MovieTypePriceDTO> findByMovieTypeId(Long movieTypeId) {
    log.debug("Request to find MovieTypePrice by movieTypeId: {}", movieTypeId);
    return movieTypePriceRepository.findByMovieTypeId(movieTypeId).map(movieTypePriceMapper::toDto);
  }
}
