package com.dma.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dma.dto.HomeDto;
import com.dma.response.MarsRoverApiResponse;
import com.dma.service.MarsRoverApiService;

@Controller
public class HomeController {

	@Autowired
	private MarsRoverApiService roverService;

	@GetMapping("/")
	public String getHomeView(Model model, Long userId) throws Exception {

		HomeDto homeDto = createDefaultHomeDto(userId);

		if(userId == null) {
			// converts dto to entity, saves and returns a dto object
			homeDto = saveHomeDto(homeDto);
		} else {
			// finds the userId and converts to dto object
			homeDto = roverService.convertToDto(roverService.findByUserId(userId));
		}

		MarsRoverApiResponse roverResponse = roverService.getRoverData(homeDto);

		model.addAttribute("roverResponse", roverResponse);
		model.addAttribute("homeDto", homeDto);
		model.addAttribute("validCameras", roverService.getValidCameras().get(homeDto.getMarsApiRoverData()));

		if (!Boolean.TRUE.equals(homeDto.getRememberPreferences()) && userId != null) {
			HomeDto defaultHomeDto = createDefaultHomeDto(userId);
			homeDto = saveHomeDto(defaultHomeDto);
		}
		
		return "index";
	}

	private HomeDto createDefaultHomeDto(Long userId) {
		HomeDto homeDto = new HomeDto();
		homeDto.setMarsApiRoverData("Opportunity");
		homeDto.setMarsSol(1);
		homeDto.setUserId(userId);
		return homeDto;
	}

	@PostMapping("/")
	public String postHomeView(HomeDto homeDto) {
		// homeDto = createDefaultHomeDto(homeDto.getUserId());
		// converts to entity, saves and returns a dto
		homeDto = saveHomeDto(homeDto);
		return "redirect:/?userId=" + homeDto.getUserId();
	}

	private HomeDto saveHomeDto(HomeDto homeDto) {
		return roverService.convertToDto(roverService.save(roverService.convertToEntity(homeDto)));
	}
}
