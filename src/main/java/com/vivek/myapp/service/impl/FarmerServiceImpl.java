package com.vivek.myapp.service.impl;

import com.vivek.myapp.service.FarmerService;
import com.vivek.myapp.domain.Farmer;
import com.vivek.myapp.repository.FarmerRepository;
import com.vivek.myapp.repository.search.FarmerSearchRepository;
import com.vivek.myapp.service.dto.FarmerDTO;
import com.vivek.myapp.service.mapper.FarmerMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing {@link Farmer}.
 */
@Service
@Transactional
public class FarmerServiceImpl implements FarmerService {

    private final Logger log = LoggerFactory.getLogger(FarmerServiceImpl.class);

    private final FarmerRepository farmerRepository;

    private final FarmerMapper farmerMapper;

    private final FarmerSearchRepository farmerSearchRepository;

    public FarmerServiceImpl(FarmerRepository farmerRepository, FarmerMapper farmerMapper, FarmerSearchRepository farmerSearchRepository) {
        this.farmerRepository = farmerRepository;
        this.farmerMapper = farmerMapper;
        this.farmerSearchRepository = farmerSearchRepository;
    }

    /**
     * Save a farmer.
     *
     * @param farmerDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public FarmerDTO save(FarmerDTO farmerDTO) {
        log.debug("Request to save Farmer : {}", farmerDTO);
        Farmer farmer = farmerMapper.toEntity(farmerDTO);
        farmer = farmerRepository.save(farmer);
        FarmerDTO result = farmerMapper.toDto(farmer);
        farmerSearchRepository.save(farmer);
        return result;
    }

    /**
     * Get all the farmers.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<FarmerDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Farmers");
        return farmerRepository.findAll(pageable)
            .map(farmerMapper::toDto);
    }


    /**
     * Get one farmer by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<FarmerDTO> findOne(Long id) {
        log.debug("Request to get Farmer : {}", id);
        return farmerRepository.findById(id)
            .map(farmerMapper::toDto);
    }

    /**
     * Delete the farmer by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Farmer : {}", id);
        farmerRepository.deleteById(id);
        farmerSearchRepository.deleteById(id);
    }

    /**
     * Search for the farmer corresponding to the query.
     *
     * @param query the query of the search.
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<FarmerDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of Farmers for query {}", query);
        return farmerSearchRepository.search(queryStringQuery(query), pageable)
            .map(farmerMapper::toDto);
    }
}
