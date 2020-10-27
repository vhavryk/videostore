package com.test.service.mapper;


import com.test.domain.*;
import com.test.service.dto.MovieDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Movie} and its DTO {@link MovieDTO}.
 */
@Mapper(componentModel = "spring", uses = {MovieTypeMapper.class})
public interface MovieMapper extends EntityMapper<MovieDTO, Movie> {

    @Mapping(source = "movieType.id", target = "movieTypeId")
    MovieDTO toDto(Movie movie);

    @Mapping(target = "rents", ignore = true)
    @Mapping(target = "removeRent", ignore = true)
    @Mapping(source = "movieTypeId", target = "movieType")
    Movie toEntity(MovieDTO movieDTO);

    default Movie fromId(Long id) {
        if (id == null) {
            return null;
        }
        Movie movie = new Movie();
        movie.setId(id);
        return movie;
    }
}
