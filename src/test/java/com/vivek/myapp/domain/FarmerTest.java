package com.vivek.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.vivek.myapp.web.rest.TestUtil;

public class FarmerTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Farmer.class);
        Farmer farmer1 = new Farmer();
        farmer1.setId(1L);
        Farmer farmer2 = new Farmer();
        farmer2.setId(farmer1.getId());
        assertThat(farmer1).isEqualTo(farmer2);
        farmer2.setId(2L);
        assertThat(farmer1).isNotEqualTo(farmer2);
        farmer1.setId(null);
        assertThat(farmer1).isNotEqualTo(farmer2);
    }
}
