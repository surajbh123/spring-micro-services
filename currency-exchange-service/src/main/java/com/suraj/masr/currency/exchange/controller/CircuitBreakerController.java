package com.suraj.masr.currency.exchange.controller;

import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;

@RestController
@RequestMapping(path = "/test")
public class CircuitBreakerController {

	private Logger logger = org.slf4j.LoggerFactory
			.getLogger(CircuitBreakerController.class);

	@GetMapping(path = "/sample")
//	@Retry(name = "sample-api", fallbackMethod = "hardCodeResponce")
	@CircuitBreaker(name = "default" , fallbackMethod = "hardCodeResponce")
	@RateLimiter(name = "default" , fallbackMethod = "hardCodeResponce")
//	@Bulkhead(name = "default") concurrent call
	public ResponseEntity<String> sampleApi() {

		logger.info("api call received ======");

		ResponseEntity<String> forEntity = new RestTemplate()
				.getForEntity("http://localhost:8000/testing", String.class);

		return new ResponseEntity<>(forEntity.getBody(), HttpStatus.OK);
	}

	public ResponseEntity<String> hardCodeResponce(Throwable ex) {
		logger.info("fallback method called ======");
		return new ResponseEntity<>("Fallback res", HttpStatus.OK);
	}
}
