package com.test.service.mapper;


import com.test.domain.*;
import com.test.service.dto.PriceDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Price} and its DTO {@link PriceDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface PriceMapper extends EntityMapper<PriceDTO, Price> {


    @Mapping(target = "moviewTypePrices", ignore = true)
    @Mapping(target = "removeMoviewTypePrice", ignore = true)
    Price toEntity(PriceDTO priceDTO);

    default Price fromId(Long id) {
        if (id == null) {
            return null;
        }
        Price price = new Price();
        price.setId(id);
        return price;
    }
}
