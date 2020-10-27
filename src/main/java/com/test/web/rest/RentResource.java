package com.test.web.rest;

import com.test.service.RentService;
import com.test.web.rest.errors.BadRequestAlertException;
import com.test.service.dto.RentDTO;


import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.test.domain.Rent}.
 */
@RestController
@RequestMapping("/api")
public class RentResource {

    private final Logger log = LoggerFactory.getLogger(RentResource.class);

    private static final String ENTITY_NAME = "rent";



    private final RentService rentService;

    public RentResource(RentService rentService) {
        this.rentService = rentService;
    }

    /**
     * {@code POST  /rents} : Create a new rent.
     *
     * @param rentDTO the rentDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new rentDTO, or with status {@code 400 (Bad Request)} if the rent has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/rents")
    public ResponseEntity<RentDTO> createRent(@RequestBody RentDTO rentDTO) throws URISyntaxException {
        log.debug("REST request to save Rent : {}", rentDTO);
        if (rentDTO.getId() != null) {
            throw new BadRequestAlertException("A new rent cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RentDTO result = rentService.save(rentDTO);
        return ResponseEntity.created(new URI("/api/rents/" + result.getId()))

            .body(result);
    }

    /**
     * {@code PUT  /rents} : Updates an existing rent.
     *
     * @param rentDTO the rentDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated rentDTO,
     * or with status {@code 400 (Bad Request)} if the rentDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the rentDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/rents")
    public ResponseEntity<RentDTO> updateRent(@RequestBody RentDTO rentDTO) throws URISyntaxException {
        log.debug("REST request to update Rent : {}", rentDTO);
        if (rentDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        RentDTO result = rentService.save(rentDTO);
        return ResponseEntity.ok()
            .body(result);
    }

    /**
     * {@code GET  /rents} : get all the rents.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of rents in body.
     */
    @GetMapping("/rents")
    public List<RentDTO> getAllRents() {
        log.debug("REST request to get all Rents");
        return rentService.findAll();
    }

    /**
     * {@code GET  /rents/:id} : get the "id" rent.
     *
     * @param id the id of the rentDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the rentDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/rents/{id}")
    public ResponseEntity<RentDTO> getRent(@PathVariable Long id) {
        log.debug("REST request to get Rent : {}", id);
        Optional<RentDTO> rentDTO = rentService.findOne(id);
        return ResponseUtil.wrapOrNotFound(rentDTO);
    }

    /**
     * {@code DELETE  /rents/:id} : delete the "id" rent.
     *
     * @param id the id of the rentDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/rents/{id}")
    public ResponseEntity<Void> deleteRent(@PathVariable Long id) {
        log.debug("REST request to delete Rent : {}", id);
        rentService.delete(id);
        return ResponseEntity.noContent()  .build();
    }
}
