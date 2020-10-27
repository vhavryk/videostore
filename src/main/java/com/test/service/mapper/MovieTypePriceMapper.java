package com.test.service.mapper;


import com.test.domain.*;
import com.test.service.dto.MovieTypePriceDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MovieTypePrice} and its DTO {@link MovieTypePriceDTO}.
 */
@Mapper(componentModel = "spring", uses = {MovieTypeMapper.class, PriceMapper.class})
public interface MovieTypePriceMapper extends EntityMapper<MovieTypePriceDTO, MovieTypePrice> {

    @Mapping(source = "movieType.id", target = "movieTypeId")
    @Mapping(source = "price.id", target = "priceId")
    MovieTypePriceDTO toDto(MovieTypePrice movieTypePrice);

    @Mapping(source = "movieTypeId", target = "movieType")
    @Mapping(source = "priceId", target = "price")
    MovieTypePrice toEntity(MovieTypePriceDTO movieTypePriceDTO);

    default MovieTypePrice fromId(Long id) {
        if (id == null) {
            return null;
        }
        MovieTypePrice movieTypePrice = new MovieTypePrice();
        movieTypePrice.setId(id);
        return movieTypePrice;
    }
}
