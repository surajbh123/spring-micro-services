package com.suraj.masr.limits.service.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("limits-service")
public class LimitsConfiguration {
	private Integer minimum;
	private Integer maximum;
	
	public Integer getMinimum() {
		return minimum;
	}
	public void setMinimum(Integer minimum) {
		this.minimum = minimum;
	}
	public Integer getMaximum() {
		return maximum;
	}
	public void setMaximum(Integer maximum) {
		this.maximum = maximum;
	}
	
	
}
