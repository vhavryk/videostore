package com.test.service.mapper;


import com.test.domain.Order;
import com.test.service.dto.OrderDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the entity {@link Order} and its DTO {@link OrderDTO}.
 */
@Mapper(componentModel = "spring")
public interface OrderMapper extends EntityMapper<OrderDTO, Order> {

    OrderDTO toDto(Order movie);

    @Mapping(target = "rents", ignore = true)
    @Mapping(target = "removeRent", ignore = true)
    Order toEntity(OrderDTO movieDTO);

    default Order fromId(Long id) {
        if (id == null) {
            return null;
        }
        Order movie = new Order();
        movie.setId(id);
        return movie;
    }
}
