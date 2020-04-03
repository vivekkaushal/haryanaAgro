package com.vivek.myapp.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.vivek.myapp.web.rest.TestUtil;

public class ProcurementCenterDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProcurementCenterDTO.class);
        ProcurementCenterDTO procurementCenterDTO1 = new ProcurementCenterDTO();
        procurementCenterDTO1.setId(1L);
        ProcurementCenterDTO procurementCenterDTO2 = new ProcurementCenterDTO();
        assertThat(procurementCenterDTO1).isNotEqualTo(procurementCenterDTO2);
        procurementCenterDTO2.setId(procurementCenterDTO1.getId());
        assertThat(procurementCenterDTO1).isEqualTo(procurementCenterDTO2);
        procurementCenterDTO2.setId(2L);
        assertThat(procurementCenterDTO1).isNotEqualTo(procurementCenterDTO2);
        procurementCenterDTO1.setId(null);
        assertThat(procurementCenterDTO1).isNotEqualTo(procurementCenterDTO2);
    }
}
