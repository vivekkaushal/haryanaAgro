package com.vivek.myapp.service.mapper;

import com.vivek.myapp.domain.*;
import com.vivek.myapp.service.dto.CropProcurementPassDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link CropProcurementPass} and its DTO {@link CropProcurementPassDTO}.
 */
@Mapper(componentModel = "spring", uses = {ProcurementCenterMapper.class, FarmerMapper.class, EmployeeMapper.class})
public interface CropProcurementPassMapper extends EntityMapper<CropProcurementPassDTO, CropProcurementPass> {

    @Mapping(source = "procurementCenter.id", target = "procurementCenterId")
    @Mapping(source = "farmer.id", target = "farmerId")
    @Mapping(source = "passApprovedBy.id", target = "passApprovedById")
    @Mapping(source = "createdBy.id", target = "createdById")
    @Mapping(source = "modifiedBy.id", target = "modifiedById")
    CropProcurementPassDTO toDto(CropProcurementPass cropProcurementPass);

    @Mapping(source = "procurementCenterId", target = "procurementCenter")
    @Mapping(source = "farmerId", target = "farmer")
    @Mapping(source = "passApprovedById", target = "passApprovedBy")
    @Mapping(source = "createdById", target = "createdBy")
    @Mapping(source = "modifiedById", target = "modifiedBy")
    CropProcurementPass toEntity(CropProcurementPassDTO cropProcurementPassDTO);

    default CropProcurementPass fromId(Long id) {
        if (id == null) {
            return null;
        }
        CropProcurementPass cropProcurementPass = new CropProcurementPass();
        cropProcurementPass.setId(id);
        return cropProcurementPass;
    }
}
