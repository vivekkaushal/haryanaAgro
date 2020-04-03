package com.vivek.myapp.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import org.springframework.data.elasticsearch.annotations.FieldType;
import java.io.Serializable;
import java.time.Instant;

import com.vivek.myapp.domain.enumeration.CropType;

import com.vivek.myapp.domain.enumeration.ExpectedYieldUnit;

import com.vivek.myapp.domain.enumeration.PassStatus;

/**
 * A CropProcurementPass.
 */
@Entity
@Table(name = "crop_procurement_pass")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "cropprocurementpass")
public class CropProcurementPass implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @org.springframework.data.elasticsearch.annotations.Field(type = FieldType.Keyword)
    private Long id;

    @Column(name = "crop_date")
    private Instant cropDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "crop_type")
    private CropType cropType;

    @Column(name = "expected_yeild")
    private String expectedYeild;

    @Enumerated(EnumType.STRING)
    @Column(name = "expected_yeild_units")
    private ExpectedYieldUnit expectedYeildUnits;

    @Column(name = "procurement_date")
    private Instant procurementDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "pass_status")
    private PassStatus passStatus;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "modified_at")
    private Instant modifiedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties("cropProcurementPasses")
    private ProcurementCenter procurementCenter;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties("cropProcurementPasses")
    private Farmer farmer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties("cropProcurementPasses")
    private Employee passApprovedBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties("cropProcurementPasses")
    private Employee createdBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties("cropProcurementPasses")
    private Employee modifiedBy;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getCropDate() {
        return cropDate;
    }

    public CropProcurementPass cropDate(Instant cropDate) {
        this.cropDate = cropDate;
        return this;
    }

    public void setCropDate(Instant cropDate) {
        this.cropDate = cropDate;
    }

    public CropType getCropType() {
        return cropType;
    }

    public CropProcurementPass cropType(CropType cropType) {
        this.cropType = cropType;
        return this;
    }

    public void setCropType(CropType cropType) {
        this.cropType = cropType;
    }

    public String getExpectedYeild() {
        return expectedYeild;
    }

    public CropProcurementPass expectedYeild(String expectedYeild) {
        this.expectedYeild = expectedYeild;
        return this;
    }

    public void setExpectedYeild(String expectedYeild) {
        this.expectedYeild = expectedYeild;
    }

    public ExpectedYieldUnit getExpectedYeildUnits() {
        return expectedYeildUnits;
    }

    public CropProcurementPass expectedYeildUnits(ExpectedYieldUnit expectedYeildUnits) {
        this.expectedYeildUnits = expectedYeildUnits;
        return this;
    }

    public void setExpectedYeildUnits(ExpectedYieldUnit expectedYeildUnits) {
        this.expectedYeildUnits = expectedYeildUnits;
    }

    public Instant getProcurementDate() {
        return procurementDate;
    }

    public CropProcurementPass procurementDate(Instant procurementDate) {
        this.procurementDate = procurementDate;
        return this;
    }

    public void setProcurementDate(Instant procurementDate) {
        this.procurementDate = procurementDate;
    }

    public PassStatus getPassStatus() {
        return passStatus;
    }

    public CropProcurementPass passStatus(PassStatus passStatus) {
        this.passStatus = passStatus;
        return this;
    }

    public void setPassStatus(PassStatus passStatus) {
        this.passStatus = passStatus;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public CropProcurementPass createdAt(Instant createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getModifiedAt() {
        return modifiedAt;
    }

    public CropProcurementPass modifiedAt(Instant modifiedAt) {
        this.modifiedAt = modifiedAt;
        return this;
    }

    public void setModifiedAt(Instant modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    public ProcurementCenter getProcurementCenter() {
        return procurementCenter;
    }

    public CropProcurementPass procurementCenter(ProcurementCenter procurementCenter) {
        this.procurementCenter = procurementCenter;
        return this;
    }

    public void setProcurementCenter(ProcurementCenter procurementCenter) {
        this.procurementCenter = procurementCenter;
    }

    public Farmer getFarmer() {
        return farmer;
    }

    public CropProcurementPass farmer(Farmer farmer) {
        this.farmer = farmer;
        return this;
    }

    public void setFarmer(Farmer farmer) {
        this.farmer = farmer;
    }

    public Employee getPassApprovedBy() {
        return passApprovedBy;
    }

    public CropProcurementPass passApprovedBy(Employee employee) {
        this.passApprovedBy = employee;
        return this;
    }

    public void setPassApprovedBy(Employee employee) {
        this.passApprovedBy = employee;
    }

    public Employee getCreatedBy() {
        return createdBy;
    }

    public CropProcurementPass createdBy(Employee employee) {
        this.createdBy = employee;
        return this;
    }

    public void setCreatedBy(Employee employee) {
        this.createdBy = employee;
    }

    public Employee getModifiedBy() {
        return modifiedBy;
    }

    public CropProcurementPass modifiedBy(Employee employee) {
        this.modifiedBy = employee;
        return this;
    }

    public void setModifiedBy(Employee employee) {
        this.modifiedBy = employee;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CropProcurementPass)) {
            return false;
        }
        return id != null && id.equals(((CropProcurementPass) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "CropProcurementPass{" +
            "id=" + getId() +
            ", cropDate='" + getCropDate() + "'" +
            ", cropType='" + getCropType() + "'" +
            ", expectedYeild='" + getExpectedYeild() + "'" +
            ", expectedYeildUnits='" + getExpectedYeildUnits() + "'" +
            ", procurementDate='" + getProcurementDate() + "'" +
            ", passStatus='" + getPassStatus() + "'" +
            ", createdAt='" + getCreatedAt() + "'" +
            ", modifiedAt='" + getModifiedAt() + "'" +
            "}";
    }
}
