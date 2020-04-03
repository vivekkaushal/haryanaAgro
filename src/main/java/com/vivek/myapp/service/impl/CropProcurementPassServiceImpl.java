package com.vivek.myapp.service.impl;

import com.vivek.myapp.service.CropProcurementPassService;
import com.vivek.myapp.domain.CropProcurementPass;
import com.vivek.myapp.repository.CropProcurementPassRepository;
import com.vivek.myapp.repository.search.CropProcurementPassSearchRepository;
import com.vivek.myapp.service.dto.CropProcurementPassDTO;
import com.vivek.myapp.service.mapper.CropProcurementPassMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing {@link CropProcurementPass}.
 */
@Service
@Transactional
public class CropProcurementPassServiceImpl implements CropProcurementPassService {

    private final Logger log = LoggerFactory.getLogger(CropProcurementPassServiceImpl.class);

    private final CropProcurementPassRepository cropProcurementPassRepository;

    private final CropProcurementPassMapper cropProcurementPassMapper;

    private final CropProcurementPassSearchRepository cropProcurementPassSearchRepository;

    public CropProcurementPassServiceImpl(CropProcurementPassRepository cropProcurementPassRepository, CropProcurementPassMapper cropProcurementPassMapper, CropProcurementPassSearchRepository cropProcurementPassSearchRepository) {
        this.cropProcurementPassRepository = cropProcurementPassRepository;
        this.cropProcurementPassMapper = cropProcurementPassMapper;
        this.cropProcurementPassSearchRepository = cropProcurementPassSearchRepository;
    }

    /**
     * Save a cropProcurementPass.
     *
     * @param cropProcurementPassDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public CropProcurementPassDTO save(CropProcurementPassDTO cropProcurementPassDTO) {
        log.debug("Request to save CropProcurementPass : {}", cropProcurementPassDTO);
        CropProcurementPass cropProcurementPass = cropProcurementPassMapper.toEntity(cropProcurementPassDTO);
        cropProcurementPass = cropProcurementPassRepository.save(cropProcurementPass);
        CropProcurementPassDTO result = cropProcurementPassMapper.toDto(cropProcurementPass);
        cropProcurementPassSearchRepository.save(cropProcurementPass);
        return result;
    }

    /**
     * Get all the cropProcurementPasses.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CropProcurementPassDTO> findAll(Pageable pageable) {
        log.debug("Request to get all CropProcurementPasses");
        return cropProcurementPassRepository.findAll(pageable)
            .map(cropProcurementPassMapper::toDto);
    }


    /**
     * Get one cropProcurementPass by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<CropProcurementPassDTO> findOne(Long id) {
        log.debug("Request to get CropProcurementPass : {}", id);
        return cropProcurementPassRepository.findById(id)
            .map(cropProcurementPassMapper::toDto);
    }

    /**
     * Delete the cropProcurementPass by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete CropProcurementPass : {}", id);
        cropProcurementPassRepository.deleteById(id);
        cropProcurementPassSearchRepository.deleteById(id);
    }

    /**
     * Search for the cropProcurementPass corresponding to the query.
     *
     * @param query the query of the search.
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CropProcurementPassDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of CropProcurementPasses for query {}", query);
        return cropProcurementPassSearchRepository.search(queryStringQuery(query), pageable)
            .map(cropProcurementPassMapper::toDto);
    }
}
