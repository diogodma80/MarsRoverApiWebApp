package com.dma.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.dma.dto.HomeDto;
import com.dma.response.MarsRoverApiResponse;
import com.dma.response.Photo;

@Service
public class MarsRoverApiService {
	
	private static String API_KEY = "BzevhBdgtUbMMaTJ01k6XTmwtf9oi1cdI4jRaaQd";

	public MarsRoverApiResponse getRoverData(HomeDto homeDto) {
		RestTemplate restTemplate = new RestTemplate();
		
		List<String> apiUrlEndpoints = getUrlEndpoints(homeDto);
		List<Photo> photos = new ArrayList<>();
		MarsRoverApiResponse response = new MarsRoverApiResponse();
		
		apiUrlEndpoints.stream()
			.forEach(url -> {
				MarsRoverApiResponse apiResponse = restTemplate.getForObject(url,  MarsRoverApiResponse.class);
				photos.addAll(apiResponse.getPhotos());
		});
	
		response.setPhotos(photos);
		
		return response;
	}
	
	public List<String> getUrlEndpoints(HomeDto homeDto) {
		
		List<String> urls = new ArrayList<String>();
		
		if(Boolean.TRUE.equals(homeDto.getCameraFhaz())) {
			urls.add("https://api.nasa.gov/mars-photos/api/v1/rovers/"+ 
					homeDto.getMarsApiRoverData() +"/photos?sol="+ 
					homeDto.getMarsSol() +
					"&api_key=" +API_KEY+ "&camera=FHAZ");
		} 

		if(Boolean.TRUE.equals(homeDto.getCameraChemcam()) && "curiosity".equalsIgnoreCase(homeDto.getMarsApiRoverData())) {
			urls.add("https://api.nasa.gov/mars-photos/api/v1/rovers/"+ 
					homeDto.getMarsApiRoverData() +"/photos?sol="+ 
					homeDto.getMarsSol() +
					"&api_key=" +API_KEY+ "&camera=CHEM");
		} 

		if(Boolean.TRUE.equals(homeDto.getCameraMahli()) && "curiosity".equalsIgnoreCase(homeDto.getMarsApiRoverData())) {
			urls.add("https://api.nasa.gov/mars-photos/api/v1/rovers/"+ 
					homeDto.getMarsApiRoverData() +"/photos?sol="+ 
					homeDto.getMarsSol() +
					"&api_key=" +API_KEY+ "&camera=MAHLI");
		} 

		if(Boolean.TRUE.equals(homeDto.getCameraMardi()) && "curiosity".equalsIgnoreCase(homeDto.getMarsApiRoverData())) {
			urls.add("https://api.nasa.gov/mars-photos/api/v1/rovers/"+ 
					homeDto.getMarsApiRoverData() +"/photos?sol="+ 
					homeDto.getMarsSol() +
					"&api_key=" +API_KEY+ "&camera=MARDI");
		} 

		if(Boolean.TRUE.equals(homeDto.getCameraMast()) && "curiosity".equalsIgnoreCase(homeDto.getMarsApiRoverData())) {
			urls.add("https://api.nasa.gov/mars-photos/api/v1/rovers/"+ 
					homeDto.getMarsApiRoverData() +"/photos?sol="+ 
					homeDto.getMarsSol() +
					"&api_key=" +API_KEY+ "&camera=MAST");
		} 

		if(Boolean.TRUE.equals(homeDto.getCameraMinites())) {
			urls.add("https://api.nasa.gov/mars-photos/api/v1/rovers/"+ 
					homeDto.getMarsApiRoverData() +"/photos?sol="+ 
					homeDto.getMarsSol() +
					"&api_key=" +API_KEY+ "&camera=MINITES");
		} 

		if(Boolean.TRUE.equals(homeDto.getCameraNavcam()) && !"curiosity".equalsIgnoreCase(homeDto.getMarsApiRoverData())) {
			urls.add("https://api.nasa.gov/mars-photos/api/v1/rovers/"+ 
					homeDto.getMarsApiRoverData() +"/photos?sol="+ 
					homeDto.getMarsSol() +
					"&api_key=" +API_KEY+ "&camera=NAVCAM");
		} 

		if(Boolean.TRUE.equals(homeDto.getCameraPancam()) && !"curiosity".equalsIgnoreCase(homeDto.getMarsApiRoverData())) {
			urls.add("https://api.nasa.gov/mars-photos/api/v1/rovers/"+ 
					homeDto.getMarsApiRoverData() +"/photos?sol="+ 
					homeDto.getMarsSol() +
					"&api_key=" +API_KEY+ "&camera=PANCAM");
		} 

		if(Boolean.TRUE.equals(homeDto.getCameraRhaz())) {
			urls.add("https://api.nasa.gov/mars-photos/api/v1/rovers/"+ 
					homeDto.getMarsApiRoverData() +"/photos?sol="+ 
					homeDto.getMarsSol() +
					"&api_key=" +API_KEY+ "&camera=RHAZ"); 
		}
		
		return urls;
	}
	
}
