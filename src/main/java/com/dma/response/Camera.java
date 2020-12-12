package com.dma.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Camera {

	private Long id;
	private String name;
	@JsonProperty(value = "rover_id")
	private Long roverId;
	@JsonProperty(value = "full_name")
	private String fullName;

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

	public Long getRoverId() {
		return roverId;
	}

	public void setRoverId(Long roverId) {
		this.roverId = roverId;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
}
