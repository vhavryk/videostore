package com.test.service;

import com.test.domain.Rent;
import com.test.repository.RentRepository;
import com.test.service.dto.RentDTO;
import com.test.service.mapper.RentMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Rent}.
 */
@Service
@Transactional
public class RentService {

  private final Logger log = LoggerFactory.getLogger(RentService.class);

  private final RentRepository rentRepository;

  private final RentMapper rentMapper;

  public RentService(RentRepository rentRepository, RentMapper rentMapper) {
    this.rentRepository = rentRepository;
    this.rentMapper = rentMapper;
  }

  /**
   * Save a rent.
   *
   * @param rentDTO the entity to save.
   * @return the persisted entity.
   */
  public RentDTO save(RentDTO rentDTO) {
    log.debug("Request to save Rent : {}", rentDTO);
    Rent rent = rentMapper.toEntity(rentDTO);
    rent = rentRepository.save(rent);
    return rentMapper.toDto(rent);
  }

  /**
   * Get all the rents.
   *
   * @return the list of entities.
   */
  @Transactional(readOnly = true)
  public List<RentDTO> findAll() {
    log.debug("Request to get all Rents");
    return rentRepository.findAll().stream()
        .map(rentMapper::toDto)
        .collect(Collectors.toCollection(LinkedList::new));
  }


  /**
   * Get one rent by id.
   *
   * @param id the id of the entity.
   * @return the entity.
   */
  @Transactional(readOnly = true)
  public Optional<RentDTO> findOne(Long id) {
    log.debug("Request to get Rent : {}", id);
    return rentRepository.findById(id)
        .map(rentMapper::toDto);
  }

  /**
   * Delete the rent by id.
   *
   * @param id the id of the entity.
   */
  public void delete(Long id) {
    log.debug("Request to delete Rent : {}", id);
    rentRepository.deleteById(id);
  }

  public Optional<RentDTO> findByMovieIdAndCustomerId(Long id, Long customerId) {
    return rentRepository.findByMovieIdAndCustomerId(id, customerId).map(rentMapper::toDto);
  }
}
