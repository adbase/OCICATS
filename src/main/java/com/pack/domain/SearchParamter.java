package com.pack.domain;

import org.springframework.stereotype.Component;

@Component
public class SearchParamter {

	String q;
	String startdate;
	String enddate;
	public String getQ() {
		return q;
	}
	public String getStartdate() {
		return startdate;
	}
	public String getEnddate() {
		return enddate;
	}
	public void setQ(String q) {
		this.q = q;
	}
	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}
	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}

	
	
}
