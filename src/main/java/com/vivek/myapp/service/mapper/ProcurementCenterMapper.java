package com.vivek.myapp.service.mapper;

import com.vivek.myapp.domain.*;
import com.vivek.myapp.service.dto.ProcurementCenterDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ProcurementCenter} and its DTO {@link ProcurementCenterDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ProcurementCenterMapper extends EntityMapper<ProcurementCenterDTO, ProcurementCenter> {



    default ProcurementCenter fromId(Long id) {
        if (id == null) {
            return null;
        }
        ProcurementCenter procurementCenter = new ProcurementCenter();
        procurementCenter.setId(id);
        return procurementCenter;
    }
}
