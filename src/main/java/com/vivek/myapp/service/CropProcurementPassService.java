package com.vivek.myapp.service;

import com.vivek.myapp.service.dto.CropProcurementPassDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.vivek.myapp.domain.CropProcurementPass}.
 */
public interface CropProcurementPassService {

    /**
     * Save a cropProcurementPass.
     *
     * @param cropProcurementPassDTO the entity to save.
     * @return the persisted entity.
     */
    CropProcurementPassDTO save(CropProcurementPassDTO cropProcurementPassDTO);

    /**
     * Get all the cropProcurementPasses.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<CropProcurementPassDTO> findAll(Pageable pageable);


    /**
     * Get the "id" cropProcurementPass.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<CropProcurementPassDTO> findOne(Long id);

    /**
     * Delete the "id" cropProcurementPass.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    /**
     * Search for the cropProcurementPass corresponding to the query.
     *
     * @param query the query of the search.
     * 
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<CropProcurementPassDTO> search(String query, Pageable pageable);
}
