package com.dma.service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.dma.dto.HomeDto;
import com.dma.repository.MarsPreferences;
import com.dma.repository.PreferencesRepository;
import com.dma.response.MarsRoverApiResponse;
import com.dma.response.Photo;

@Service
public class MarsRoverApiService {

	private static String API_KEY = "BzevhBdgtUbMMaTJ01k6XTmwtf9oi1cdI4jRaaQd";

	private Map<String, List<String>> validCameras = new HashMap<>();

	@Autowired
	PreferencesRepository preferencesRepository;

	public MarsRoverApiService() {
		validCameras.put("Opportunity", Arrays.asList("FHAZ", "RHAZ", "NAVCAM", "PANCAM", "MINITES"));
		validCameras.put("Curiosity", Arrays.asList("FHAZ", "RHAZ", "MAST", "CHEMCAM", "MAHLI", "MARDI", "NAVCAM"));
		validCameras.put("Spirit", Arrays.asList("FHAZ", "RHAZ", "NAVCAM", "PANCAM", "MINITES"));
	}

	public MarsRoverApiResponse getRoverData(HomeDto homeDto)
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		RestTemplate restTemplate = new RestTemplate();

		List<String> apiUrlEndpoints = getUrlEndpoints(homeDto);
		List<Photo> photos = new ArrayList<>();
		MarsRoverApiResponse response = new MarsRoverApiResponse();

		apiUrlEndpoints.stream().forEach(url -> {
			MarsRoverApiResponse apiResponse = restTemplate.getForObject(url, MarsRoverApiResponse.class);
			photos.addAll(apiResponse.getPhotos());
		});

		response.setPhotos(photos);

		return response;
	}

	public List<String> getUrlEndpoints(HomeDto homeDto)
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {

		List<String> urls = new ArrayList<String>();

		Method[] methods = homeDto.getClass().getMethods();

		// iterate through the methods within the class HomeDto
		for (Method method : methods) {
			// if the name of the camera exists
			if (method.getName().indexOf("getCamera") > -1 && Boolean.TRUE.equals(method.invoke(homeDto))) {
				String cameraName = method.getName().split("getCamera")[1].toUpperCase();
				if (validCameras.get(homeDto.getMarsApiRoverData()).contains(cameraName)) {
					urls.add("https://api.nasa.gov/mars-photos/api/v1/rovers/" + homeDto.getMarsApiRoverData()
							+ "/photos?sol=" + homeDto.getMarsSol() + "&api_key=" + API_KEY + "&camera=" + cameraName);
				}
			}
		}
		return urls;
	}

	public Map<String, List<String>> getValidCameras() {

		return validCameras;
	}

	public MarsPreferences save(MarsPreferences preferences) {

		return preferencesRepository.saveAndFlush(preferences);

		// return preferencesRepository.save(preferences);

	}

	public MarsPreferences findByUserId(Long userId) {
		return preferencesRepository.findByUserId(userId);
	}

	// utility method to convert HomeDto to an entity class
	public MarsPreferences convertToEntity(HomeDto homeDto) {

		ModelMapper modelMapper = new ModelMapper();

		return modelMapper.map(homeDto, MarsPreferences.class);
	}

	// utility method to convert entity class to a dto
	public HomeDto convertToDto(MarsPreferences preferences) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(preferences, HomeDto.class);
	}
}
