package com.vivek.myapp.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import org.springframework.data.elasticsearch.annotations.FieldType;
import java.io.Serializable;

import com.vivek.myapp.domain.enumeration.Title;

import com.vivek.myapp.domain.enumeration.Gender;

import com.vivek.myapp.domain.enumeration.FarmingType;

/**
 * A Farmer.
 */
@Entity
@Table(name = "farmer")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "farmer")
public class Farmer implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @org.springframework.data.elasticsearch.annotations.Field(type = FieldType.Keyword)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "title")
    private Title title;

    @Column(name = "name")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;

    @Enumerated(EnumType.STRING)
    @Column(name = "farmer_type")
    private FarmingType farmerType;

    @Column(name = "adhaar_number")
    private String adhaarNumber;

    @Column(name = "pan")
    private String pan;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "signiture")
    private String signiture;

    @Column(name = "address")
    private String address;

    @Column(name = "house_number")
    private String houseNumber;

    @Column(name = "street")
    private String street;

    @Column(name = "landmark")
    private String landmark;

    @Column(name = "village")
    private String village;

    @Column(name = "tehsil")
    private String tehsil;

    @Column(name = "district")
    private String district;

    @Column(name = "pincode")
    private String pincode;

    @Column(name = "khata_number")
    private String khataNumber;

    @Column(name = "survey_number")
    private String surveyNumber;

    @Column(name = "bankbook_scan_image_url")
    private String bankbookScanImageUrl;

    @Column(name = "account_holder_name_as_per_bank")
    private String accountHolderNameAsPerBank;

    @Column(name = "account_number")
    private String accountNumber;

    @Column(name = "ifsc")
    private String ifsc;

    @Column(name = "bank_name")
    private String bankName;

    @Column(name = "branch")
    private String branch;

    @OneToOne(fetch = FetchType.LAZY)

    @JoinColumn(unique = true)
    private User user;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Title getTitle() {
        return title;
    }

    public Farmer title(Title title) {
        this.title = title;
        return this;
    }

    public void setTitle(Title title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public Farmer name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Gender getGender() {
        return gender;
    }

    public Farmer gender(Gender gender) {
        this.gender = gender;
        return this;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public FarmingType getFarmerType() {
        return farmerType;
    }

    public Farmer farmerType(FarmingType farmerType) {
        this.farmerType = farmerType;
        return this;
    }

    public void setFarmerType(FarmingType farmerType) {
        this.farmerType = farmerType;
    }

    public String getAdhaarNumber() {
        return adhaarNumber;
    }

    public Farmer adhaarNumber(String adhaarNumber) {
        this.adhaarNumber = adhaarNumber;
        return this;
    }

    public void setAdhaarNumber(String adhaarNumber) {
        this.adhaarNumber = adhaarNumber;
    }

    public String getPan() {
        return pan;
    }

    public Farmer pan(String pan) {
        this.pan = pan;
        return this;
    }

    public void setPan(String pan) {
        this.pan = pan;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Farmer phoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getSigniture() {
        return signiture;
    }

    public Farmer signiture(String signiture) {
        this.signiture = signiture;
        return this;
    }

    public void setSigniture(String signiture) {
        this.signiture = signiture;
    }

    public String getAddress() {
        return address;
    }

    public Farmer address(String address) {
        this.address = address;
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public Farmer houseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
        return this;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getStreet() {
        return street;
    }

    public Farmer street(String street) {
        this.street = street;
        return this;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getLandmark() {
        return landmark;
    }

    public Farmer landmark(String landmark) {
        this.landmark = landmark;
        return this;
    }

    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }

    public String getVillage() {
        return village;
    }

    public Farmer village(String village) {
        this.village = village;
        return this;
    }

    public void setVillage(String village) {
        this.village = village;
    }

    public String getTehsil() {
        return tehsil;
    }

    public Farmer tehsil(String tehsil) {
        this.tehsil = tehsil;
        return this;
    }

    public void setTehsil(String tehsil) {
        this.tehsil = tehsil;
    }

    public String getDistrict() {
        return district;
    }

    public Farmer district(String district) {
        this.district = district;
        return this;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getPincode() {
        return pincode;
    }

    public Farmer pincode(String pincode) {
        this.pincode = pincode;
        return this;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getKhataNumber() {
        return khataNumber;
    }

    public Farmer khataNumber(String khataNumber) {
        this.khataNumber = khataNumber;
        return this;
    }

    public void setKhataNumber(String khataNumber) {
        this.khataNumber = khataNumber;
    }

    public String getSurveyNumber() {
        return surveyNumber;
    }

    public Farmer surveyNumber(String surveyNumber) {
        this.surveyNumber = surveyNumber;
        return this;
    }

    public void setSurveyNumber(String surveyNumber) {
        this.surveyNumber = surveyNumber;
    }

    public String getBankbookScanImageUrl() {
        return bankbookScanImageUrl;
    }

    public Farmer bankbookScanImageUrl(String bankbookScanImageUrl) {
        this.bankbookScanImageUrl = bankbookScanImageUrl;
        return this;
    }

    public void setBankbookScanImageUrl(String bankbookScanImageUrl) {
        this.bankbookScanImageUrl = bankbookScanImageUrl;
    }

    public String getAccountHolderNameAsPerBank() {
        return accountHolderNameAsPerBank;
    }

    public Farmer accountHolderNameAsPerBank(String accountHolderNameAsPerBank) {
        this.accountHolderNameAsPerBank = accountHolderNameAsPerBank;
        return this;
    }

    public void setAccountHolderNameAsPerBank(String accountHolderNameAsPerBank) {
        this.accountHolderNameAsPerBank = accountHolderNameAsPerBank;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public Farmer accountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
        return this;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getIfsc() {
        return ifsc;
    }

    public Farmer ifsc(String ifsc) {
        this.ifsc = ifsc;
        return this;
    }

    public void setIfsc(String ifsc) {
        this.ifsc = ifsc;
    }

    public String getBankName() {
        return bankName;
    }

    public Farmer bankName(String bankName) {
        this.bankName = bankName;
        return this;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBranch() {
        return branch;
    }

    public Farmer branch(String branch) {
        this.branch = branch;
        return this;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public User getUser() {
        return user;
    }

    public Farmer user(User user) {
        this.user = user;
        return this;
    }

    public void setUser(User user) {
        this.user = user;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Farmer)) {
            return false;
        }
        return id != null && id.equals(((Farmer) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Farmer{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            ", name='" + getName() + "'" +
            ", gender='" + getGender() + "'" +
            ", farmerType='" + getFarmerType() + "'" +
            ", adhaarNumber='" + getAdhaarNumber() + "'" +
            ", pan='" + getPan() + "'" +
            ", phoneNumber='" + getPhoneNumber() + "'" +
            ", signiture='" + getSigniture() + "'" +
            ", address='" + getAddress() + "'" +
            ", houseNumber='" + getHouseNumber() + "'" +
            ", street='" + getStreet() + "'" +
            ", landmark='" + getLandmark() + "'" +
            ", village='" + getVillage() + "'" +
            ", tehsil='" + getTehsil() + "'" +
            ", district='" + getDistrict() + "'" +
            ", pincode='" + getPincode() + "'" +
            ", khataNumber='" + getKhataNumber() + "'" +
            ", surveyNumber='" + getSurveyNumber() + "'" +
            ", bankbookScanImageUrl='" + getBankbookScanImageUrl() + "'" +
            ", accountHolderNameAsPerBank='" + getAccountHolderNameAsPerBank() + "'" +
            ", accountNumber='" + getAccountNumber() + "'" +
            ", ifsc='" + getIfsc() + "'" +
            ", bankName='" + getBankName() + "'" +
            ", branch='" + getBranch() + "'" +
            "}";
    }
}
