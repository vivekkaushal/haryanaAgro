package com.vivek.myapp.web.rest;

import com.vivek.myapp.service.FarmerService;
import com.vivek.myapp.web.rest.errors.BadRequestAlertException;
import com.vivek.myapp.service.dto.FarmerDTO;

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
 * REST controller for managing {@link com.vivek.myapp.domain.Farmer}.
 */
@RestController
@RequestMapping("/api")
public class FarmerResource {

    private final Logger log = LoggerFactory.getLogger(FarmerResource.class);

    private static final String ENTITY_NAME = "farmer";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FarmerService farmerService;

    public FarmerResource(FarmerService farmerService) {
        this.farmerService = farmerService;
    }

    /**
     * {@code POST  /farmers} : Create a new farmer.
     *
     * @param farmerDTO the farmerDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new farmerDTO, or with status {@code 400 (Bad Request)} if the farmer has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/farmers")
    public ResponseEntity<FarmerDTO> createFarmer(@RequestBody FarmerDTO farmerDTO) throws URISyntaxException {
        log.debug("REST request to save Farmer : {}", farmerDTO);
        if (farmerDTO.getId() != null) {
            throw new BadRequestAlertException("A new farmer cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FarmerDTO result = farmerService.save(farmerDTO);
        return ResponseEntity.created(new URI("/api/farmers/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /farmers} : Updates an existing farmer.
     *
     * @param farmerDTO the farmerDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated farmerDTO,
     * or with status {@code 400 (Bad Request)} if the farmerDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the farmerDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/farmers")
    public ResponseEntity<FarmerDTO> updateFarmer(@RequestBody FarmerDTO farmerDTO) throws URISyntaxException {
        log.debug("REST request to update Farmer : {}", farmerDTO);
        if (farmerDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        FarmerDTO result = farmerService.save(farmerDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, farmerDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /farmers} : get all the farmers.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of farmers in body.
     */
    @GetMapping("/farmers")
    public ResponseEntity<List<FarmerDTO>> getAllFarmers(Pageable pageable) {
        log.debug("REST request to get a page of Farmers");
        Page<FarmerDTO> page = farmerService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /farmers/:id} : get the "id" farmer.
     *
     * @param id the id of the farmerDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the farmerDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/farmers/{id}")
    public ResponseEntity<FarmerDTO> getFarmer(@PathVariable Long id) {
        log.debug("REST request to get Farmer : {}", id);
        Optional<FarmerDTO> farmerDTO = farmerService.findOne(id);
        return ResponseUtil.wrapOrNotFound(farmerDTO);
    }

    /**
     * {@code DELETE  /farmers/:id} : delete the "id" farmer.
     *
     * @param id the id of the farmerDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/farmers/{id}")
    public ResponseEntity<Void> deleteFarmer(@PathVariable Long id) {
        log.debug("REST request to delete Farmer : {}", id);
        farmerService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code SEARCH  /_search/farmers?query=:query} : search for the farmer corresponding
     * to the query.
     *
     * @param query the query of the farmer search.
     * @param pageable the pagination information.
     * @return the result of the search.
     */
    @GetMapping("/_search/farmers")
    public ResponseEntity<List<FarmerDTO>> searchFarmers(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of Farmers for query {}", query);
        Page<FarmerDTO> page = farmerService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }
}
