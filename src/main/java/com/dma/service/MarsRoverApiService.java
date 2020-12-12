package com.dma.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.dma.response.MarsRoverApiResponse;

@Service
public class MarsRoverApiService {
	
	private static String API_KEY = "BzevhBdgtUbMMaTJ01k6XTmwtf9oi1cdI4jRaaQd";

	public MarsRoverApiResponse getRoverData(String roverType) {
		RestTemplate restTemplate = new RestTemplate();
		
		ResponseEntity<MarsRoverApiResponse> response = restTemplate.getForEntity("https://api.nasa.gov/mars-photos/api/v1/rovers/"+ roverType +"/photos?sol=2&api_key=" +API_KEY, 
				MarsRoverApiResponse.class);
		
		return response.getBody();
	}
	
}
