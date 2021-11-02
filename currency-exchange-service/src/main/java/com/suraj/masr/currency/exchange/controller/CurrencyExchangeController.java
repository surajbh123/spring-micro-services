package com.suraj.masr.currency.exchange.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.suraj.masr.currency.exchange.service.CurrencyExchange;

@RestController
@RequestMapping(path = "/currency-exchange")
public class CurrencyExchangeController {
	
	@Autowired
	private Environment environment;
	
	@GetMapping(path = "/{from}/to/{to}")
	public CurrencyExchange retriveExchangeValue(@PathVariable String from,@PathVariable String to) {
		String port = environment.getProperty("server.port");
//		String env = environment.getProperty("env");
		return new CurrencyExchange(1000L, from, to, BigDecimal.valueOf(50L),port);
	}

}
