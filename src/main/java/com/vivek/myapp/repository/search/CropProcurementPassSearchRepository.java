package com.vivek.myapp.repository.search;
import com.vivek.myapp.domain.CropProcurementPass;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link CropProcurementPass} entity.
 */
public interface CropProcurementPassSearchRepository extends ElasticsearchRepository<CropProcurementPass, Long> {
}
