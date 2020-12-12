package com.dma.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dma.response.MarsRoverApiResponse;
import com.dma.service.MarsRoverApiService;

@Controller
public class HomeController {
	
	@Autowired
	private MarsRoverApiService roverService;

	@GetMapping("/")
	public String getHomeView(Model model, @RequestParam(required = false) String marsApiRoverData) {
		
		// if the request param is empty, then set the default value to opportunity
		if(StringUtils.isEmpty(marsApiRoverData)) {
			marsApiRoverData = "opportunity";
		}
		
		MarsRoverApiResponse roverResponse = roverService.getRoverData(marsApiRoverData);
		
		model.addAttribute("roverResponse", roverResponse);
		
		return "index";
	}
}
