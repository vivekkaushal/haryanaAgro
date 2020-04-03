package com.vivek.myapp.repository.search;
import com.vivek.myapp.domain.Farmer;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link Farmer} entity.
 */
public interface FarmerSearchRepository extends ElasticsearchRepository<Farmer, Long> {
}
