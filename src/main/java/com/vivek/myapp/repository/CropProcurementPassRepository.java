package com.vivek.myapp.repository;
import com.vivek.myapp.domain.CropProcurementPass;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the CropProcurementPass entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CropProcurementPassRepository extends JpaRepository<CropProcurementPass, Long> {

}
