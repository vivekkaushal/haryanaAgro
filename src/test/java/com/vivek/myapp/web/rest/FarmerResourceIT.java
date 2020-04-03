package com.vivek.myapp.web.rest;

import com.vivek.myapp.HaryanaAgroApp;
import com.vivek.myapp.domain.Farmer;
import com.vivek.myapp.repository.FarmerRepository;
import com.vivek.myapp.repository.search.FarmerSearchRepository;
import com.vivek.myapp.service.FarmerService;
import com.vivek.myapp.service.dto.FarmerDTO;
import com.vivek.myapp.service.mapper.FarmerMapper;
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

import com.vivek.myapp.domain.enumeration.Title;
import com.vivek.myapp.domain.enumeration.Gender;
import com.vivek.myapp.domain.enumeration.FarmingType;
/**
 * Integration tests for the {@link FarmerResource} REST controller.
 */
@SpringBootTest(classes = HaryanaAgroApp.class)
public class FarmerResourceIT {

    private static final Title DEFAULT_TITLE = Title.Mr;
    private static final Title UPDATED_TITLE = Title.Mrs;

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final Gender DEFAULT_GENDER = Gender.Male;
    private static final Gender UPDATED_GENDER = Gender.Female;

    private static final FarmingType DEFAULT_FARMER_TYPE = FarmingType.Lease;
    private static final FarmingType UPDATED_FARMER_TYPE = FarmingType.Self;

    private static final String DEFAULT_ADHAAR_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_ADHAAR_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_PAN = "AAAAAAAAAA";
    private static final String UPDATED_PAN = "BBBBBBBBBB";

    private static final String DEFAULT_PHONE_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_PHONE_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_SIGNITURE = "AAAAAAAAAA";
    private static final String UPDATED_SIGNITURE = "BBBBBBBBBB";

    private static final String DEFAULT_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_HOUSE_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_HOUSE_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_STREET = "AAAAAAAAAA";
    private static final String UPDATED_STREET = "BBBBBBBBBB";

    private static final String DEFAULT_LANDMARK = "AAAAAAAAAA";
    private static final String UPDATED_LANDMARK = "BBBBBBBBBB";

    private static final String DEFAULT_VILLAGE = "AAAAAAAAAA";
    private static final String UPDATED_VILLAGE = "BBBBBBBBBB";

    private static final String DEFAULT_TEHSIL = "AAAAAAAAAA";
    private static final String UPDATED_TEHSIL = "BBBBBBBBBB";

    private static final String DEFAULT_DISTRICT = "AAAAAAAAAA";
    private static final String UPDATED_DISTRICT = "BBBBBBBBBB";

    private static final String DEFAULT_PINCODE = "AAAAAAAAAA";
    private static final String UPDATED_PINCODE = "BBBBBBBBBB";

    private static final String DEFAULT_KHATA_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_KHATA_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_SURVEY_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_SURVEY_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_BANKBOOK_SCAN_IMAGE_URL = "AAAAAAAAAA";
    private static final String UPDATED_BANKBOOK_SCAN_IMAGE_URL = "BBBBBBBBBB";

    private static final String DEFAULT_ACCOUNT_HOLDER_NAME_AS_PER_BANK = "AAAAAAAAAA";
    private static final String UPDATED_ACCOUNT_HOLDER_NAME_AS_PER_BANK = "BBBBBBBBBB";

    private static final String DEFAULT_ACCOUNT_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_ACCOUNT_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_IFSC = "AAAAAAAAAA";
    private static final String UPDATED_IFSC = "BBBBBBBBBB";

    private static final String DEFAULT_BANK_NAME = "AAAAAAAAAA";
    private static final String UPDATED_BANK_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_BRANCH = "AAAAAAAAAA";
    private static final String UPDATED_BRANCH = "BBBBBBBBBB";

    @Autowired
    private FarmerRepository farmerRepository;

    @Autowired
    private FarmerMapper farmerMapper;

    @Autowired
    private FarmerService farmerService;

    /**
     * This repository is mocked in the com.vivek.myapp.repository.search test package.
     *
     * @see com.vivek.myapp.repository.search.FarmerSearchRepositoryMockConfiguration
     */
    @Autowired
    private FarmerSearchRepository mockFarmerSearchRepository;

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

    private MockMvc restFarmerMockMvc;

    private Farmer farmer;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final FarmerResource farmerResource = new FarmerResource(farmerService);
        this.restFarmerMockMvc = MockMvcBuilders.standaloneSetup(farmerResource)
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
    public static Farmer createEntity(EntityManager em) {
        Farmer farmer = new Farmer()
            .title(DEFAULT_TITLE)
            .name(DEFAULT_NAME)
            .gender(DEFAULT_GENDER)
            .farmerType(DEFAULT_FARMER_TYPE)
            .adhaarNumber(DEFAULT_ADHAAR_NUMBER)
            .pan(DEFAULT_PAN)
            .phoneNumber(DEFAULT_PHONE_NUMBER)
            .signiture(DEFAULT_SIGNITURE)
            .address(DEFAULT_ADDRESS)
            .houseNumber(DEFAULT_HOUSE_NUMBER)
            .street(DEFAULT_STREET)
            .landmark(DEFAULT_LANDMARK)
            .village(DEFAULT_VILLAGE)
            .tehsil(DEFAULT_TEHSIL)
            .district(DEFAULT_DISTRICT)
            .pincode(DEFAULT_PINCODE)
            .khataNumber(DEFAULT_KHATA_NUMBER)
            .surveyNumber(DEFAULT_SURVEY_NUMBER)
            .bankbookScanImageUrl(DEFAULT_BANKBOOK_SCAN_IMAGE_URL)
            .accountHolderNameAsPerBank(DEFAULT_ACCOUNT_HOLDER_NAME_AS_PER_BANK)
            .accountNumber(DEFAULT_ACCOUNT_NUMBER)
            .ifsc(DEFAULT_IFSC)
            .bankName(DEFAULT_BANK_NAME)
            .branch(DEFAULT_BRANCH);
        return farmer;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Farmer createUpdatedEntity(EntityManager em) {
        Farmer farmer = new Farmer()
            .title(UPDATED_TITLE)
            .name(UPDATED_NAME)
            .gender(UPDATED_GENDER)
            .farmerType(UPDATED_FARMER_TYPE)
            .adhaarNumber(UPDATED_ADHAAR_NUMBER)
            .pan(UPDATED_PAN)
            .phoneNumber(UPDATED_PHONE_NUMBER)
            .signiture(UPDATED_SIGNITURE)
            .address(UPDATED_ADDRESS)
            .houseNumber(UPDATED_HOUSE_NUMBER)
            .street(UPDATED_STREET)
            .landmark(UPDATED_LANDMARK)
            .village(UPDATED_VILLAGE)
            .tehsil(UPDATED_TEHSIL)
            .district(UPDATED_DISTRICT)
            .pincode(UPDATED_PINCODE)
            .khataNumber(UPDATED_KHATA_NUMBER)
            .surveyNumber(UPDATED_SURVEY_NUMBER)
            .bankbookScanImageUrl(UPDATED_BANKBOOK_SCAN_IMAGE_URL)
            .accountHolderNameAsPerBank(UPDATED_ACCOUNT_HOLDER_NAME_AS_PER_BANK)
            .accountNumber(UPDATED_ACCOUNT_NUMBER)
            .ifsc(UPDATED_IFSC)
            .bankName(UPDATED_BANK_NAME)
            .branch(UPDATED_BRANCH);
        return farmer;
    }

    @BeforeEach
    public void initTest() {
        farmer = createEntity(em);
    }

    @Test
    @Transactional
    public void createFarmer() throws Exception {
        int databaseSizeBeforeCreate = farmerRepository.findAll().size();

        // Create the Farmer
        FarmerDTO farmerDTO = farmerMapper.toDto(farmer);
        restFarmerMockMvc.perform(post("/api/farmers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(farmerDTO)))
            .andExpect(status().isCreated());

        // Validate the Farmer in the database
        List<Farmer> farmerList = farmerRepository.findAll();
        assertThat(farmerList).hasSize(databaseSizeBeforeCreate + 1);
        Farmer testFarmer = farmerList.get(farmerList.size() - 1);
        assertThat(testFarmer.getTitle()).isEqualTo(DEFAULT_TITLE);
        assertThat(testFarmer.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testFarmer.getGender()).isEqualTo(DEFAULT_GENDER);
        assertThat(testFarmer.getFarmerType()).isEqualTo(DEFAULT_FARMER_TYPE);
        assertThat(testFarmer.getAdhaarNumber()).isEqualTo(DEFAULT_ADHAAR_NUMBER);
        assertThat(testFarmer.getPan()).isEqualTo(DEFAULT_PAN);
        assertThat(testFarmer.getPhoneNumber()).isEqualTo(DEFAULT_PHONE_NUMBER);
        assertThat(testFarmer.getSigniture()).isEqualTo(DEFAULT_SIGNITURE);
        assertThat(testFarmer.getAddress()).isEqualTo(DEFAULT_ADDRESS);
        assertThat(testFarmer.getHouseNumber()).isEqualTo(DEFAULT_HOUSE_NUMBER);
        assertThat(testFarmer.getStreet()).isEqualTo(DEFAULT_STREET);
        assertThat(testFarmer.getLandmark()).isEqualTo(DEFAULT_LANDMARK);
        assertThat(testFarmer.getVillage()).isEqualTo(DEFAULT_VILLAGE);
        assertThat(testFarmer.getTehsil()).isEqualTo(DEFAULT_TEHSIL);
        assertThat(testFarmer.getDistrict()).isEqualTo(DEFAULT_DISTRICT);
        assertThat(testFarmer.getPincode()).isEqualTo(DEFAULT_PINCODE);
        assertThat(testFarmer.getKhataNumber()).isEqualTo(DEFAULT_KHATA_NUMBER);
        assertThat(testFarmer.getSurveyNumber()).isEqualTo(DEFAULT_SURVEY_NUMBER);
        assertThat(testFarmer.getBankbookScanImageUrl()).isEqualTo(DEFAULT_BANKBOOK_SCAN_IMAGE_URL);
        assertThat(testFarmer.getAccountHolderNameAsPerBank()).isEqualTo(DEFAULT_ACCOUNT_HOLDER_NAME_AS_PER_BANK);
        assertThat(testFarmer.getAccountNumber()).isEqualTo(DEFAULT_ACCOUNT_NUMBER);
        assertThat(testFarmer.getIfsc()).isEqualTo(DEFAULT_IFSC);
        assertThat(testFarmer.getBankName()).isEqualTo(DEFAULT_BANK_NAME);
        assertThat(testFarmer.getBranch()).isEqualTo(DEFAULT_BRANCH);

        // Validate the Farmer in Elasticsearch
        verify(mockFarmerSearchRepository, times(1)).save(testFarmer);
    }

    @Test
    @Transactional
    public void createFarmerWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = farmerRepository.findAll().size();

        // Create the Farmer with an existing ID
        farmer.setId(1L);
        FarmerDTO farmerDTO = farmerMapper.toDto(farmer);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFarmerMockMvc.perform(post("/api/farmers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(farmerDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Farmer in the database
        List<Farmer> farmerList = farmerRepository.findAll();
        assertThat(farmerList).hasSize(databaseSizeBeforeCreate);

        // Validate the Farmer in Elasticsearch
        verify(mockFarmerSearchRepository, times(0)).save(farmer);
    }


    @Test
    @Transactional
    public void getAllFarmers() throws Exception {
        // Initialize the database
        farmerRepository.saveAndFlush(farmer);

        // Get all the farmerList
        restFarmerMockMvc.perform(get("/api/farmers?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(farmer.getId().intValue())))
            .andExpect(jsonPath("$.[*].title").value(hasItem(DEFAULT_TITLE.toString())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].gender").value(hasItem(DEFAULT_GENDER.toString())))
            .andExpect(jsonPath("$.[*].farmerType").value(hasItem(DEFAULT_FARMER_TYPE.toString())))
            .andExpect(jsonPath("$.[*].adhaarNumber").value(hasItem(DEFAULT_ADHAAR_NUMBER)))
            .andExpect(jsonPath("$.[*].pan").value(hasItem(DEFAULT_PAN)))
            .andExpect(jsonPath("$.[*].phoneNumber").value(hasItem(DEFAULT_PHONE_NUMBER)))
            .andExpect(jsonPath("$.[*].signiture").value(hasItem(DEFAULT_SIGNITURE)))
            .andExpect(jsonPath("$.[*].address").value(hasItem(DEFAULT_ADDRESS)))
            .andExpect(jsonPath("$.[*].houseNumber").value(hasItem(DEFAULT_HOUSE_NUMBER)))
            .andExpect(jsonPath("$.[*].street").value(hasItem(DEFAULT_STREET)))
            .andExpect(jsonPath("$.[*].landmark").value(hasItem(DEFAULT_LANDMARK)))
            .andExpect(jsonPath("$.[*].village").value(hasItem(DEFAULT_VILLAGE)))
            .andExpect(jsonPath("$.[*].tehsil").value(hasItem(DEFAULT_TEHSIL)))
            .andExpect(jsonPath("$.[*].district").value(hasItem(DEFAULT_DISTRICT)))
            .andExpect(jsonPath("$.[*].pincode").value(hasItem(DEFAULT_PINCODE)))
            .andExpect(jsonPath("$.[*].khataNumber").value(hasItem(DEFAULT_KHATA_NUMBER)))
            .andExpect(jsonPath("$.[*].surveyNumber").value(hasItem(DEFAULT_SURVEY_NUMBER)))
            .andExpect(jsonPath("$.[*].bankbookScanImageUrl").value(hasItem(DEFAULT_BANKBOOK_SCAN_IMAGE_URL)))
            .andExpect(jsonPath("$.[*].accountHolderNameAsPerBank").value(hasItem(DEFAULT_ACCOUNT_HOLDER_NAME_AS_PER_BANK)))
            .andExpect(jsonPath("$.[*].accountNumber").value(hasItem(DEFAULT_ACCOUNT_NUMBER)))
            .andExpect(jsonPath("$.[*].ifsc").value(hasItem(DEFAULT_IFSC)))
            .andExpect(jsonPath("$.[*].bankName").value(hasItem(DEFAULT_BANK_NAME)))
            .andExpect(jsonPath("$.[*].branch").value(hasItem(DEFAULT_BRANCH)));
    }
    
    @Test
    @Transactional
    public void getFarmer() throws Exception {
        // Initialize the database
        farmerRepository.saveAndFlush(farmer);

        // Get the farmer
        restFarmerMockMvc.perform(get("/api/farmers/{id}", farmer.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(farmer.getId().intValue()))
            .andExpect(jsonPath("$.title").value(DEFAULT_TITLE.toString()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.gender").value(DEFAULT_GENDER.toString()))
            .andExpect(jsonPath("$.farmerType").value(DEFAULT_FARMER_TYPE.toString()))
            .andExpect(jsonPath("$.adhaarNumber").value(DEFAULT_ADHAAR_NUMBER))
            .andExpect(jsonPath("$.pan").value(DEFAULT_PAN))
            .andExpect(jsonPath("$.phoneNumber").value(DEFAULT_PHONE_NUMBER))
            .andExpect(jsonPath("$.signiture").value(DEFAULT_SIGNITURE))
            .andExpect(jsonPath("$.address").value(DEFAULT_ADDRESS))
            .andExpect(jsonPath("$.houseNumber").value(DEFAULT_HOUSE_NUMBER))
            .andExpect(jsonPath("$.street").value(DEFAULT_STREET))
            .andExpect(jsonPath("$.landmark").value(DEFAULT_LANDMARK))
            .andExpect(jsonPath("$.village").value(DEFAULT_VILLAGE))
            .andExpect(jsonPath("$.tehsil").value(DEFAULT_TEHSIL))
            .andExpect(jsonPath("$.district").value(DEFAULT_DISTRICT))
            .andExpect(jsonPath("$.pincode").value(DEFAULT_PINCODE))
            .andExpect(jsonPath("$.khataNumber").value(DEFAULT_KHATA_NUMBER))
            .andExpect(jsonPath("$.surveyNumber").value(DEFAULT_SURVEY_NUMBER))
            .andExpect(jsonPath("$.bankbookScanImageUrl").value(DEFAULT_BANKBOOK_SCAN_IMAGE_URL))
            .andExpect(jsonPath("$.accountHolderNameAsPerBank").value(DEFAULT_ACCOUNT_HOLDER_NAME_AS_PER_BANK))
            .andExpect(jsonPath("$.accountNumber").value(DEFAULT_ACCOUNT_NUMBER))
            .andExpect(jsonPath("$.ifsc").value(DEFAULT_IFSC))
            .andExpect(jsonPath("$.bankName").value(DEFAULT_BANK_NAME))
            .andExpect(jsonPath("$.branch").value(DEFAULT_BRANCH));
    }

    @Test
    @Transactional
    public void getNonExistingFarmer() throws Exception {
        // Get the farmer
        restFarmerMockMvc.perform(get("/api/farmers/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFarmer() throws Exception {
        // Initialize the database
        farmerRepository.saveAndFlush(farmer);

        int databaseSizeBeforeUpdate = farmerRepository.findAll().size();

        // Update the farmer
        Farmer updatedFarmer = farmerRepository.findById(farmer.getId()).get();
        // Disconnect from session so that the updates on updatedFarmer are not directly saved in db
        em.detach(updatedFarmer);
        updatedFarmer
            .title(UPDATED_TITLE)
            .name(UPDATED_NAME)
            .gender(UPDATED_GENDER)
            .farmerType(UPDATED_FARMER_TYPE)
            .adhaarNumber(UPDATED_ADHAAR_NUMBER)
            .pan(UPDATED_PAN)
            .phoneNumber(UPDATED_PHONE_NUMBER)
            .signiture(UPDATED_SIGNITURE)
            .address(UPDATED_ADDRESS)
            .houseNumber(UPDATED_HOUSE_NUMBER)
            .street(UPDATED_STREET)
            .landmark(UPDATED_LANDMARK)
            .village(UPDATED_VILLAGE)
            .tehsil(UPDATED_TEHSIL)
            .district(UPDATED_DISTRICT)
            .pincode(UPDATED_PINCODE)
            .khataNumber(UPDATED_KHATA_NUMBER)
            .surveyNumber(UPDATED_SURVEY_NUMBER)
            .bankbookScanImageUrl(UPDATED_BANKBOOK_SCAN_IMAGE_URL)
            .accountHolderNameAsPerBank(UPDATED_ACCOUNT_HOLDER_NAME_AS_PER_BANK)
            .accountNumber(UPDATED_ACCOUNT_NUMBER)
            .ifsc(UPDATED_IFSC)
            .bankName(UPDATED_BANK_NAME)
            .branch(UPDATED_BRANCH);
        FarmerDTO farmerDTO = farmerMapper.toDto(updatedFarmer);

        restFarmerMockMvc.perform(put("/api/farmers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(farmerDTO)))
            .andExpect(status().isOk());

        // Validate the Farmer in the database
        List<Farmer> farmerList = farmerRepository.findAll();
        assertThat(farmerList).hasSize(databaseSizeBeforeUpdate);
        Farmer testFarmer = farmerList.get(farmerList.size() - 1);
        assertThat(testFarmer.getTitle()).isEqualTo(UPDATED_TITLE);
        assertThat(testFarmer.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testFarmer.getGender()).isEqualTo(UPDATED_GENDER);
        assertThat(testFarmer.getFarmerType()).isEqualTo(UPDATED_FARMER_TYPE);
        assertThat(testFarmer.getAdhaarNumber()).isEqualTo(UPDATED_ADHAAR_NUMBER);
        assertThat(testFarmer.getPan()).isEqualTo(UPDATED_PAN);
        assertThat(testFarmer.getPhoneNumber()).isEqualTo(UPDATED_PHONE_NUMBER);
        assertThat(testFarmer.getSigniture()).isEqualTo(UPDATED_SIGNITURE);
        assertThat(testFarmer.getAddress()).isEqualTo(UPDATED_ADDRESS);
        assertThat(testFarmer.getHouseNumber()).isEqualTo(UPDATED_HOUSE_NUMBER);
        assertThat(testFarmer.getStreet()).isEqualTo(UPDATED_STREET);
        assertThat(testFarmer.getLandmark()).isEqualTo(UPDATED_LANDMARK);
        assertThat(testFarmer.getVillage()).isEqualTo(UPDATED_VILLAGE);
        assertThat(testFarmer.getTehsil()).isEqualTo(UPDATED_TEHSIL);
        assertThat(testFarmer.getDistrict()).isEqualTo(UPDATED_DISTRICT);
        assertThat(testFarmer.getPincode()).isEqualTo(UPDATED_PINCODE);
        assertThat(testFarmer.getKhataNumber()).isEqualTo(UPDATED_KHATA_NUMBER);
        assertThat(testFarmer.getSurveyNumber()).isEqualTo(UPDATED_SURVEY_NUMBER);
        assertThat(testFarmer.getBankbookScanImageUrl()).isEqualTo(UPDATED_BANKBOOK_SCAN_IMAGE_URL);
        assertThat(testFarmer.getAccountHolderNameAsPerBank()).isEqualTo(UPDATED_ACCOUNT_HOLDER_NAME_AS_PER_BANK);
        assertThat(testFarmer.getAccountNumber()).isEqualTo(UPDATED_ACCOUNT_NUMBER);
        assertThat(testFarmer.getIfsc()).isEqualTo(UPDATED_IFSC);
        assertThat(testFarmer.getBankName()).isEqualTo(UPDATED_BANK_NAME);
        assertThat(testFarmer.getBranch()).isEqualTo(UPDATED_BRANCH);

        // Validate the Farmer in Elasticsearch
        verify(mockFarmerSearchRepository, times(1)).save(testFarmer);
    }

    @Test
    @Transactional
    public void updateNonExistingFarmer() throws Exception {
        int databaseSizeBeforeUpdate = farmerRepository.findAll().size();

        // Create the Farmer
        FarmerDTO farmerDTO = farmerMapper.toDto(farmer);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFarmerMockMvc.perform(put("/api/farmers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(farmerDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Farmer in the database
        List<Farmer> farmerList = farmerRepository.findAll();
        assertThat(farmerList).hasSize(databaseSizeBeforeUpdate);

        // Validate the Farmer in Elasticsearch
        verify(mockFarmerSearchRepository, times(0)).save(farmer);
    }

    @Test
    @Transactional
    public void deleteFarmer() throws Exception {
        // Initialize the database
        farmerRepository.saveAndFlush(farmer);

        int databaseSizeBeforeDelete = farmerRepository.findAll().size();

        // Delete the farmer
        restFarmerMockMvc.perform(delete("/api/farmers/{id}", farmer.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Farmer> farmerList = farmerRepository.findAll();
        assertThat(farmerList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the Farmer in Elasticsearch
        verify(mockFarmerSearchRepository, times(1)).deleteById(farmer.getId());
    }

    @Test
    @Transactional
    public void searchFarmer() throws Exception {
        // Initialize the database
        farmerRepository.saveAndFlush(farmer);
        when(mockFarmerSearchRepository.search(queryStringQuery("id:" + farmer.getId()), PageRequest.of(0, 20)))
            .thenReturn(new PageImpl<>(Collections.singletonList(farmer), PageRequest.of(0, 1), 1));
        // Search the farmer
        restFarmerMockMvc.perform(get("/api/_search/farmers?query=id:" + farmer.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(farmer.getId().intValue())))
            .andExpect(jsonPath("$.[*].title").value(hasItem(DEFAULT_TITLE.toString())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].gender").value(hasItem(DEFAULT_GENDER.toString())))
            .andExpect(jsonPath("$.[*].farmerType").value(hasItem(DEFAULT_FARMER_TYPE.toString())))
            .andExpect(jsonPath("$.[*].adhaarNumber").value(hasItem(DEFAULT_ADHAAR_NUMBER)))
            .andExpect(jsonPath("$.[*].pan").value(hasItem(DEFAULT_PAN)))
            .andExpect(jsonPath("$.[*].phoneNumber").value(hasItem(DEFAULT_PHONE_NUMBER)))
            .andExpect(jsonPath("$.[*].signiture").value(hasItem(DEFAULT_SIGNITURE)))
            .andExpect(jsonPath("$.[*].address").value(hasItem(DEFAULT_ADDRESS)))
            .andExpect(jsonPath("$.[*].houseNumber").value(hasItem(DEFAULT_HOUSE_NUMBER)))
            .andExpect(jsonPath("$.[*].street").value(hasItem(DEFAULT_STREET)))
            .andExpect(jsonPath("$.[*].landmark").value(hasItem(DEFAULT_LANDMARK)))
            .andExpect(jsonPath("$.[*].village").value(hasItem(DEFAULT_VILLAGE)))
            .andExpect(jsonPath("$.[*].tehsil").value(hasItem(DEFAULT_TEHSIL)))
            .andExpect(jsonPath("$.[*].district").value(hasItem(DEFAULT_DISTRICT)))
            .andExpect(jsonPath("$.[*].pincode").value(hasItem(DEFAULT_PINCODE)))
            .andExpect(jsonPath("$.[*].khataNumber").value(hasItem(DEFAULT_KHATA_NUMBER)))
            .andExpect(jsonPath("$.[*].surveyNumber").value(hasItem(DEFAULT_SURVEY_NUMBER)))
            .andExpect(jsonPath("$.[*].bankbookScanImageUrl").value(hasItem(DEFAULT_BANKBOOK_SCAN_IMAGE_URL)))
            .andExpect(jsonPath("$.[*].accountHolderNameAsPerBank").value(hasItem(DEFAULT_ACCOUNT_HOLDER_NAME_AS_PER_BANK)))
            .andExpect(jsonPath("$.[*].accountNumber").value(hasItem(DEFAULT_ACCOUNT_NUMBER)))
            .andExpect(jsonPath("$.[*].ifsc").value(hasItem(DEFAULT_IFSC)))
            .andExpect(jsonPath("$.[*].bankName").value(hasItem(DEFAULT_BANK_NAME)))
            .andExpect(jsonPath("$.[*].branch").value(hasItem(DEFAULT_BRANCH)));
    }
}
