package com.test.service;

import com.test.domain.BonusPrice;
import com.test.repository.BonusPriceRepository;
import com.test.service.dto.BonusPriceDTO;
import com.test.service.mapper.BonusPriceMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link BonusPrice}.
 */
@Service
@Transactional
public class BonusPriceService {

    private final Logger log = LoggerFactory.getLogger(BonusPriceService.class);

    private final BonusPriceRepository bonusPriceRepository;

    private final BonusPriceMapper bonusPriceMapper;

    public BonusPriceService(BonusPriceRepository bonusPriceRepository, BonusPriceMapper bonusPriceMapper) {
        this.bonusPriceRepository = bonusPriceRepository;
        this.bonusPriceMapper = bonusPriceMapper;
    }

    /**
     * Save a bonusPrice.
     *
     * @param bonusPriceDTO the entity to save.
     * @return the persisted entity.
     */
    public BonusPriceDTO save(BonusPriceDTO bonusPriceDTO) {
        log.debug("Request to save BonusPrice : {}", bonusPriceDTO);
        BonusPrice bonusPrice = bonusPriceMapper.toEntity(bonusPriceDTO);
        bonusPrice = bonusPriceRepository.save(bonusPrice);
        return bonusPriceMapper.toDto(bonusPrice);
    }

    /**
     * Get all the bonusPrices.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<BonusPriceDTO> findAll() {
        log.debug("Request to get all BonusPrices");
        return bonusPriceRepository.findAll().stream()
            .map(bonusPriceMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one bonusPrice by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<BonusPriceDTO> findOne(Long id) {
        log.debug("Request to get BonusPrice : {}", id);
        return bonusPriceRepository.findById(id)
            .map(bonusPriceMapper::toDto);
    }

    /**
     * Delete the bonusPrice by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete BonusPrice : {}", id);
        bonusPriceRepository.deleteById(id);
    }
}
