package com.suraj.masr.currency.conversion.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@FeignClient(name="currency-exchange",url = "localhost:8000",path = "/currency-exchange")
@FeignClient(name="currency-exchange",path = "/currency-exchange")
public interface CurrencyExchangeProxy {

	@GetMapping(path = "/from/{from}/to/{to}")
	public CurrencyConversion retriveExchangeValue(@PathVariable(value = "from") String from,
			@PathVariable(value = "to") String to);
	
}
