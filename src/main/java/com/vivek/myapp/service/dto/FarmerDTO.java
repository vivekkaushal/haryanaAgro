package com.vivek.myapp.service.dto;
import java.io.Serializable;
import java.util.Objects;
import com.vivek.myapp.domain.enumeration.Title;
import com.vivek.myapp.domain.enumeration.Gender;
import com.vivek.myapp.domain.enumeration.FarmingType;

/**
 * A DTO for the {@link com.vivek.myapp.domain.Farmer} entity.
 */
public class FarmerDTO implements Serializable {

    private Long id;

    private Title title;

    private String name;

    private Gender gender;

    private FarmingType farmerType;

    private String adhaarNumber;

    private String pan;

    private String phoneNumber;

    private String signiture;

    private String address;

    private String houseNumber;

    private String street;

    private String landmark;

    private String village;

    private String tehsil;

    private String district;

    private String pincode;

    private String khataNumber;

    private String surveyNumber;

    private String bankbookScanImageUrl;

    private String accountHolderNameAsPerBank;

    private String accountNumber;

    private String ifsc;

    private String bankName;

    private String branch;


    private Long userId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Title getTitle() {
        return title;
    }

    public void setTitle(Title title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public FarmingType getFarmerType() {
        return farmerType;
    }

    public void setFarmerType(FarmingType farmerType) {
        this.farmerType = farmerType;
    }

    public String getAdhaarNumber() {
        return adhaarNumber;
    }

    public void setAdhaarNumber(String adhaarNumber) {
        this.adhaarNumber = adhaarNumber;
    }

    public String getPan() {
        return pan;
    }

    public void setPan(String pan) {
        this.pan = pan;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getSigniture() {
        return signiture;
    }

    public void setSigniture(String signiture) {
        this.signiture = signiture;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getLandmark() {
        return landmark;
    }

    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
    }

    public String getTehsil() {
        return tehsil;
    }

    public void setTehsil(String tehsil) {
        this.tehsil = tehsil;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getKhataNumber() {
        return khataNumber;
    }

    public void setKhataNumber(String khataNumber) {
        this.khataNumber = khataNumber;
    }

    public String getSurveyNumber() {
        return surveyNumber;
    }

    public void setSurveyNumber(String surveyNumber) {
        this.surveyNumber = surveyNumber;
    }

    public String getBankbookScanImageUrl() {
        return bankbookScanImageUrl;
    }

    public void setBankbookScanImageUrl(String bankbookScanImageUrl) {
        this.bankbookScanImageUrl = bankbookScanImageUrl;
    }

    public String getAccountHolderNameAsPerBank() {
        return accountHolderNameAsPerBank;
    }

    public void setAccountHolderNameAsPerBank(String accountHolderNameAsPerBank) {
        this.accountHolderNameAsPerBank = accountHolderNameAsPerBank;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getIfsc() {
        return ifsc;
    }

    public void setIfsc(String ifsc) {
        this.ifsc = ifsc;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        FarmerDTO farmerDTO = (FarmerDTO) o;
        if (farmerDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), farmerDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "FarmerDTO{" +
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
            ", user=" + getUserId() +
            "}";
    }
}
