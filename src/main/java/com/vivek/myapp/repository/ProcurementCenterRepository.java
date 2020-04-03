package com.vivek.myapp.repository;
import com.vivek.myapp.domain.ProcurementCenter;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ProcurementCenter entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProcurementCenterRepository extends JpaRepository<ProcurementCenter, Long> {

}
