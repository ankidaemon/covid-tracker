package com.xebia.covidtracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xebia.covidtracker.facade.CovidDataFacade;
import com.xebia.covidtracker.model.CovidDataRequest;
import com.xebia.covidtracker.model.CovidDataRequestList;
import com.xebia.covidtracker.model.CovidDataResponse;

/**
 * CovidRestController
 * @author ankitm
 */

@RestController
public class CovidRestController {
	
	@Autowired
	private CovidDataFacade covidDataFacade;
		
	@PutMapping(value="/initialData",consumes = "text/csv")
	public ResponseEntity<CovidDataResponse> initialData(@RequestBody CovidDataRequestList covidReq) {
		covidDataFacade.loadIntialData(covidReq);
		return new ResponseEntity<>(new CovidDataResponse("Data Uploaded Succesfully!"),HttpStatus.CREATED);
	}
	
	@PatchMapping(value="updateCityData/{location}",consumes = "application/json")
	public ResponseEntity<CovidDataResponse> updateCityData(@RequestBody CovidDataRequest covidReq,@PathVariable("location") String location) {
		CovidDataResponse reponse=covidDataFacade.updateCityData(covidReq,location);
		return new ResponseEntity<>(reponse,HttpStatus.OK);
	}

	@GetMapping("/findAll")
	public CovidDataRequestList findAll(@RequestParam(name = "location", required = false) List<String> locations,
			@RequestParam(name="type",required = false) String type,@RequestParam(name = "selected",required = false) String selected) {
		return covidDataFacade.findAll(locations,type,selected);
	}
}
