package com.vivek.myapp.service.impl;

import com.vivek.myapp.service.ProcurementCenterService;
import com.vivek.myapp.domain.ProcurementCenter;
import com.vivek.myapp.repository.ProcurementCenterRepository;
import com.vivek.myapp.repository.search.ProcurementCenterSearchRepository;
import com.vivek.myapp.service.dto.ProcurementCenterDTO;
import com.vivek.myapp.service.mapper.ProcurementCenterMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing {@link ProcurementCenter}.
 */
@Service
@Transactional
public class ProcurementCenterServiceImpl implements ProcurementCenterService {

    private final Logger log = LoggerFactory.getLogger(ProcurementCenterServiceImpl.class);

    private final ProcurementCenterRepository procurementCenterRepository;

    private final ProcurementCenterMapper procurementCenterMapper;

    private final ProcurementCenterSearchRepository procurementCenterSearchRepository;

    public ProcurementCenterServiceImpl(ProcurementCenterRepository procurementCenterRepository, ProcurementCenterMapper procurementCenterMapper, ProcurementCenterSearchRepository procurementCenterSearchRepository) {
        this.procurementCenterRepository = procurementCenterRepository;
        this.procurementCenterMapper = procurementCenterMapper;
        this.procurementCenterSearchRepository = procurementCenterSearchRepository;
    }

    /**
     * Save a procurementCenter.
     *
     * @param procurementCenterDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public ProcurementCenterDTO save(ProcurementCenterDTO procurementCenterDTO) {
        log.debug("Request to save ProcurementCenter : {}", procurementCenterDTO);
        ProcurementCenter procurementCenter = procurementCenterMapper.toEntity(procurementCenterDTO);
        procurementCenter = procurementCenterRepository.save(procurementCenter);
        ProcurementCenterDTO result = procurementCenterMapper.toDto(procurementCenter);
        procurementCenterSearchRepository.save(procurementCenter);
        return result;
    }

    /**
     * Get all the procurementCenters.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ProcurementCenterDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ProcurementCenters");
        return procurementCenterRepository.findAll(pageable)
            .map(procurementCenterMapper::toDto);
    }


    /**
     * Get one procurementCenter by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ProcurementCenterDTO> findOne(Long id) {
        log.debug("Request to get ProcurementCenter : {}", id);
        return procurementCenterRepository.findById(id)
            .map(procurementCenterMapper::toDto);
    }

    /**
     * Delete the procurementCenter by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ProcurementCenter : {}", id);
        procurementCenterRepository.deleteById(id);
        procurementCenterSearchRepository.deleteById(id);
    }

    /**
     * Search for the procurementCenter corresponding to the query.
     *
     * @param query the query of the search.
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ProcurementCenterDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of ProcurementCenters for query {}", query);
        return procurementCenterSearchRepository.search(queryStringQuery(query), pageable)
            .map(procurementCenterMapper::toDto);
    }
}
