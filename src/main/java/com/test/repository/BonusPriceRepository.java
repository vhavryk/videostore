package com.test.repository;

import com.test.domain.BonusPrice;

import java.util.Optional;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the BonusPrice entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BonusPriceRepository extends JpaRepository<BonusPrice, Long> {

  Optional<BonusPrice> findByActive(boolean b);
}
