package com.vivek.myapp.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class CropProcurementPassMapperTest {

    private CropProcurementPassMapper cropProcurementPassMapper;

    @BeforeEach
    public void setUp() {
        cropProcurementPassMapper = new CropProcurementPassMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(cropProcurementPassMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(cropProcurementPassMapper.fromId(null)).isNull();
    }
}
