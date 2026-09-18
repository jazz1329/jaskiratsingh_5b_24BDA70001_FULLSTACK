package com.example.springboot.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springboot.response.ApiResponse;

@RestController
@RequestMapping("/api")
public class ScheduleController {

	@GetMapping("/schedule")
	public ApiResponse<String> schedule() {
		return ApiResponse.success(HttpStatus.OK.value(), "Schedule endpoint executed successfully", "Success");
	}
}