package com.test.service;

import com.test.domain.Movie;
import com.test.repository.MovieRepository;
import com.test.service.dto.MovieDTO;
import com.test.service.mapper.MovieMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Movie}.
 */
@Service
@Transactional
public class MovieService {

  private final Logger log = LoggerFactory.getLogger(MovieService.class);

  private final MovieRepository movieRepository;

  private final MovieMapper movieMapper;

  public MovieService(MovieRepository movieRepository, MovieMapper movieMapper) {
    this.movieRepository = movieRepository;
    this.movieMapper = movieMapper;
  }

  /**
   * Save a movie.
   *
   * @param movieDTO the entity to save.
   * @return the persisted entity.
   */
  public MovieDTO save(MovieDTO movieDTO) {
    log.debug("Request to save Movie : {}", movieDTO);
    Movie movie = movieMapper.toEntity(movieDTO);
    movie = movieRepository.save(movie);
    return movieMapper.toDto(movie);
  }

  /**
   * Get all the movies.
   *
   * @return the list of entities.
   */
  @Transactional(readOnly = true)
  public List<MovieDTO> findAll() {
    log.debug("Request to get all Movies");
    return movieRepository.findAll().stream()
        .map(movieMapper::toDto)
        .collect(Collectors.toCollection(LinkedList::new));
  }


  /**
   * Get one movie by id.
   *
   * @param id the id of the entity.
   * @return the entity.
   */
  @Transactional(readOnly = true)
  public Optional<MovieDTO> findOne(Long id) {
    log.debug("Request to get Movie : {}", id);
    return movieRepository.findById(id)
        .map(movieMapper::toDto);
  }

  /**
   * Delete the movie by id.
   *
   * @param id the id of the entity.
   */
  public void delete(Long id) {
    log.debug("Request to delete Movie : {}", id);
    movieRepository.deleteById(id);
  }

  public List<MovieDTO> findAll(List<Long> movieIds) {
    log.debug("Request to get all Movies by ids :" + movieIds);
    return movieRepository.findAllById(movieIds).stream()
        .map(movieMapper::toDto)
        .collect(Collectors.toCollection(LinkedList::new));
  }
}
