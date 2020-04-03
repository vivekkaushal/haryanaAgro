package com.vivek.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.vivek.myapp.web.rest.TestUtil;

public class CropProcurementPassTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CropProcurementPass.class);
        CropProcurementPass cropProcurementPass1 = new CropProcurementPass();
        cropProcurementPass1.setId(1L);
        CropProcurementPass cropProcurementPass2 = new CropProcurementPass();
        cropProcurementPass2.setId(cropProcurementPass1.getId());
        assertThat(cropProcurementPass1).isEqualTo(cropProcurementPass2);
        cropProcurementPass2.setId(2L);
        assertThat(cropProcurementPass1).isNotEqualTo(cropProcurementPass2);
        cropProcurementPass1.setId(null);
        assertThat(cropProcurementPass1).isNotEqualTo(cropProcurementPass2);
    }
}
