package com.xebia.covidtracker.service;

import java.util.List;

import com.xebia.covidtracker.entity.CovidDataEntity;

/**
 * Covid Service Interface
 * @author ankitm
 *
 */
public interface CovidDataService {

	void loadIntialData(List<CovidDataEntity> entityList);

	List<CovidDataEntity> findAll();

	CovidDataEntity findByLocation(String location);

	void save(CovidDataEntity entityData);

	List<CovidDataEntity> findAll(List<String> locations, String type, String selected);

	List<CovidDataEntity> findAllByTypeAndSelect(String type, String selected);

	List<CovidDataEntity> findAllByLocations(List<String> locations);

}
