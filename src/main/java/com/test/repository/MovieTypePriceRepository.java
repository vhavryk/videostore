package com.test.repository;

import com.test.domain.Movie;
import com.test.domain.MovieTypePrice;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the MovieTypePrice entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MovieTypePriceRepository extends JpaRepository<MovieTypePrice, Long> {

  Optional<MovieTypePrice> findByMovieTypeId(Long movieIds);
}
