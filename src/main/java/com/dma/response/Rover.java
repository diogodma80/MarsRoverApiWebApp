package com.dma.response;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Rover {

	private Long id;
	private String name;
	@JsonProperty(value = "landing_date")
	private Date landingDate;
	@JsonProperty(value = "launch_date")
	private Date launchDate;
	private String status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getLandingDate() {
		return landingDate;
	}

	public void setLandingDate(Date landingDate) {
		this.landingDate = landingDate;
	}

	public Date getLaunchDate() {
		return launchDate;
	}

	public void setLaunchDate(Date launchDate) {
		this.launchDate = launchDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Rover [id=" + id + ", name=" + name + ", landingDate=" + landingDate + ", launchDate=" + launchDate
				+ ", status=" + status + "]";
	}

}
