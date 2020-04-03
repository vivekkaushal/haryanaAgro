package com.vivek.myapp.repository;
import com.vivek.myapp.domain.Farmer;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Farmer entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FarmerRepository extends JpaRepository<Farmer, Long> {

}
