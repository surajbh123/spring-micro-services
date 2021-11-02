package com.suraj.masr.limits.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.suraj.masr.limits.service.bean.Limits;
import com.suraj.masr.limits.service.configuration.LimitsConfiguration;

@RestController
@RequestMapping(path = "/limits")
public class LimitController {
	
	@Autowired
	private LimitsConfiguration limitsConfiguration;

	@GetMapping
	public Limits getLimits() {
		return new Limits(limitsConfiguration.getMinimum(), limitsConfiguration.getMaximum());
	}
}
