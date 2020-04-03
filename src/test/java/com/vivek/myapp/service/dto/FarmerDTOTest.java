package com.vivek.myapp.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.vivek.myapp.web.rest.TestUtil;

public class FarmerDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(FarmerDTO.class);
        FarmerDTO farmerDTO1 = new FarmerDTO();
        farmerDTO1.setId(1L);
        FarmerDTO farmerDTO2 = new FarmerDTO();
        assertThat(farmerDTO1).isNotEqualTo(farmerDTO2);
        farmerDTO2.setId(farmerDTO1.getId());
        assertThat(farmerDTO1).isEqualTo(farmerDTO2);
        farmerDTO2.setId(2L);
        assertThat(farmerDTO1).isNotEqualTo(farmerDTO2);
        farmerDTO1.setId(null);
        assertThat(farmerDTO1).isNotEqualTo(farmerDTO2);
    }
}
