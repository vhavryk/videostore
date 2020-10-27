package com.test.web.rest;

import com.test.service.MovieTypePriceService;
import com.test.web.rest.errors.BadRequestAlertException;
import com.test.service.dto.MovieTypePriceDTO;


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
 * REST controller for managing {@link com.test.domain.MovieTypePrice}.
 */
@RestController
@RequestMapping("/api")
public class MovieTypePriceResource {

    private final Logger log = LoggerFactory.getLogger(MovieTypePriceResource.class);

    private static final String ENTITY_NAME = "movieTypePrice";



    private final MovieTypePriceService movieTypePriceService;

    public MovieTypePriceResource(MovieTypePriceService movieTypePriceService) {
        this.movieTypePriceService = movieTypePriceService;
    }

    /**
     * {@code POST  /movie-type-prices} : Create a new movieTypePrice.
     *
     * @param movieTypePriceDTO the movieTypePriceDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new movieTypePriceDTO, or with status {@code 400 (Bad Request)} if the movieTypePrice has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/movie-type-prices")
    public ResponseEntity<MovieTypePriceDTO> createMovieTypePrice(@RequestBody MovieTypePriceDTO movieTypePriceDTO) throws URISyntaxException {
        log.debug("REST request to save MovieTypePrice : {}", movieTypePriceDTO);
        if (movieTypePriceDTO.getId() != null) {
            throw new BadRequestAlertException("A new movieTypePrice cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MovieTypePriceDTO result = movieTypePriceService.save(movieTypePriceDTO);
        return ResponseEntity.created(new URI("/api/movie-type-prices/" + result.getId()))

            .body(result);
    }

    /**
     * {@code PUT  /movie-type-prices} : Updates an existing movieTypePrice.
     *
     * @param movieTypePriceDTO the movieTypePriceDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated movieTypePriceDTO,
     * or with status {@code 400 (Bad Request)} if the movieTypePriceDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the movieTypePriceDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/movie-type-prices")
    public ResponseEntity<MovieTypePriceDTO> updateMovieTypePrice(@RequestBody MovieTypePriceDTO movieTypePriceDTO) throws URISyntaxException {
        log.debug("REST request to update MovieTypePrice : {}", movieTypePriceDTO);
        if (movieTypePriceDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MovieTypePriceDTO result = movieTypePriceService.save(movieTypePriceDTO);
        return ResponseEntity.ok()
            .body(result);
    }

    /**
     * {@code GET  /movie-type-prices} : get all the movieTypePrices.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of movieTypePrices in body.
     */
    @GetMapping("/movie-type-prices")
    public List<MovieTypePriceDTO> getAllMovieTypePrices() {
        log.debug("REST request to get all MovieTypePrices");
        return movieTypePriceService.findAll();
    }

    /**
     * {@code GET  /movie-type-prices/:id} : get the "id" movieTypePrice.
     *
     * @param id the id of the movieTypePriceDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the movieTypePriceDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/movie-type-prices/{id}")
    public ResponseEntity<MovieTypePriceDTO> getMovieTypePrice(@PathVariable Long id) {
        log.debug("REST request to get MovieTypePrice : {}", id);
        Optional<MovieTypePriceDTO> movieTypePriceDTO = movieTypePriceService.findOne(id);
        return ResponseUtil.wrapOrNotFound(movieTypePriceDTO);
    }

    /**
     * {@code DELETE  /movie-type-prices/:id} : delete the "id" movieTypePrice.
     *
     * @param id the id of the movieTypePriceDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/movie-type-prices/{id}")
    public ResponseEntity<Void> deleteMovieTypePrice(@PathVariable Long id) {
        log.debug("REST request to delete MovieTypePrice : {}", id);
        movieTypePriceService.delete(id);
        return ResponseEntity.noContent()  .build();
    }
}
