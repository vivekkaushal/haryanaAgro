package com.vivek.myapp.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class ProcurementCenterMapperTest {

    private ProcurementCenterMapper procurementCenterMapper;

    @BeforeEach
    public void setUp() {
        procurementCenterMapper = new ProcurementCenterMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(procurementCenterMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(procurementCenterMapper.fromId(null)).isNull();
    }
}
