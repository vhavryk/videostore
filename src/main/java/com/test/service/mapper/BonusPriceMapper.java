package com.test.service.mapper;


import com.test.domain.*;
import com.test.service.dto.BonusPriceDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link BonusPrice} and its DTO {@link BonusPriceDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface BonusPriceMapper extends EntityMapper<BonusPriceDTO, BonusPrice> {



    default BonusPrice fromId(Long id) {
        if (id == null) {
            return null;
        }
        BonusPrice bonusPrice = new BonusPrice();
        bonusPrice.setId(id);
        return bonusPrice;
    }
}
