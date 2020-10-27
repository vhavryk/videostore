package com.test.repository;

import com.test.domain.Rent;

import java.util.Optional;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Rent entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RentRepository extends JpaRepository<Rent, Long> {

  Optional<Rent> findByMovieIdAndCustomerId(Long id, Long customerId);
}
