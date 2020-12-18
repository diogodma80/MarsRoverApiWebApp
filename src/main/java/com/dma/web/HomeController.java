package com.dma.web;

import java.lang.reflect.InvocationTargetException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.dma.dto.HomeDto;
import com.dma.repository.MarsPreferences;
import com.dma.response.MarsRoverApiResponse;
import com.dma.service.MarsRoverApiService;

@Controller
public class HomeController {
	
	@Autowired
	private MarsRoverApiService roverService;

	@GetMapping("/")
	public String getHomeView(Model model, HomeDto homeDto) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		// if the request param is empty, then set the default value to opportunity
		if(StringUtils.isEmpty(homeDto.getMarsApiRoverData())) {
			homeDto.setMarsApiRoverData("Opportunity");
		}
		
		if(homeDto.getMarsSol() == null) {
			homeDto.setMarsSol(1);
		}
		
		MarsRoverApiResponse roverResponse = roverService.getRoverData(homeDto);
		
		model.addAttribute("roverResponse", roverResponse);
		model.addAttribute("homeDto", homeDto);
		model.addAttribute("validCameras", roverService.getValidCameras().get(homeDto.getMarsApiRoverData()));
		
		return "index";
	}
	
	@PostMapping("/")
	public String postHomeView(HomeDto homeDto) {
		System.out.println(homeDto);
		
		roverService.save(convertToEntity(homeDto));

		return "redirect:/";
	}

	// utility method to convert HomeDto to an entity class
	private MarsPreferences convertToEntity(HomeDto homeDto) {

		ModelMapper modelMapper = new ModelMapper();
		
		return modelMapper.map(homeDto, MarsPreferences.class);		
	}
}
