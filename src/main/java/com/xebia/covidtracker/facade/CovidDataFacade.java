package com.xebia.covidtracker.facade;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xebia.covidtracker.entity.CovidDataEntity;
import com.xebia.covidtracker.exception.InvalidLocationException;
import com.xebia.covidtracker.model.CovidDataRequest;
import com.xebia.covidtracker.model.CovidDataRequestList;
import com.xebia.covidtracker.model.CovidDataResponse;
import com.xebia.covidtracker.service.CovidDataService;

/**
 * CovidDataFacade - Facade pattern
 * @author ankit.mishra@xebia.com
 *
 */
@Component
public class CovidDataFacade {
	
	@Autowired
	private CovidDataService covidDataService;

	public void loadIntialData(CovidDataRequestList covidReq) {
		List<CovidDataEntity> entityList = listCopyAdaptor(covidReq.getList(), new ArrayList<CovidDataEntity>(),CovidDataEntity.class);
		covidDataService.loadIntialData(entityList);
	}

	private <P, T> List<P> listCopyAdaptor(List<T> origin,List<P> dest, Class<P> destClass) {
		P destObj = null;
		for(T data: origin) {
			try {
				destObj = destClass.newInstance();
				BeanUtils.copyProperties(destObj, data);
			} catch (IllegalAccessException | InvocationTargetException | InstantiationException e) {}
			dest.add(destObj);
		}
		return dest;
	}

	public CovidDataRequestList findAll(List<String> locations, String type, String selected) {
		List<CovidDataEntity> entityList = null;
		if(CollectionUtils.isNotEmpty(locations) && type!=null && selected!=null) {
			entityList = covidDataService.findAll(locations,type,selected);
		}else if(CollectionUtils.isEmpty(locations) && type!=null && selected!=null) {
			entityList = covidDataService.findAllByTypeAndSelect(type,selected);
		}else if(CollectionUtils.isNotEmpty(locations)) {
			entityList = covidDataService.findAllByLocations(locations);
		}else {
			entityList = covidDataService.findAll();
		}
		CovidDataRequestList dataList=new CovidDataRequestList();
		dataList.setList(listCopyAdaptor(entityList, new ArrayList<CovidDataRequest>(), CovidDataRequest.class));
		return dataList;
	}

	public CovidDataResponse updateCityData(CovidDataRequest covidReq, String location) {
		CovidDataEntity entityData = covidDataService.findByLocation(location);
		CovidDataResponse covidResponse = new CovidDataResponse();
		if(entityData==null) {
			throw new InvalidLocationException();
		}else {
			entityData.setActive(entityData.getActive()+covidReq.getActive());
			entityData.setConfirmed(entityData.getConfirmed()+covidReq.getConfirmed());
			entityData.setDead(entityData.getDead()+covidReq.getDead());
			entityData.setRecovered(entityData.getRecovered()+covidReq.getRecovered());
			entityData.setTested(entityData.getTested()+covidReq.getTested());
			covidDataService.save(entityData);
			try {
				BeanUtils.copyProperties(covidResponse, entityData);
			} catch (IllegalAccessException | InvocationTargetException e) {}
		}
		return covidResponse;
	}

}
