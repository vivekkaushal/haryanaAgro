package com.vivek.myapp.service;

import com.vivek.myapp.service.dto.ProcurementCenterDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.vivek.myapp.domain.ProcurementCenter}.
 */
public interface ProcurementCenterService {

    /**
     * Save a procurementCenter.
     *
     * @param procurementCenterDTO the entity to save.
     * @return the persisted entity.
     */
    ProcurementCenterDTO save(ProcurementCenterDTO procurementCenterDTO);

    /**
     * Get all the procurementCenters.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ProcurementCenterDTO> findAll(Pageable pageable);


    /**
     * Get the "id" procurementCenter.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ProcurementCenterDTO> findOne(Long id);

    /**
     * Delete the "id" procurementCenter.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    /**
     * Search for the procurementCenter corresponding to the query.
     *
     * @param query the query of the search.
     * 
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ProcurementCenterDTO> search(String query, Pageable pageable);
}
