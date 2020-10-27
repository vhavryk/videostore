package com.test.repository;

import com.test.domain.MovieType;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the MovieType entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MovieTypeRepository extends JpaRepository<MovieType, Long> {
}
