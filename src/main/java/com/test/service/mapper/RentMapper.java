package com.test.service.mapper;


import com.test.domain.*;
import com.test.service.dto.RentDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Rent} and its DTO {@link RentDTO}.
 */
@Mapper(componentModel = "spring", uses = {CustomerMapper.class, MovieMapper.class,OrderMapper.class})
public interface RentMapper extends EntityMapper<RentDTO, Rent> {

    @Mapping(source = "customer.id", target = "customerId")
    @Mapping(source = "movie.id", target = "movieId")
    @Mapping(source = "order.id", target = "orderId")
    RentDTO toDto(Rent rent);

    @Mapping(source = "customerId", target = "customer")
    @Mapping(source = "movieId", target = "movie")
    @Mapping(source = "orderId", target = "order")
    Rent toEntity(RentDTO rentDTO);

    default Rent fromId(Long id) {
        if (id == null) {
            return null;
        }
        Rent rent = new Rent();
        rent.setId(id);
        return rent;
    }
}
