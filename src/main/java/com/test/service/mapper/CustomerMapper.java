package com.test.service.mapper;


import com.test.domain.*;
import com.test.service.dto.CustomerDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Customer} and its DTO {@link CustomerDTO}.
 */
@Mapper(componentModel = "spring")
public interface CustomerMapper extends EntityMapper<CustomerDTO, Customer> {

    @Mapping(source = "bonus", target = "bonus")
    CustomerDTO toDto(Customer customer);

    @Mapping(source = "bonus", target = "bonus")
    @Mapping(target = "rents", ignore = true)
    @Mapping(target = "removeRent", ignore = true)
    Customer toEntity(CustomerDTO customerDTO);

    default Customer fromId(Long id) {
        if (id == null) {
            return null;
        }
        Customer customer = new Customer();
        customer.setId(id);
        return customer;
    }
}
