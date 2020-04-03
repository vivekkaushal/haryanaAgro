package com.vivek.myapp.repository.search;
import com.vivek.myapp.domain.ProcurementCenter;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link ProcurementCenter} entity.
 */
public interface ProcurementCenterSearchRepository extends ElasticsearchRepository<ProcurementCenter, Long> {
}
