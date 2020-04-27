package com.xebia.covidtracker.repo;

import java.util.List;

import com.xebia.covidtracker.entity.CovidDataEntity;

public interface CovidFilterRepo {

	List<CovidDataEntity> findAll(List<String> locations, String type, String selected);

	List<CovidDataEntity> findAll(String type, String selected);

	List<CovidDataEntity> findAll(List<String> locations);

}
