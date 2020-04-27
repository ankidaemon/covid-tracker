package com.xebia.covidtracker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xebia.covidtracker.entity.CovidDataEntity;
import com.xebia.covidtracker.repo.CovidDataRepo;
import com.xebia.covidtracker.repo.CovidFilterRepo;

/**
 * CovidDataServiceImpl
 * @author ankit.mishra@xebia.com
 *
 */
@Service
public class CovidDataServiceImpl implements CovidDataService {

	@Autowired
	private CovidDataRepo covidDataRepo;
	
	@Autowired
	private CovidFilterRepo covidFilterRepo;

	@Override
	@Transactional
	public void loadIntialData(List<CovidDataEntity> entityList) {
		covidDataRepo.saveAll(entityList);
	}

	@Override
	@Transactional(readOnly = true)
	public List<CovidDataEntity> findAll() {
		return covidDataRepo.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public CovidDataEntity findByLocation(String location) {
		return covidDataRepo.findByLocationIgnoreCase(location);
	}

	@Override
	@Transactional
	public void save(CovidDataEntity entityData) {
		covidDataRepo.save(entityData);
	}

	@Override
	@Transactional(readOnly = true)
	public List<CovidDataEntity> findAll(List<String> locations, String type, String selected) {
		return covidFilterRepo.findAll(locations,type,selected);
	}

	@Override
	@Transactional(readOnly = true)
	public List<CovidDataEntity> findAllByTypeAndSelect(String type, String selected) {
		return covidFilterRepo.findAll(type,selected);
	}

	@Override
	@Transactional(readOnly = true)
	public List<CovidDataEntity> findAllByLocations(List<String> locations) {
		return covidFilterRepo.findAll(locations);
	}

}
