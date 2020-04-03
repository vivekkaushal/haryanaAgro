package com.vivek.myapp.web.rest;

import com.vivek.myapp.service.CropProcurementPassService;
import com.vivek.myapp.web.rest.errors.BadRequestAlertException;
import com.vivek.myapp.service.dto.CropProcurementPassDTO;

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
 * REST controller for managing {@link com.vivek.myapp.domain.CropProcurementPass}.
 */
@RestController
@RequestMapping("/api")
public class CropProcurementPassResource {

    private final Logger log = LoggerFactory.getLogger(CropProcurementPassResource.class);

    private static final String ENTITY_NAME = "cropProcurementPass";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CropProcurementPassService cropProcurementPassService;

    public CropProcurementPassResource(CropProcurementPassService cropProcurementPassService) {
        this.cropProcurementPassService = cropProcurementPassService;
    }

    /**
     * {@code POST  /crop-procurement-passes} : Create a new cropProcurementPass.
     *
     * @param cropProcurementPassDTO the cropProcurementPassDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new cropProcurementPassDTO, or with status {@code 400 (Bad Request)} if the cropProcurementPass has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/crop-procurement-passes")
    public ResponseEntity<CropProcurementPassDTO> createCropProcurementPass(@RequestBody CropProcurementPassDTO cropProcurementPassDTO) throws URISyntaxException {
        log.debug("REST request to save CropProcurementPass : {}", cropProcurementPassDTO);
        if (cropProcurementPassDTO.getId() != null) {
            throw new BadRequestAlertException("A new cropProcurementPass cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CropProcurementPassDTO result = cropProcurementPassService.save(cropProcurementPassDTO);
        return ResponseEntity.created(new URI("/api/crop-procurement-passes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /crop-procurement-passes} : Updates an existing cropProcurementPass.
     *
     * @param cropProcurementPassDTO the cropProcurementPassDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated cropProcurementPassDTO,
     * or with status {@code 400 (Bad Request)} if the cropProcurementPassDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the cropProcurementPassDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/crop-procurement-passes")
    public ResponseEntity<CropProcurementPassDTO> updateCropProcurementPass(@RequestBody CropProcurementPassDTO cropProcurementPassDTO) throws URISyntaxException {
        log.debug("REST request to update CropProcurementPass : {}", cropProcurementPassDTO);
        if (cropProcurementPassDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CropProcurementPassDTO result = cropProcurementPassService.save(cropProcurementPassDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, cropProcurementPassDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /crop-procurement-passes} : get all the cropProcurementPasses.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of cropProcurementPasses in body.
     */
    @GetMapping("/crop-procurement-passes")
    public ResponseEntity<List<CropProcurementPassDTO>> getAllCropProcurementPasses(Pageable pageable) {
        log.debug("REST request to get a page of CropProcurementPasses");
        Page<CropProcurementPassDTO> page = cropProcurementPassService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /crop-procurement-passes/:id} : get the "id" cropProcurementPass.
     *
     * @param id the id of the cropProcurementPassDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the cropProcurementPassDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/crop-procurement-passes/{id}")
    public ResponseEntity<CropProcurementPassDTO> getCropProcurementPass(@PathVariable Long id) {
        log.debug("REST request to get CropProcurementPass : {}", id);
        Optional<CropProcurementPassDTO> cropProcurementPassDTO = cropProcurementPassService.findOne(id);
        return ResponseUtil.wrapOrNotFound(cropProcurementPassDTO);
    }

    /**
     * {@code DELETE  /crop-procurement-passes/:id} : delete the "id" cropProcurementPass.
     *
     * @param id the id of the cropProcurementPassDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/crop-procurement-passes/{id}")
    public ResponseEntity<Void> deleteCropProcurementPass(@PathVariable Long id) {
        log.debug("REST request to delete CropProcurementPass : {}", id);
        cropProcurementPassService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code SEARCH  /_search/crop-procurement-passes?query=:query} : search for the cropProcurementPass corresponding
     * to the query.
     *
     * @param query the query of the cropProcurementPass search.
     * @param pageable the pagination information.
     * @return the result of the search.
     */
    @GetMapping("/_search/crop-procurement-passes")
    public ResponseEntity<List<CropProcurementPassDTO>> searchCropProcurementPasses(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of CropProcurementPasses for query {}", query);
        Page<CropProcurementPassDTO> page = cropProcurementPassService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }
}
