package com.vivek.myapp.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.vivek.myapp.web.rest.TestUtil;

public class CropProcurementPassDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CropProcurementPassDTO.class);
        CropProcurementPassDTO cropProcurementPassDTO1 = new CropProcurementPassDTO();
        cropProcurementPassDTO1.setId(1L);
        CropProcurementPassDTO cropProcurementPassDTO2 = new CropProcurementPassDTO();
        assertThat(cropProcurementPassDTO1).isNotEqualTo(cropProcurementPassDTO2);
        cropProcurementPassDTO2.setId(cropProcurementPassDTO1.getId());
        assertThat(cropProcurementPassDTO1).isEqualTo(cropProcurementPassDTO2);
        cropProcurementPassDTO2.setId(2L);
        assertThat(cropProcurementPassDTO1).isNotEqualTo(cropProcurementPassDTO2);
        cropProcurementPassDTO1.setId(null);
        assertThat(cropProcurementPassDTO1).isNotEqualTo(cropProcurementPassDTO2);
    }
}
