package com.vivek.myapp.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class FarmerMapperTest {

    private FarmerMapper farmerMapper;

    @BeforeEach
    public void setUp() {
        farmerMapper = new FarmerMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(farmerMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(farmerMapper.fromId(null)).isNull();
    }
}
