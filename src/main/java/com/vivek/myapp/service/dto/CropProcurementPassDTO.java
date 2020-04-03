package com.vivek.myapp.service.dto;
import java.time.Instant;
import java.io.Serializable;
import java.util.Objects;
import com.vivek.myapp.domain.enumeration.CropType;
import com.vivek.myapp.domain.enumeration.ExpectedYieldUnit;
import com.vivek.myapp.domain.enumeration.PassStatus;

/**
 * A DTO for the {@link com.vivek.myapp.domain.CropProcurementPass} entity.
 */
public class CropProcurementPassDTO implements Serializable {

    private Long id;

    private Instant cropDate;

    private CropType cropType;

    private String expectedYeild;

    private ExpectedYieldUnit expectedYeildUnits;

    private Instant procurementDate;

    private PassStatus passStatus;

    private Instant createdAt;

    private Instant modifiedAt;


    private Long procurementCenterId;

    private Long farmerId;

    private Long passApprovedById;

    private Long createdById;

    private Long modifiedById;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getCropDate() {
        return cropDate;
    }

    public void setCropDate(Instant cropDate) {
        this.cropDate = cropDate;
    }

    public CropType getCropType() {
        return cropType;
    }

    public void setCropType(CropType cropType) {
        this.cropType = cropType;
    }

    public String getExpectedYeild() {
        return expectedYeild;
    }

    public void setExpectedYeild(String expectedYeild) {
        this.expectedYeild = expectedYeild;
    }

    public ExpectedYieldUnit getExpectedYeildUnits() {
        return expectedYeildUnits;
    }

    public void setExpectedYeildUnits(ExpectedYieldUnit expectedYeildUnits) {
        this.expectedYeildUnits = expectedYeildUnits;
    }

    public Instant getProcurementDate() {
        return procurementDate;
    }

    public void setProcurementDate(Instant procurementDate) {
        this.procurementDate = procurementDate;
    }

    public PassStatus getPassStatus() {
        return passStatus;
    }

    public void setPassStatus(PassStatus passStatus) {
        this.passStatus = passStatus;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(Instant modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    public Long getProcurementCenterId() {
        return procurementCenterId;
    }

    public void setProcurementCenterId(Long procurementCenterId) {
        this.procurementCenterId = procurementCenterId;
    }

    public Long getFarmerId() {
        return farmerId;
    }

    public void setFarmerId(Long farmerId) {
        this.farmerId = farmerId;
    }

    public Long getPassApprovedById() {
        return passApprovedById;
    }

    public void setPassApprovedById(Long employeeId) {
        this.passApprovedById = employeeId;
    }

    public Long getCreatedById() {
        return createdById;
    }

    public void setCreatedById(Long employeeId) {
        this.createdById = employeeId;
    }

    public Long getModifiedById() {
        return modifiedById;
    }

    public void setModifiedById(Long employeeId) {
        this.modifiedById = employeeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CropProcurementPassDTO cropProcurementPassDTO = (CropProcurementPassDTO) o;
        if (cropProcurementPassDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), cropProcurementPassDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CropProcurementPassDTO{" +
            "id=" + getId() +
            ", cropDate='" + getCropDate() + "'" +
            ", cropType='" + getCropType() + "'" +
            ", expectedYeild='" + getExpectedYeild() + "'" +
            ", expectedYeildUnits='" + getExpectedYeildUnits() + "'" +
            ", procurementDate='" + getProcurementDate() + "'" +
            ", passStatus='" + getPassStatus() + "'" +
            ", createdAt='" + getCreatedAt() + "'" +
            ", modifiedAt='" + getModifiedAt() + "'" +
            ", procurementCenter=" + getProcurementCenterId() +
            ", farmer=" + getFarmerId() +
            ", passApprovedBy=" + getPassApprovedById() +
            ", createdBy=" + getCreatedById() +
            ", modifiedBy=" + getModifiedById() +
            "}";
    }
}
