package com.xebia.covidtracker.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.xebia.covidtracker.entity.CovidDataEntity;

/**
 * CovidDataRepo
 * @author ankit.mishra@xebia.com
 *
 */
@Repository
public interface CovidDataRepo extends JpaRepository<CovidDataEntity, Long>, JpaSpecificationExecutor<CovidDataEntity> {

	CovidDataEntity findByLocationIgnoreCase(String location);

}
