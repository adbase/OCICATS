package com.pack.interceptor;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.util.StopWatch;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class PerformanceMonitorInterceptor implements HandlerInterceptor{

	ThreadLocal<StopWatch> stopWatchLocal = new ThreadLocal<>();
	Logger logger = Logger.getLogger(this.getClass());
	
	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		StopWatch stopWatch = stopWatchLocal.get();
		stopWatch.stop();
		
		logger.info("Total time taken for processing: " + stopWatch.getTotalTimeMillis() + "ms");
		stopWatchLocal.set(null);
		logger.info("===================================================");
	}
	

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub
		logger.info("Request processing ended on " + getCurrentTime());
	}

	
	@Override
	public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2) throws Exception {
		// TODO Auto-generated method stub
		StopWatch stopWatch = new StopWatch(arg2.toString());
		stopWatch.start(arg2.toString());
		stopWatchLocal.set(stopWatch);
		
		logger.info("Accessing URL path: " + getURLPath(arg0));
		logger.info("Request processing started on: " + getCurrentTime());
		return true;
	}
	
	
	private String getURLPath(HttpServletRequest request) {
		// TODO Auto-generated method stub
		String currentPath = request.getRequestURI();
		String queryString = request.getQueryString();
		queryString = queryString == null ? "" : "?" + queryString;
		return currentPath + queryString;
	}
	
	private String getCurrentTime() {
		// TODO Auto-generated method stub
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy 'at' hh:mm:ss");
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(System.currentTimeMillis());
		return formatter.format(calendar.getTime());
	}

}
