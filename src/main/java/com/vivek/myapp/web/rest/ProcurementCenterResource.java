package com.vivek.myapp.web.rest;

import com.vivek.myapp.service.ProcurementCenterService;
import com.vivek.myapp.web.rest.errors.BadRequestAlertException;
import com.vivek.myapp.service.dto.ProcurementCenterDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * REST controller for managing {@link com.vivek.myapp.domain.ProcurementCenter}.
 */
@RestController
@RequestMapping("/api")
public class ProcurementCenterResource {

    private final Logger log = LoggerFactory.getLogger(ProcurementCenterResource.class);

    private static final String ENTITY_NAME = "procurementCenter";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ProcurementCenterService procurementCenterService;

    public ProcurementCenterResource(ProcurementCenterService procurementCenterService) {
        this.procurementCenterService = procurementCenterService;
    }

    /**
     * {@code POST  /procurement-centers} : Create a new procurementCenter.
     *
     * @param procurementCenterDTO the procurementCenterDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new procurementCenterDTO, or with status {@code 400 (Bad Request)} if the procurementCenter has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/procurement-centers")
    public ResponseEntity<ProcurementCenterDTO> createProcurementCenter(@RequestBody ProcurementCenterDTO procurementCenterDTO) throws URISyntaxException {
        log.debug("REST request to save ProcurementCenter : {}", procurementCenterDTO);
        if (procurementCenterDTO.getId() != null) {
            throw new BadRequestAlertException("A new procurementCenter cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ProcurementCenterDTO result = procurementCenterService.save(procurementCenterDTO);
        return ResponseEntity.created(new URI("/api/procurement-centers/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /procurement-centers} : Updates an existing procurementCenter.
     *
     * @param procurementCenterDTO the procurementCenterDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated procurementCenterDTO,
     * or with status {@code 400 (Bad Request)} if the procurementCenterDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the procurementCenterDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/procurement-centers")
    public ResponseEntity<ProcurementCenterDTO> updateProcurementCenter(@RequestBody ProcurementCenterDTO procurementCenterDTO) throws URISyntaxException {
        log.debug("REST request to update ProcurementCenter : {}", procurementCenterDTO);
        if (procurementCenterDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ProcurementCenterDTO result = procurementCenterService.save(procurementCenterDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, procurementCenterDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /procurement-centers} : get all the procurementCenters.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of procurementCenters in body.
     */
    @GetMapping("/procurement-centers")
    public ResponseEntity<List<ProcurementCenterDTO>> getAllProcurementCenters(Pageable pageable) {
        log.debug("REST request to get a page of ProcurementCenters");
        Page<ProcurementCenterDTO> page = procurementCenterService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /procurement-centers/:id} : get the "id" procurementCenter.
     *
     * @param id the id of the procurementCenterDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the procurementCenterDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/procurement-centers/{id}")
    public ResponseEntity<ProcurementCenterDTO> getProcurementCenter(@PathVariable Long id) {
        log.debug("REST request to get ProcurementCenter : {}", id);
        Optional<ProcurementCenterDTO> procurementCenterDTO = procurementCenterService.findOne(id);
        return ResponseUtil.wrapOrNotFound(procurementCenterDTO);
    }

    /**
     * {@code DELETE  /procurement-centers/:id} : delete the "id" procurementCenter.
     *
     * @param id the id of the procurementCenterDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/procurement-centers/{id}")
    public ResponseEntity<Void> deleteProcurementCenter(@PathVariable Long id) {
        log.debug("REST request to delete ProcurementCenter : {}", id);
        procurementCenterService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code SEARCH  /_search/procurement-centers?query=:query} : search for the procurementCenter corresponding
     * to the query.
     *
     * @param query the query of the procurementCenter search.
     * @param pageable the pagination information.
     * @return the result of the search.
     */
    @GetMapping("/_search/procurement-centers")
    public ResponseEntity<List<ProcurementCenterDTO>> searchProcurementCenters(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of ProcurementCenters for query {}", query);
        Page<ProcurementCenterDTO> page = procurementCenterService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }
}
