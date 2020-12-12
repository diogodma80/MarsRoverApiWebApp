package com.dma.response;

import java.util.ArrayList;
import java.util.List;

public class MarsRoverApiResponse {

	List<Photo> photos = new ArrayList<>();

	public List<Photo> getPhotos() {
		return photos;
	}

	public void setPhotos(List<Photo> photos) {
		this.photos = photos;
	}

	@Override
	public String toString() {
		return "MarsRoverApiResponse [photos=" + photos + "]";
	}

}
