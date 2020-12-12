package com.dma;

import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.dma.response.MarsRoverApiResponse;

public class MarsRoverApiTest {

	@Test
	public void smallTest() {
		RestTemplate restTemplate = new RestTemplate();
		
		ResponseEntity<MarsRoverApiResponse> response = restTemplate.getForEntity("https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/photos?sol=1000&api_key=DEMO_KEY", 
				MarsRoverApiResponse.class);
		System.out.println(response.getBody());
	}
}
