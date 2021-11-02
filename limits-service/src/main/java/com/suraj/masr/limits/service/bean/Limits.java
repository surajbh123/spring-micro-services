package com.suraj.masr.limits.service.bean;

public class Limits {

	private Integer minimum;
	private Integer maximum;
	
	public Limits() {
	}
	
	public Limits(Integer minimum, Integer maximum) {
		super();
		this.minimum = minimum;
		this.maximum = maximum;
	}
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
