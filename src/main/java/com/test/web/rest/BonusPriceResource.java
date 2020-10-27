package com.test.web.rest;

import com.test.service.BonusPriceService;
import com.test.service.dto.BonusPriceDTO;
import com.test.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.web.util.ResponseUtil;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for managing {@link com.test.domain.BonusPrice}.
 */
@RestController
@RequestMapping("/api")
public class BonusPriceResource {

    private final Logger log = LoggerFactory.getLogger(BonusPriceResource.class);

    private static final String ENTITY_NAME = "bonusPrice";

    private final BonusPriceService bonusPriceService;

    public BonusPriceResource(BonusPriceService bonusPriceService) {
        this.bonusPriceService = bonusPriceService;
    }

    /**
     * {@code POST  /bonus-prices} : Create a new bonusPrice.
     *
     * @param bonusPriceDTO the bonusPriceDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new bonusPriceDTO, or with status {@code 400 (Bad Request)} if the bonusPrice has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/bonus-prices")
    public ResponseEntity<BonusPriceDTO> createBonusPrice(@RequestBody BonusPriceDTO bonusPriceDTO) throws URISyntaxException {
        log.debug("REST request to save BonusPrice : {}", bonusPriceDTO);
        if (bonusPriceDTO.getId() != null)
            throw new BadRequestAlertException("A new bonusPrice cannot already have an ID", ENTITY_NAME, "idexists");
        BonusPriceDTO result = bonusPriceService.save(bonusPriceDTO);
        return ResponseEntity.created(new URI("/api/bonus-prices/" + result.getId()))

            .body(result);
    }

    /**
     * {@code PUT  /bonus-prices} : Updates an existing bonusPrice.
     *
     * @param bonusPriceDTO the bonusPriceDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated bonusPriceDTO,
     * or with status {@code 400 (Bad Request)} if the bonusPriceDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the bonusPriceDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/bonus-prices")
    public ResponseEntity<BonusPriceDTO> updateBonusPrice(@RequestBody BonusPriceDTO bonusPriceDTO) throws URISyntaxException {
        log.debug("REST request to update BonusPrice : {}", bonusPriceDTO);
        if (bonusPriceDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        BonusPriceDTO result = bonusPriceService.save(bonusPriceDTO);
        return ResponseEntity.ok()

            .body(result);
    }

    /**
     * {@code GET  /bonus-prices} : get all the bonusPrices.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of bonusPrices in body.
     */
    @GetMapping("/bonus-prices")
    public List<BonusPriceDTO> getAllBonusPrices() {
        log.debug("REST request to get all BonusPrices");
        return bonusPriceService.findAll();
    }

    /**
     * {@code GET  /bonus-prices/:id} : get the "id" bonusPrice.
     *
     * @param id the id of the bonusPriceDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the bonusPriceDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/bonus-prices/{id}")
    public ResponseEntity<BonusPriceDTO> getBonusPrice(@PathVariable Long id) {
        log.debug("REST request to get BonusPrice : {}", id);
        Optional<BonusPriceDTO> bonusPriceDTO = bonusPriceService.findOne(id);
        return ResponseUtil.wrapOrNotFound(bonusPriceDTO);
    }

    /**
     * {@code DELETE  /bonus-prices/:id} : delete the "id" bonusPrice.
     *
     * @param id the id of the bonusPriceDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/bonus-prices/{id}")
    public ResponseEntity<Void> deleteBonusPrice(@PathVariable Long id) {
        log.debug("REST request to delete BonusPrice : {}", id);
        bonusPriceService.delete(id);
        return ResponseEntity.noContent()  .build();
    }
}
