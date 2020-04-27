package com.xebia.covidtracker.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * CovidDataEntity
 * @author ankitm
 *
 */
@Entity
public class CovidDataEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	Long id;
	String location;
	Long tested;
	Long confirmed;
	Long active;
	Long recovered;
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
	
	public CovidDataEntity(Long active) {
		super();
		this.active = active;
	}
	public CovidDataEntity() {
		super();
	}
	
}

