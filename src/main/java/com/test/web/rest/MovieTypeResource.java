package com.test.web.rest;

import com.test.service.MovieTypeService;
import com.test.web.rest.errors.BadRequestAlertException;
import com.test.service.dto.MovieTypeDTO;


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
 * REST controller for managing {@link com.test.domain.MovieType}.
 */
@RestController
@RequestMapping("/api")
public class MovieTypeResource {

    private final Logger log = LoggerFactory.getLogger(MovieTypeResource.class);

    private static final String ENTITY_NAME = "movieType";



    private final MovieTypeService movieTypeService;

    public MovieTypeResource(MovieTypeService movieTypeService) {
        this.movieTypeService = movieTypeService;
    }

    /**
     * {@code POST  /movie-types} : Create a new movieType.
     *
     * @param movieTypeDTO the movieTypeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new movieTypeDTO, or with status {@code 400 (Bad Request)} if the movieType has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/movie-types")
    public ResponseEntity<MovieTypeDTO> createMovieType(@RequestBody MovieTypeDTO movieTypeDTO) throws URISyntaxException {
        log.debug("REST request to save MovieType : {}", movieTypeDTO);
        if (movieTypeDTO.getId() != null) {
            throw new BadRequestAlertException("A new movieType cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MovieTypeDTO result = movieTypeService.save(movieTypeDTO);
        return ResponseEntity.created(new URI("/api/movie-types/" + result.getId()))

            .body(result);
    }

    /**
     * {@code PUT  /movie-types} : Updates an existing movieType.
     *
     * @param movieTypeDTO the movieTypeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated movieTypeDTO,
     * or with status {@code 400 (Bad Request)} if the movieTypeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the movieTypeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/movie-types")
    public ResponseEntity<MovieTypeDTO> updateMovieType(@RequestBody MovieTypeDTO movieTypeDTO) throws URISyntaxException {
        log.debug("REST request to update MovieType : {}", movieTypeDTO);
        if (movieTypeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MovieTypeDTO result = movieTypeService.save(movieTypeDTO);
        return ResponseEntity.ok()
            .body(result);
    }

    /**
     * {@code GET  /movie-types} : get all the movieTypes.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of movieTypes in body.
     */
    @GetMapping("/movie-types")
    public List<MovieTypeDTO> getAllMovieTypes() {
        log.debug("REST request to get all MovieTypes");
        return movieTypeService.findAll();
    }

    /**
     * {@code GET  /movie-types/:id} : get the "id" movieType.
     *
     * @param id the id of the movieTypeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the movieTypeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/movie-types/{id}")
    public ResponseEntity<MovieTypeDTO> getMovieType(@PathVariable Long id) {
        log.debug("REST request to get MovieType : {}", id);
        Optional<MovieTypeDTO> movieTypeDTO = movieTypeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(movieTypeDTO);
    }

    /**
     * {@code DELETE  /movie-types/:id} : delete the "id" movieType.
     *
     * @param id the id of the movieTypeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/movie-types/{id}")
    public ResponseEntity<Void> deleteMovieType(@PathVariable Long id) {
        log.debug("REST request to delete MovieType : {}", id);
        movieTypeService.delete(id);
        return ResponseEntity.noContent()  .build();
    }
}
