package com.xebia.covidtracker.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.opencsv.bean.CsvBindByName;

/**
 * CovidDetailsRequest
 * @author ankit.mishra@xebia.com
 * 
 * @JsonNaming is used since question#2 uses captialization property
 *
 */
@JsonNaming(PropertyNamingStrategy.UpperCamelCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CovidDataRequest {
	
	@CsvBindByName(column="Id")
	Long id;
	@CsvBindByName(column="Location")
	String location;
	@CsvBindByName(column="Tested")
	Long tested;
	@CsvBindByName(column="Confirmed")
	Long confirmed;
	@CsvBindByName(column="Active")
	Long active;
	@CsvBindByName(column="Recovered")
	Long recovered;
	@CsvBindByName(column="Dead")
	Long dead;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Long getTested() {
		return tested==null?0L:tested;
	}

	public void setTested(Long tested) {
		this.tested = tested;
	}

	public Long getConfirmed() {
		return confirmed==null?0L:confirmed;
	}

	public void setConfirmed(Long confirmed) {
		this.confirmed = confirmed;
	}

	public Long getActive() {
		return active==null?0L:active;
	}

	public void setActive(Long active) {
		this.active = active;
	}

	public Long getRecovered() {
		return recovered==null?0L:recovered;
	}

	public void setRecovered(Long recovered) {
		this.recovered = recovered;
	}

	public Long getDead() {
		return dead==null?0L:dead;
	}

	public void setDead(Long dead) {
		this.dead = dead;
	}
	
}
