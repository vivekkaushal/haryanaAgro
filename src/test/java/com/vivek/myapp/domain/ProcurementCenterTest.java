package com.vivek.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.vivek.myapp.web.rest.TestUtil;

public class ProcurementCenterTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProcurementCenter.class);
        ProcurementCenter procurementCenter1 = new ProcurementCenter();
        procurementCenter1.setId(1L);
        ProcurementCenter procurementCenter2 = new ProcurementCenter();
        procurementCenter2.setId(procurementCenter1.getId());
        assertThat(procurementCenter1).isEqualTo(procurementCenter2);
        procurementCenter2.setId(2L);
        assertThat(procurementCenter1).isNotEqualTo(procurementCenter2);
        procurementCenter1.setId(null);
        assertThat(procurementCenter1).isNotEqualTo(procurementCenter2);
    }
}
