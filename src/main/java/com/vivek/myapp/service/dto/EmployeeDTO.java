package com.vivek.myapp.service.dto;
import java.time.Instant;
import java.io.Serializable;
import java.util.Objects;
import com.vivek.myapp.domain.enumeration.Title;
import com.vivek.myapp.domain.enumeration.Gender;
import com.vivek.myapp.domain.enumeration.Designation;
import com.vivek.myapp.domain.enumeration.EmployeeType;

/**
 * A DTO for the {@link com.vivek.myapp.domain.Employee} entity.
 */
public class EmployeeDTO implements Serializable {

    private Long id;

    private Title title;

    private String name;

    private Gender gender;

    private String employeeCode;

    private String phoneNumber;

    private Instant joiningdate;

    private Instant dateOfBirth;

    private Designation designation;

    private EmployeeType employeeType;

    private String adhaarNumber;

    private String pan;

    private String signiture;


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

    public String getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(String employeeCode) {
        this.employeeCode = employeeCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Instant getJoiningdate() {
        return joiningdate;
    }

    public void setJoiningdate(Instant joiningdate) {
        this.joiningdate = joiningdate;
    }

    public Instant getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Instant dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Designation getDesignation() {
        return designation;
    }

    public void setDesignation(Designation designation) {
        this.designation = designation;
    }

    public EmployeeType getEmployeeType() {
        return employeeType;
    }

    public void setEmployeeType(EmployeeType employeeType) {
        this.employeeType = employeeType;
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

    public String getSigniture() {
        return signiture;
    }

    public void setSigniture(String signiture) {
        this.signiture = signiture;
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

        EmployeeDTO employeeDTO = (EmployeeDTO) o;
        if (employeeDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), employeeDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EmployeeDTO{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            ", name='" + getName() + "'" +
            ", gender='" + getGender() + "'" +
            ", employeeCode='" + getEmployeeCode() + "'" +
            ", phoneNumber='" + getPhoneNumber() + "'" +
            ", joiningdate='" + getJoiningdate() + "'" +
            ", dateOfBirth='" + getDateOfBirth() + "'" +
            ", designation='" + getDesignation() + "'" +
            ", employeeType='" + getEmployeeType() + "'" +
            ", adhaarNumber='" + getAdhaarNumber() + "'" +
            ", pan='" + getPan() + "'" +
            ", signiture='" + getSigniture() + "'" +
            ", user=" + getUserId() +
            "}";
    }
}
