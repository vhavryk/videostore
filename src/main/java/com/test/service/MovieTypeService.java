package com.test.service;

import com.test.domain.MovieType;
import com.test.repository.MovieTypeRepository;
import com.test.service.dto.MovieTypeDTO;
import com.test.service.mapper.MovieTypeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link MovieType}.
 */
@Service
@Transactional
public class MovieTypeService {

    private final Logger log = LoggerFactory.getLogger(MovieTypeService.class);

    private final MovieTypeRepository movieTypeRepository;

    private final MovieTypeMapper movieTypeMapper;

    public MovieTypeService(MovieTypeRepository movieTypeRepository, MovieTypeMapper movieTypeMapper) {
        this.movieTypeRepository = movieTypeRepository;
        this.movieTypeMapper = movieTypeMapper;
    }

    /**
     * Save a movieType.
     *
     * @param movieTypeDTO the entity to save.
     * @return the persisted entity.
     */
    public MovieTypeDTO save(MovieTypeDTO movieTypeDTO) {
        log.debug("Request to save MovieType : {}", movieTypeDTO);
        MovieType movieType = movieTypeMapper.toEntity(movieTypeDTO);
        movieType = movieTypeRepository.save(movieType);
        return movieTypeMapper.toDto(movieType);
    }

    /**
     * Get all the movieTypes.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<MovieTypeDTO> findAll() {
        log.debug("Request to get all MovieTypes");
        return movieTypeRepository.findAll().stream()
            .map(movieTypeMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one movieType by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MovieTypeDTO> findOne(Long id) {
        log.debug("Request to get MovieType : {}", id);
        return movieTypeRepository.findById(id)
            .map(movieTypeMapper::toDto);
    }

    /**
     * Delete the movieType by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MovieType : {}", id);
        movieTypeRepository.deleteById(id);
    }
}
