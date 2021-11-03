package com.suraj.masr.currency.conversion.controller;

import java.math.BigDecimal;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.suraj.masr.currency.conversion.service.CurrencyConversion;
import com.suraj.masr.currency.conversion.service.CurrencyExchangeProxy;

@RestController
@RequestMapping(path = "/currency-conversion")
public class CurrencyConversionController {

	@Autowired
	private Environment environment;

	@Autowired
	private CurrencyExchangeProxy proxy;

	@GetMapping(path = "/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversion calculateCurrencyConversion(
			@PathVariable String from, @PathVariable String to,
			@PathVariable BigDecimal quantity) {
		HashMap<String, String> uriVariables = new HashMap<>();
		uriVariables.put("from", from);
		uriVariables.put("to", to);
		CurrencyConversion currencyConversion = new RestTemplate().getForEntity(
				"http://localhost:8000/currency-exchange/from/{from}/to/{to}",
				CurrencyConversion.class, uriVariables).getBody();
		String port = environment.getProperty("server.port");
		BigDecimal totalCalculatedAmount = currencyConversion
				.getConversionMultiple().multiply(quantity);
		return new CurrencyConversion(currencyConversion.getId(), from, to,
				currencyConversion.getConversionMultiple(), quantity,
				totalCalculatedAmount, port+" rest-template");
	}

	@GetMapping(path = "/feign/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversion calculateCurrencyConversionByFeign(
			@PathVariable String from, @PathVariable String to,
			@PathVariable BigDecimal quantity) {

		CurrencyConversion currencyConversion = proxy.retriveExchangeValue(from,
				to);
		BigDecimal totalCalculatedAmount = currencyConversion
				.getConversionMultiple().multiply(quantity);
		String port = environment.getProperty("server.port");
		return new CurrencyConversion(currencyConversion.getId(), from, to,
				currencyConversion.getConversionMultiple(), quantity,
				totalCalculatedAmount, port+" feign-client /, exchangePort "+currencyConversion.getEnv());
	}

}
