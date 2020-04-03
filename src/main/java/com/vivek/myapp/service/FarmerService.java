package com.vivek.myapp.service;

import com.vivek.myapp.service.dto.FarmerDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.vivek.myapp.domain.Farmer}.
 */
public interface FarmerService {

    /**
     * Save a farmer.
     *
     * @param farmerDTO the entity to save.
     * @return the persisted entity.
     */
    FarmerDTO save(FarmerDTO farmerDTO);

    /**
     * Get all the farmers.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<FarmerDTO> findAll(Pageable pageable);


    /**
     * Get the "id" farmer.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<FarmerDTO> findOne(Long id);

    /**
     * Delete the "id" farmer.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    /**
     * Search for the farmer corresponding to the query.
     *
     * @param query the query of the search.
     * 
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<FarmerDTO> search(String query, Pageable pageable);
}
