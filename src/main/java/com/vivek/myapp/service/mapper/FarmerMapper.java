package com.vivek.myapp.service.mapper;

import com.vivek.myapp.domain.*;
import com.vivek.myapp.service.dto.FarmerDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Farmer} and its DTO {@link FarmerDTO}.
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface FarmerMapper extends EntityMapper<FarmerDTO, Farmer> {

    @Mapping(source = "user.id", target = "userId")
    FarmerDTO toDto(Farmer farmer);

    @Mapping(source = "userId", target = "user")
    Farmer toEntity(FarmerDTO farmerDTO);

    default Farmer fromId(Long id) {
        if (id == null) {
            return null;
        }
        Farmer farmer = new Farmer();
        farmer.setId(id);
        return farmer;
    }
}
