package com.dma.response;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Photo {
	private Long id;
	private Integer sol;
	private Camera camera;
	@JsonProperty(value = "img_src")
	private String imgSrc;
	@JsonProperty(value = "earth_date")
	private Date earthDate;
	private Rover rover;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getSol() {
		return sol;
	}

	public void setSol(Integer sol) {
		this.sol = sol;
	}

	public Camera getCamera() {
		return camera;
	}

	public void setCamera(Camera camera) {
		this.camera = camera;
	}

	public String getImgSrc() {
		return imgSrc;
	}

	public void setImgSrc(String imgSrc) {
		this.imgSrc = imgSrc;
	}

	public Date getEarthDate() {
		return earthDate;
	}

	public void setEarthDate(Date earthDate) {
		this.earthDate = earthDate;
	}

	public Rover getRover() {
		return rover;
	}

	public void setRover(Rover rover) {
		this.rover = rover;
	}

	@Override
	public String toString() {
		return "Photo [id=" + id + ", sol=" + sol + ", camera=" + camera + ", imgSrc=" + imgSrc + "]";
	}

}
