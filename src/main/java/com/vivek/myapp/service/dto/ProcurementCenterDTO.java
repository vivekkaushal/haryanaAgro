package com.vivek.myapp.service.dto;
import java.io.Serializable;
import java.util.Objects;
import com.vivek.myapp.domain.enumeration.CenterType;

/**
 * A DTO for the {@link com.vivek.myapp.domain.ProcurementCenter} entity.
 */
public class ProcurementCenterDTO implements Serializable {

    private Long id;

    private String state;

    private String district;

    private String tehsil;

    private String village;

    private CenterType centerType;

    private String centerIdentificationNumber;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getTehsil() {
        return tehsil;
    }

    public void setTehsil(String tehsil) {
        this.tehsil = tehsil;
    }

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
    }

    public CenterType getCenterType() {
        return centerType;
    }

    public void setCenterType(CenterType centerType) {
        this.centerType = centerType;
    }

    public String getCenterIdentificationNumber() {
        return centerIdentificationNumber;
    }

    public void setCenterIdentificationNumber(String centerIdentificationNumber) {
        this.centerIdentificationNumber = centerIdentificationNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ProcurementCenterDTO procurementCenterDTO = (ProcurementCenterDTO) o;
        if (procurementCenterDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), procurementCenterDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ProcurementCenterDTO{" +
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
