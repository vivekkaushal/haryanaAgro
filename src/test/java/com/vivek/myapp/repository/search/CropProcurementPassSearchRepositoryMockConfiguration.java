package com.vivek.myapp.repository.search;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;

/**
 * Configure a Mock version of {@link CropProcurementPassSearchRepository} to test the
 * application without starting Elasticsearch.
 */
@Configuration
public class CropProcurementPassSearchRepositoryMockConfiguration {

    @MockBean
    private CropProcurementPassSearchRepository mockCropProcurementPassSearchRepository;

}
