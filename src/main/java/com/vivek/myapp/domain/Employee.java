package com.vivek.myapp.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import org.springframework.data.elasticsearch.annotations.FieldType;
import java.io.Serializable;
import java.time.Instant;

import com.vivek.myapp.domain.enumeration.Title;

import com.vivek.myapp.domain.enumeration.Gender;

import com.vivek.myapp.domain.enumeration.Designation;

import com.vivek.myapp.domain.enumeration.EmployeeType;

/**
 * A Employee.
 */
@Entity
@Table(name = "employee")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "employee")
public class Employee implements Serializable {

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

    @Column(name = "employee_code")
    private String employeeCode;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "joiningdate")
    private Instant joiningdate;

    @Column(name = "date_of_birth")
    private Instant dateOfBirth;

    @Enumerated(EnumType.STRING)
    @Column(name = "designation")
    private Designation designation;

    @Enumerated(EnumType.STRING)
    @Column(name = "employee_type")
    private EmployeeType employeeType;

    @Column(name = "adhaar_number")
    private String adhaarNumber;

    @Column(name = "pan")
    private String pan;

    @Column(name = "signiture")
    private String signiture;

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

    public Employee title(Title title) {
        this.title = title;
        return this;
    }

    public void setTitle(Title title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public Employee name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Gender getGender() {
        return gender;
    }

    public Employee gender(Gender gender) {
        this.gender = gender;
        return this;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getEmployeeCode() {
        return employeeCode;
    }

    public Employee employeeCode(String employeeCode) {
        this.employeeCode = employeeCode;
        return this;
    }

    public void setEmployeeCode(String employeeCode) {
        this.employeeCode = employeeCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Employee phoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Instant getJoiningdate() {
        return joiningdate;
    }

    public Employee joiningdate(Instant joiningdate) {
        this.joiningdate = joiningdate;
        return this;
    }

    public void setJoiningdate(Instant joiningdate) {
        this.joiningdate = joiningdate;
    }

    public Instant getDateOfBirth() {
        return dateOfBirth;
    }

    public Employee dateOfBirth(Instant dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        return this;
    }

    public void setDateOfBirth(Instant dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Designation getDesignation() {
        return designation;
    }

    public Employee designation(Designation designation) {
        this.designation = designation;
        return this;
    }

    public void setDesignation(Designation designation) {
        this.designation = designation;
    }

    public EmployeeType getEmployeeType() {
        return employeeType;
    }

    public Employee employeeType(EmployeeType employeeType) {
        this.employeeType = employeeType;
        return this;
    }

    public void setEmployeeType(EmployeeType employeeType) {
        this.employeeType = employeeType;
    }

    public String getAdhaarNumber() {
        return adhaarNumber;
    }

    public Employee adhaarNumber(String adhaarNumber) {
        this.adhaarNumber = adhaarNumber;
        return this;
    }

    public void setAdhaarNumber(String adhaarNumber) {
        this.adhaarNumber = adhaarNumber;
    }

    public String getPan() {
        return pan;
    }

    public Employee pan(String pan) {
        this.pan = pan;
        return this;
    }

    public void setPan(String pan) {
        this.pan = pan;
    }

    public String getSigniture() {
        return signiture;
    }

    public Employee signiture(String signiture) {
        this.signiture = signiture;
        return this;
    }

    public void setSigniture(String signiture) {
        this.signiture = signiture;
    }

    public User getUser() {
        return user;
    }

    public Employee user(User user) {
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
        if (!(o instanceof Employee)) {
            return false;
        }
        return id != null && id.equals(((Employee) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Employee{" +
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
            "}";
    }
}
