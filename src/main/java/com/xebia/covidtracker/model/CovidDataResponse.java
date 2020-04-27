package com.xebia.covidtracker.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * CovidDetailsResponse
 * @author ankit.mishra@xebia.com
 *
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CovidDataResponse {
	
	public CovidDataResponse(String msg) {
		super();
		this.msg = msg;
	}

	
	public CovidDataResponse() {
		super();
	}


	String msg;
	@JsonProperty("Tested")
	Long tested;
	@JsonProperty("Confirmed")
	Long confirmed;
	@JsonProperty("Active")
	Long active;
	@JsonProperty("Recovered")
	Long recovered;
	@JsonProperty("Dead")
	Long dead;
	

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Long getTested() {
		return tested;
	}

	public void setTested(Long tested) {
		this.tested = tested;
	}

	public Long getConfirmed() {
		return confirmed;
	}

	public void setConfirmed(Long confirmed) {
		this.confirmed = confirmed;
	}

	public Long getActive() {
		return active;
	}

	public void setActive(Long active) {
		this.active = active;
	}

	public Long getRecovered() {
		return recovered;
	}

	public void setRecovered(Long recovered) {
		this.recovered = recovered;
	}

	public Long getDead() {
		return dead;
	}

	public void setDead(Long dead) {
		this.dead = dead;
	}
	
}
