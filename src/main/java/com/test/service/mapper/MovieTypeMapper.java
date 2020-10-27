package com.test.service.mapper;


import com.test.domain.*;
import com.test.service.dto.MovieTypeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MovieType} and its DTO {@link MovieTypeDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MovieTypeMapper extends EntityMapper<MovieTypeDTO, MovieType> {


    @Mapping(target = "movieTypePrices", ignore = true)
    @Mapping(target = "removeMovieTypePrice", ignore = true)
    @Mapping(target = "movies", ignore = true)
    @Mapping(target = "removeMovie", ignore = true)
    MovieType toEntity(MovieTypeDTO movieTypeDTO);

    default MovieType fromId(Long id) {
        if (id == null) {
            return null;
        }
        MovieType movieType = new MovieType();
        movieType.setId(id);
        return movieType;
    }
}
