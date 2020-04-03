package com.vivek.myapp.web.rest;

import com.vivek.myapp.HaryanaAgroApp;
import com.vivek.myapp.domain.ProcurementCenter;
import com.vivek.myapp.repository.ProcurementCenterRepository;
import com.vivek.myapp.repository.search.ProcurementCenterSearchRepository;
import com.vivek.myapp.service.ProcurementCenterService;
import com.vivek.myapp.service.dto.ProcurementCenterDTO;
import com.vivek.myapp.service.mapper.ProcurementCenterMapper;
import com.vivek.myapp.web.rest.errors.ExceptionTranslator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.Collections;
import java.util.List;

import static com.vivek.myapp.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.vivek.myapp.domain.enumeration.CenterType;
/**
 * Integration tests for the {@link ProcurementCenterResource} REST controller.
 */
@SpringBootTest(classes = HaryanaAgroApp.class)
public class ProcurementCenterResourceIT {

    private static final String DEFAULT_STATE = "AAAAAAAAAA";
    private static final String UPDATED_STATE = "BBBBBBBBBB";

    private static final String DEFAULT_DISTRICT = "AAAAAAAAAA";
    private static final String UPDATED_DISTRICT = "BBBBBBBBBB";

    private static final String DEFAULT_TEHSIL = "AAAAAAAAAA";
    private static final String UPDATED_TEHSIL = "BBBBBBBBBB";

    private static final String DEFAULT_VILLAGE = "AAAAAAAAAA";
    private static final String UPDATED_VILLAGE = "BBBBBBBBBB";

    private static final CenterType DEFAULT_CENTER_TYPE = CenterType.Godown;
    private static final CenterType UPDATED_CENTER_TYPE = CenterType.ColdStorage;

    private static final String DEFAULT_CENTER_IDENTIFICATION_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_CENTER_IDENTIFICATION_NUMBER = "BBBBBBBBBB";

    @Autowired
    private ProcurementCenterRepository procurementCenterRepository;

    @Autowired
    private ProcurementCenterMapper procurementCenterMapper;

    @Autowired
    private ProcurementCenterService procurementCenterService;

    /**
     * This repository is mocked in the com.vivek.myapp.repository.search test package.
     *
     * @see com.vivek.myapp.repository.search.ProcurementCenterSearchRepositoryMockConfiguration
     */
    @Autowired
    private ProcurementCenterSearchRepository mockProcurementCenterSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restProcurementCenterMockMvc;

    private ProcurementCenter procurementCenter;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ProcurementCenterResource procurementCenterResource = new ProcurementCenterResource(procurementCenterService);
        this.restProcurementCenterMockMvc = MockMvcBuilders.standaloneSetup(procurementCenterResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProcurementCenter createEntity(EntityManager em) {
        ProcurementCenter procurementCenter = new ProcurementCenter()
            .state(DEFAULT_STATE)
            .district(DEFAULT_DISTRICT)
            .tehsil(DEFAULT_TEHSIL)
            .village(DEFAULT_VILLAGE)
            .centerType(DEFAULT_CENTER_TYPE)
            .centerIdentificationNumber(DEFAULT_CENTER_IDENTIFICATION_NUMBER);
        return procurementCenter;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProcurementCenter createUpdatedEntity(EntityManager em) {
        ProcurementCenter procurementCenter = new ProcurementCenter()
            .state(UPDATED_STATE)
            .district(UPDATED_DISTRICT)
            .tehsil(UPDATED_TEHSIL)
            .village(UPDATED_VILLAGE)
            .centerType(UPDATED_CENTER_TYPE)
            .centerIdentificationNumber(UPDATED_CENTER_IDENTIFICATION_NUMBER);
        return procurementCenter;
    }

    @BeforeEach
    public void initTest() {
        procurementCenter = createEntity(em);
    }

    @Test
    @Transactional
    public void createProcurementCenter() throws Exception {
        int databaseSizeBeforeCreate = procurementCenterRepository.findAll().size();

        // Create the ProcurementCenter
        ProcurementCenterDTO procurementCenterDTO = procurementCenterMapper.toDto(procurementCenter);
        restProcurementCenterMockMvc.perform(post("/api/procurement-centers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(procurementCenterDTO)))
            .andExpect(status().isCreated());

        // Validate the ProcurementCenter in the database
        List<ProcurementCenter> procurementCenterList = procurementCenterRepository.findAll();
        assertThat(procurementCenterList).hasSize(databaseSizeBeforeCreate + 1);
        ProcurementCenter testProcurementCenter = procurementCenterList.get(procurementCenterList.size() - 1);
        assertThat(testProcurementCenter.getState()).isEqualTo(DEFAULT_STATE);
        assertThat(testProcurementCenter.getDistrict()).isEqualTo(DEFAULT_DISTRICT);
        assertThat(testProcurementCenter.getTehsil()).isEqualTo(DEFAULT_TEHSIL);
        assertThat(testProcurementCenter.getVillage()).isEqualTo(DEFAULT_VILLAGE);
        assertThat(testProcurementCenter.getCenterType()).isEqualTo(DEFAULT_CENTER_TYPE);
        assertThat(testProcurementCenter.getCenterIdentificationNumber()).isEqualTo(DEFAULT_CENTER_IDENTIFICATION_NUMBER);

        // Validate the ProcurementCenter in Elasticsearch
        verify(mockProcurementCenterSearchRepository, times(1)).save(testProcurementCenter);
    }

    @Test
    @Transactional
    public void createProcurementCenterWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = procurementCenterRepository.findAll().size();

        // Create the ProcurementCenter with an existing ID
        procurementCenter.setId(1L);
        ProcurementCenterDTO procurementCenterDTO = procurementCenterMapper.toDto(procurementCenter);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProcurementCenterMockMvc.perform(post("/api/procurement-centers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(procurementCenterDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ProcurementCenter in the database
        List<ProcurementCenter> procurementCenterList = procurementCenterRepository.findAll();
        assertThat(procurementCenterList).hasSize(databaseSizeBeforeCreate);

        // Validate the ProcurementCenter in Elasticsearch
        verify(mockProcurementCenterSearchRepository, times(0)).save(procurementCenter);
    }


    @Test
    @Transactional
    public void getAllProcurementCenters() throws Exception {
        // Initialize the database
        procurementCenterRepository.saveAndFlush(procurementCenter);

        // Get all the procurementCenterList
        restProcurementCenterMockMvc.perform(get("/api/procurement-centers?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(procurementCenter.getId().intValue())))
            .andExpect(jsonPath("$.[*].state").value(hasItem(DEFAULT_STATE)))
            .andExpect(jsonPath("$.[*].district").value(hasItem(DEFAULT_DISTRICT)))
            .andExpect(jsonPath("$.[*].tehsil").value(hasItem(DEFAULT_TEHSIL)))
            .andExpect(jsonPath("$.[*].village").value(hasItem(DEFAULT_VILLAGE)))
            .andExpect(jsonPath("$.[*].centerType").value(hasItem(DEFAULT_CENTER_TYPE.toString())))
            .andExpect(jsonPath("$.[*].centerIdentificationNumber").value(hasItem(DEFAULT_CENTER_IDENTIFICATION_NUMBER)));
    }
    
    @Test
    @Transactional
    public void getProcurementCenter() throws Exception {
        // Initialize the database
        procurementCenterRepository.saveAndFlush(procurementCenter);

        // Get the procurementCenter
        restProcurementCenterMockMvc.perform(get("/api/procurement-centers/{id}", procurementCenter.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(procurementCenter.getId().intValue()))
            .andExpect(jsonPath("$.state").value(DEFAULT_STATE))
            .andExpect(jsonPath("$.district").value(DEFAULT_DISTRICT))
            .andExpect(jsonPath("$.tehsil").value(DEFAULT_TEHSIL))
            .andExpect(jsonPath("$.village").value(DEFAULT_VILLAGE))
            .andExpect(jsonPath("$.centerType").value(DEFAULT_CENTER_TYPE.toString()))
            .andExpect(jsonPath("$.centerIdentificationNumber").value(DEFAULT_CENTER_IDENTIFICATION_NUMBER));
    }

    @Test
    @Transactional
    public void getNonExistingProcurementCenter() throws Exception {
        // Get the procurementCenter
        restProcurementCenterMockMvc.perform(get("/api/procurement-centers/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateProcurementCenter() throws Exception {
        // Initialize the database
        procurementCenterRepository.saveAndFlush(procurementCenter);

        int databaseSizeBeforeUpdate = procurementCenterRepository.findAll().size();

        // Update the procurementCenter
        ProcurementCenter updatedProcurementCenter = procurementCenterRepository.findById(procurementCenter.getId()).get();
        // Disconnect from session so that the updates on updatedProcurementCenter are not directly saved in db
        em.detach(updatedProcurementCenter);
        updatedProcurementCenter
            .state(UPDATED_STATE)
            .district(UPDATED_DISTRICT)
            .tehsil(UPDATED_TEHSIL)
            .village(UPDATED_VILLAGE)
            .centerType(UPDATED_CENTER_TYPE)
            .centerIdentificationNumber(UPDATED_CENTER_IDENTIFICATION_NUMBER);
        ProcurementCenterDTO procurementCenterDTO = procurementCenterMapper.toDto(updatedProcurementCenter);

        restProcurementCenterMockMvc.perform(put("/api/procurement-centers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(procurementCenterDTO)))
            .andExpect(status().isOk());

        // Validate the ProcurementCenter in the database
        List<ProcurementCenter> procurementCenterList = procurementCenterRepository.findAll();
        assertThat(procurementCenterList).hasSize(databaseSizeBeforeUpdate);
        ProcurementCenter testProcurementCenter = procurementCenterList.get(procurementCenterList.size() - 1);
        assertThat(testProcurementCenter.getState()).isEqualTo(UPDATED_STATE);
        assertThat(testProcurementCenter.getDistrict()).isEqualTo(UPDATED_DISTRICT);
        assertThat(testProcurementCenter.getTehsil()).isEqualTo(UPDATED_TEHSIL);
        assertThat(testProcurementCenter.getVillage()).isEqualTo(UPDATED_VILLAGE);
        assertThat(testProcurementCenter.getCenterType()).isEqualTo(UPDATED_CENTER_TYPE);
        assertThat(testProcurementCenter.getCenterIdentificationNumber()).isEqualTo(UPDATED_CENTER_IDENTIFICATION_NUMBER);

        // Validate the ProcurementCenter in Elasticsearch
        verify(mockProcurementCenterSearchRepository, times(1)).save(testProcurementCenter);
    }

    @Test
    @Transactional
    public void updateNonExistingProcurementCenter() throws Exception {
        int databaseSizeBeforeUpdate = procurementCenterRepository.findAll().size();

        // Create the ProcurementCenter
        ProcurementCenterDTO procurementCenterDTO = procurementCenterMapper.toDto(procurementCenter);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProcurementCenterMockMvc.perform(put("/api/procurement-centers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(procurementCenterDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ProcurementCenter in the database
        List<ProcurementCenter> procurementCenterList = procurementCenterRepository.findAll();
        assertThat(procurementCenterList).hasSize(databaseSizeBeforeUpdate);

        // Validate the ProcurementCenter in Elasticsearch
        verify(mockProcurementCenterSearchRepository, times(0)).save(procurementCenter);
    }

    @Test
    @Transactional
    public void deleteProcurementCenter() throws Exception {
        // Initialize the database
        procurementCenterRepository.saveAndFlush(procurementCenter);

        int databaseSizeBeforeDelete = procurementCenterRepository.findAll().size();

        // Delete the procurementCenter
        restProcurementCenterMockMvc.perform(delete("/api/procurement-centers/{id}", procurementCenter.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ProcurementCenter> procurementCenterList = procurementCenterRepository.findAll();
        assertThat(procurementCenterList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the ProcurementCenter in Elasticsearch
        verify(mockProcurementCenterSearchRepository, times(1)).deleteById(procurementCenter.getId());
    }

    @Test
    @Transactional
    public void searchProcurementCenter() throws Exception {
        // Initialize the database
        procurementCenterRepository.saveAndFlush(procurementCenter);
        when(mockProcurementCenterSearchRepository.search(queryStringQuery("id:" + procurementCenter.getId()), PageRequest.of(0, 20)))
            .thenReturn(new PageImpl<>(Collections.singletonList(procurementCenter), PageRequest.of(0, 1), 1));
        // Search the procurementCenter
        restProcurementCenterMockMvc.perform(get("/api/_search/procurement-centers?query=id:" + procurementCenter.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(procurementCenter.getId().intValue())))
            .andExpect(jsonPath("$.[*].state").value(hasItem(DEFAULT_STATE)))
            .andExpect(jsonPath("$.[*].district").value(hasItem(DEFAULT_DISTRICT)))
            .andExpect(jsonPath("$.[*].tehsil").value(hasItem(DEFAULT_TEHSIL)))
            .andExpect(jsonPath("$.[*].village").value(hasItem(DEFAULT_VILLAGE)))
            .andExpect(jsonPath("$.[*].centerType").value(hasItem(DEFAULT_CENTER_TYPE.toString())))
            .andExpect(jsonPath("$.[*].centerIdentificationNumber").value(hasItem(DEFAULT_CENTER_IDENTIFICATION_NUMBER)));
    }
}
