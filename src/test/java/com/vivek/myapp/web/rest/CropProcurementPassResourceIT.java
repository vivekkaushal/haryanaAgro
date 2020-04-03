package com.vivek.myapp.web.rest;

import com.vivek.myapp.HaryanaAgroApp;
import com.vivek.myapp.domain.CropProcurementPass;
import com.vivek.myapp.repository.CropProcurementPassRepository;
import com.vivek.myapp.repository.search.CropProcurementPassSearchRepository;
import com.vivek.myapp.service.CropProcurementPassService;
import com.vivek.myapp.service.dto.CropProcurementPassDTO;
import com.vivek.myapp.service.mapper.CropProcurementPassMapper;
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
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.List;

import static com.vivek.myapp.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.vivek.myapp.domain.enumeration.CropType;
import com.vivek.myapp.domain.enumeration.ExpectedYieldUnit;
import com.vivek.myapp.domain.enumeration.PassStatus;
/**
 * Integration tests for the {@link CropProcurementPassResource} REST controller.
 */
@SpringBootTest(classes = HaryanaAgroApp.class)
public class CropProcurementPassResourceIT {

    private static final Instant DEFAULT_CROP_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CROP_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final CropType DEFAULT_CROP_TYPE = CropType.Paddy;
    private static final CropType UPDATED_CROP_TYPE = CropType.MaizeOrCorn;

    private static final String DEFAULT_EXPECTED_YEILD = "AAAAAAAAAA";
    private static final String UPDATED_EXPECTED_YEILD = "BBBBBBBBBB";

    private static final ExpectedYieldUnit DEFAULT_EXPECTED_YEILD_UNITS = ExpectedYieldUnit.Bag;
    private static final ExpectedYieldUnit UPDATED_EXPECTED_YEILD_UNITS = ExpectedYieldUnit.Quintal;

    private static final Instant DEFAULT_PROCUREMENT_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_PROCUREMENT_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final PassStatus DEFAULT_PASS_STATUS = PassStatus.Approved;
    private static final PassStatus UPDATED_PASS_STATUS = PassStatus.Pending;

    private static final Instant DEFAULT_CREATED_AT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATED_AT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_MODIFIED_AT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_MODIFIED_AT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private CropProcurementPassRepository cropProcurementPassRepository;

    @Autowired
    private CropProcurementPassMapper cropProcurementPassMapper;

    @Autowired
    private CropProcurementPassService cropProcurementPassService;

    /**
     * This repository is mocked in the com.vivek.myapp.repository.search test package.
     *
     * @see com.vivek.myapp.repository.search.CropProcurementPassSearchRepositoryMockConfiguration
     */
    @Autowired
    private CropProcurementPassSearchRepository mockCropProcurementPassSearchRepository;

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

    private MockMvc restCropProcurementPassMockMvc;

    private CropProcurementPass cropProcurementPass;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CropProcurementPassResource cropProcurementPassResource = new CropProcurementPassResource(cropProcurementPassService);
        this.restCropProcurementPassMockMvc = MockMvcBuilders.standaloneSetup(cropProcurementPassResource)
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
    public static CropProcurementPass createEntity(EntityManager em) {
        CropProcurementPass cropProcurementPass = new CropProcurementPass()
            .cropDate(DEFAULT_CROP_DATE)
            .cropType(DEFAULT_CROP_TYPE)
            .expectedYeild(DEFAULT_EXPECTED_YEILD)
            .expectedYeildUnits(DEFAULT_EXPECTED_YEILD_UNITS)
            .procurementDate(DEFAULT_PROCUREMENT_DATE)
            .passStatus(DEFAULT_PASS_STATUS)
            .createdAt(DEFAULT_CREATED_AT)
            .modifiedAt(DEFAULT_MODIFIED_AT);
        return cropProcurementPass;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CropProcurementPass createUpdatedEntity(EntityManager em) {
        CropProcurementPass cropProcurementPass = new CropProcurementPass()
            .cropDate(UPDATED_CROP_DATE)
            .cropType(UPDATED_CROP_TYPE)
            .expectedYeild(UPDATED_EXPECTED_YEILD)
            .expectedYeildUnits(UPDATED_EXPECTED_YEILD_UNITS)
            .procurementDate(UPDATED_PROCUREMENT_DATE)
            .passStatus(UPDATED_PASS_STATUS)
            .createdAt(UPDATED_CREATED_AT)
            .modifiedAt(UPDATED_MODIFIED_AT);
        return cropProcurementPass;
    }

    @BeforeEach
    public void initTest() {
        cropProcurementPass = createEntity(em);
    }

    @Test
    @Transactional
    public void createCropProcurementPass() throws Exception {
        int databaseSizeBeforeCreate = cropProcurementPassRepository.findAll().size();

        // Create the CropProcurementPass
        CropProcurementPassDTO cropProcurementPassDTO = cropProcurementPassMapper.toDto(cropProcurementPass);
        restCropProcurementPassMockMvc.perform(post("/api/crop-procurement-passes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cropProcurementPassDTO)))
            .andExpect(status().isCreated());

        // Validate the CropProcurementPass in the database
        List<CropProcurementPass> cropProcurementPassList = cropProcurementPassRepository.findAll();
        assertThat(cropProcurementPassList).hasSize(databaseSizeBeforeCreate + 1);
        CropProcurementPass testCropProcurementPass = cropProcurementPassList.get(cropProcurementPassList.size() - 1);
        assertThat(testCropProcurementPass.getCropDate()).isEqualTo(DEFAULT_CROP_DATE);
        assertThat(testCropProcurementPass.getCropType()).isEqualTo(DEFAULT_CROP_TYPE);
        assertThat(testCropProcurementPass.getExpectedYeild()).isEqualTo(DEFAULT_EXPECTED_YEILD);
        assertThat(testCropProcurementPass.getExpectedYeildUnits()).isEqualTo(DEFAULT_EXPECTED_YEILD_UNITS);
        assertThat(testCropProcurementPass.getProcurementDate()).isEqualTo(DEFAULT_PROCUREMENT_DATE);
        assertThat(testCropProcurementPass.getPassStatus()).isEqualTo(DEFAULT_PASS_STATUS);
        assertThat(testCropProcurementPass.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testCropProcurementPass.getModifiedAt()).isEqualTo(DEFAULT_MODIFIED_AT);

        // Validate the CropProcurementPass in Elasticsearch
        verify(mockCropProcurementPassSearchRepository, times(1)).save(testCropProcurementPass);
    }

    @Test
    @Transactional
    public void createCropProcurementPassWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = cropProcurementPassRepository.findAll().size();

        // Create the CropProcurementPass with an existing ID
        cropProcurementPass.setId(1L);
        CropProcurementPassDTO cropProcurementPassDTO = cropProcurementPassMapper.toDto(cropProcurementPass);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCropProcurementPassMockMvc.perform(post("/api/crop-procurement-passes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cropProcurementPassDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CropProcurementPass in the database
        List<CropProcurementPass> cropProcurementPassList = cropProcurementPassRepository.findAll();
        assertThat(cropProcurementPassList).hasSize(databaseSizeBeforeCreate);

        // Validate the CropProcurementPass in Elasticsearch
        verify(mockCropProcurementPassSearchRepository, times(0)).save(cropProcurementPass);
    }


    @Test
    @Transactional
    public void getAllCropProcurementPasses() throws Exception {
        // Initialize the database
        cropProcurementPassRepository.saveAndFlush(cropProcurementPass);

        // Get all the cropProcurementPassList
        restCropProcurementPassMockMvc.perform(get("/api/crop-procurement-passes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(cropProcurementPass.getId().intValue())))
            .andExpect(jsonPath("$.[*].cropDate").value(hasItem(DEFAULT_CROP_DATE.toString())))
            .andExpect(jsonPath("$.[*].cropType").value(hasItem(DEFAULT_CROP_TYPE.toString())))
            .andExpect(jsonPath("$.[*].expectedYeild").value(hasItem(DEFAULT_EXPECTED_YEILD)))
            .andExpect(jsonPath("$.[*].expectedYeildUnits").value(hasItem(DEFAULT_EXPECTED_YEILD_UNITS.toString())))
            .andExpect(jsonPath("$.[*].procurementDate").value(hasItem(DEFAULT_PROCUREMENT_DATE.toString())))
            .andExpect(jsonPath("$.[*].passStatus").value(hasItem(DEFAULT_PASS_STATUS.toString())))
            .andExpect(jsonPath("$.[*].createdAt").value(hasItem(DEFAULT_CREATED_AT.toString())))
            .andExpect(jsonPath("$.[*].modifiedAt").value(hasItem(DEFAULT_MODIFIED_AT.toString())));
    }
    
    @Test
    @Transactional
    public void getCropProcurementPass() throws Exception {
        // Initialize the database
        cropProcurementPassRepository.saveAndFlush(cropProcurementPass);

        // Get the cropProcurementPass
        restCropProcurementPassMockMvc.perform(get("/api/crop-procurement-passes/{id}", cropProcurementPass.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(cropProcurementPass.getId().intValue()))
            .andExpect(jsonPath("$.cropDate").value(DEFAULT_CROP_DATE.toString()))
            .andExpect(jsonPath("$.cropType").value(DEFAULT_CROP_TYPE.toString()))
            .andExpect(jsonPath("$.expectedYeild").value(DEFAULT_EXPECTED_YEILD))
            .andExpect(jsonPath("$.expectedYeildUnits").value(DEFAULT_EXPECTED_YEILD_UNITS.toString()))
            .andExpect(jsonPath("$.procurementDate").value(DEFAULT_PROCUREMENT_DATE.toString()))
            .andExpect(jsonPath("$.passStatus").value(DEFAULT_PASS_STATUS.toString()))
            .andExpect(jsonPath("$.createdAt").value(DEFAULT_CREATED_AT.toString()))
            .andExpect(jsonPath("$.modifiedAt").value(DEFAULT_MODIFIED_AT.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingCropProcurementPass() throws Exception {
        // Get the cropProcurementPass
        restCropProcurementPassMockMvc.perform(get("/api/crop-procurement-passes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCropProcurementPass() throws Exception {
        // Initialize the database
        cropProcurementPassRepository.saveAndFlush(cropProcurementPass);

        int databaseSizeBeforeUpdate = cropProcurementPassRepository.findAll().size();

        // Update the cropProcurementPass
        CropProcurementPass updatedCropProcurementPass = cropProcurementPassRepository.findById(cropProcurementPass.getId()).get();
        // Disconnect from session so that the updates on updatedCropProcurementPass are not directly saved in db
        em.detach(updatedCropProcurementPass);
        updatedCropProcurementPass
            .cropDate(UPDATED_CROP_DATE)
            .cropType(UPDATED_CROP_TYPE)
            .expectedYeild(UPDATED_EXPECTED_YEILD)
            .expectedYeildUnits(UPDATED_EXPECTED_YEILD_UNITS)
            .procurementDate(UPDATED_PROCUREMENT_DATE)
            .passStatus(UPDATED_PASS_STATUS)
            .createdAt(UPDATED_CREATED_AT)
            .modifiedAt(UPDATED_MODIFIED_AT);
        CropProcurementPassDTO cropProcurementPassDTO = cropProcurementPassMapper.toDto(updatedCropProcurementPass);

        restCropProcurementPassMockMvc.perform(put("/api/crop-procurement-passes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cropProcurementPassDTO)))
            .andExpect(status().isOk());

        // Validate the CropProcurementPass in the database
        List<CropProcurementPass> cropProcurementPassList = cropProcurementPassRepository.findAll();
        assertThat(cropProcurementPassList).hasSize(databaseSizeBeforeUpdate);
        CropProcurementPass testCropProcurementPass = cropProcurementPassList.get(cropProcurementPassList.size() - 1);
        assertThat(testCropProcurementPass.getCropDate()).isEqualTo(UPDATED_CROP_DATE);
        assertThat(testCropProcurementPass.getCropType()).isEqualTo(UPDATED_CROP_TYPE);
        assertThat(testCropProcurementPass.getExpectedYeild()).isEqualTo(UPDATED_EXPECTED_YEILD);
        assertThat(testCropProcurementPass.getExpectedYeildUnits()).isEqualTo(UPDATED_EXPECTED_YEILD_UNITS);
        assertThat(testCropProcurementPass.getProcurementDate()).isEqualTo(UPDATED_PROCUREMENT_DATE);
        assertThat(testCropProcurementPass.getPassStatus()).isEqualTo(UPDATED_PASS_STATUS);
        assertThat(testCropProcurementPass.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testCropProcurementPass.getModifiedAt()).isEqualTo(UPDATED_MODIFIED_AT);

        // Validate the CropProcurementPass in Elasticsearch
        verify(mockCropProcurementPassSearchRepository, times(1)).save(testCropProcurementPass);
    }

    @Test
    @Transactional
    public void updateNonExistingCropProcurementPass() throws Exception {
        int databaseSizeBeforeUpdate = cropProcurementPassRepository.findAll().size();

        // Create the CropProcurementPass
        CropProcurementPassDTO cropProcurementPassDTO = cropProcurementPassMapper.toDto(cropProcurementPass);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCropProcurementPassMockMvc.perform(put("/api/crop-procurement-passes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cropProcurementPassDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CropProcurementPass in the database
        List<CropProcurementPass> cropProcurementPassList = cropProcurementPassRepository.findAll();
        assertThat(cropProcurementPassList).hasSize(databaseSizeBeforeUpdate);

        // Validate the CropProcurementPass in Elasticsearch
        verify(mockCropProcurementPassSearchRepository, times(0)).save(cropProcurementPass);
    }

    @Test
    @Transactional
    public void deleteCropProcurementPass() throws Exception {
        // Initialize the database
        cropProcurementPassRepository.saveAndFlush(cropProcurementPass);

        int databaseSizeBeforeDelete = cropProcurementPassRepository.findAll().size();

        // Delete the cropProcurementPass
        restCropProcurementPassMockMvc.perform(delete("/api/crop-procurement-passes/{id}", cropProcurementPass.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<CropProcurementPass> cropProcurementPassList = cropProcurementPassRepository.findAll();
        assertThat(cropProcurementPassList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the CropProcurementPass in Elasticsearch
        verify(mockCropProcurementPassSearchRepository, times(1)).deleteById(cropProcurementPass.getId());
    }

    @Test
    @Transactional
    public void searchCropProcurementPass() throws Exception {
        // Initialize the database
        cropProcurementPassRepository.saveAndFlush(cropProcurementPass);
        when(mockCropProcurementPassSearchRepository.search(queryStringQuery("id:" + cropProcurementPass.getId()), PageRequest.of(0, 20)))
            .thenReturn(new PageImpl<>(Collections.singletonList(cropProcurementPass), PageRequest.of(0, 1), 1));
        // Search the cropProcurementPass
        restCropProcurementPassMockMvc.perform(get("/api/_search/crop-procurement-passes?query=id:" + cropProcurementPass.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(cropProcurementPass.getId().intValue())))
            .andExpect(jsonPath("$.[*].cropDate").value(hasItem(DEFAULT_CROP_DATE.toString())))
            .andExpect(jsonPath("$.[*].cropType").value(hasItem(DEFAULT_CROP_TYPE.toString())))
            .andExpect(jsonPath("$.[*].expectedYeild").value(hasItem(DEFAULT_EXPECTED_YEILD)))
            .andExpect(jsonPath("$.[*].expectedYeildUnits").value(hasItem(DEFAULT_EXPECTED_YEILD_UNITS.toString())))
            .andExpect(jsonPath("$.[*].procurementDate").value(hasItem(DEFAULT_PROCUREMENT_DATE.toString())))
            .andExpect(jsonPath("$.[*].passStatus").value(hasItem(DEFAULT_PASS_STATUS.toString())))
            .andExpect(jsonPath("$.[*].createdAt").value(hasItem(DEFAULT_CREATED_AT.toString())))
            .andExpect(jsonPath("$.[*].modifiedAt").value(hasItem(DEFAULT_MODIFIED_AT.toString())));
    }
}
