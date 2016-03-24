package com.pack.service;

import org.springframework.stereotype.Service;

@Service
public class SearchServiceImpl implements SearchService {

	@Override
	public String createURL(String q, String startdate, String enddate) {
		// TODO Auto-generated method stub
		String url = "https://twitter.com/i/search/timeline?vertical=news"
				+ "&q="+q;
		return url;
	}

	

}
