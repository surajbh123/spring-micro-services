package com.suraj.masr.api.gateway;

import java.util.function.Function;

import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.Buildable;
import org.springframework.cloud.gateway.route.builder.PredicateSpec;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfiguration {

	@Bean
	public RouteLocator gatewayrouter(RouteLocatorBuilder routeLocatorBuilder) {

		return routeLocatorBuilder.routes().route(p -> p.path("/get")
				.filters(h -> h.addRequestHeader("name", "microservice--hh")
						.addRequestParameter("param", "__myValue"))
				.uri("http://httpbin.org:80"))
				.route(p -> p.path("/currency-exchange/**")
						.uri("lb://currency-exchange"))
				.route(p -> p.path("/currency-conversion/**")
						.uri("lb://currency-conversion"))
				
//				.route(p -> p.path("/currency-conversion-new/**")
//						 .filters(f -> 
//						 f.rewritePath("/currency-conversion-new/", "/currency-conversion/"))
//						.uri("lb://currency-conversion"))
				
				.route(p -> p.path("/currency-conversion-new/**")
						 .filters(f -> 
						 f.rewritePath("/currency-conversion-new/(?<segment>.*)", "/currency-conversion/${segment}"))
						.uri("lb://currency-conversion"))
				.build();
	}
}
