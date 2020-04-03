package com.vivek.myapp.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import org.springframework.data.elasticsearch.annotations.FieldType;
import java.io.Serializable;

import com.vivek.myapp.domain.enumeration.CenterType;

/**
 * A ProcurementCenter.
 */
@Entity
@Table(name = "procurement_center")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "procurementcenter")
public class ProcurementCenter implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @org.springframework.data.elasticsearch.annotations.Field(type = FieldType.Keyword)
    private Long id;

    @Column(name = "state")
    private String state;

    @Column(name = "district")
    private String district;

    @Column(name = "tehsil")
    private String tehsil;

    @Column(name = "village")
    private String village;

    @Enumerated(EnumType.STRING)
    @Column(name = "center_type")
    private CenterType centerType;

    @Column(name = "center_identification_number")
    private String centerIdentificationNumber;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getState() {
        return state;
    }

    public ProcurementCenter state(String state) {
        this.state = state;
        return this;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDistrict() {
        return district;
    }

    public ProcurementCenter district(String district) {
        this.district = district;
        return this;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getTehsil() {
        return tehsil;
    }

    public ProcurementCenter tehsil(String tehsil) {
        this.tehsil = tehsil;
        return this;
    }

    public void setTehsil(String tehsil) {
        this.tehsil = tehsil;
    }

    public String getVillage() {
        return village;
    }

    public ProcurementCenter village(String village) {
        this.village = village;
        return this;
    }

    public void setVillage(String village) {
        this.village = village;
    }

    public CenterType getCenterType() {
        return centerType;
    }

    public ProcurementCenter centerType(CenterType centerType) {
        this.centerType = centerType;
        return this;
    }

    public void setCenterType(CenterType centerType) {
        this.centerType = centerType;
    }

    public String getCenterIdentificationNumber() {
        return centerIdentificationNumber;
    }

    public ProcurementCenter centerIdentificationNumber(String centerIdentificationNumber) {
        this.centerIdentificationNumber = centerIdentificationNumber;
        return this;
    }

    public void setCenterIdentificationNumber(String centerIdentificationNumber) {
        this.centerIdentificationNumber = centerIdentificationNumber;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProcurementCenter)) {
            return false;
        }
        return id != null && id.equals(((ProcurementCenter) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "ProcurementCenter{" +
            "id=" + getId() +
            ", state='" + getState() + "'" +
            ", district='" + getDistrict() + "'" +
            ", tehsil='" + getTehsil() + "'" +
            ", village='" + getVillage() + "'" +
            ", centerType='" + getCenterType() + "'" +
            ", centerIdentificationNumber='" + getCenterIdentificationNumber() + "'" +
            "}";
    }
}
