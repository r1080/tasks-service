package edu.tlabs.task.filter;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Iterator;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component

public class RequestResponseLogFilter implements Filter {

	@SuppressWarnings("unused")
	private static final Logger LOG = LoggerFactory.getLogger(RequestResponseLogFilter.class);

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		//LOG.info("Logging Request  {} : {}", req.getMethod(), req.getRequestURI());
		
		 Enumeration<String> headerNames = req.getHeaderNames();

		    if (headerNames != null) {
		            while (headerNames.hasMoreElements()) {
		                    System.out.println("Header: " + req.getHeader(headerNames.nextElement()));
		            }
		    }
		
		chain.doFilter(request, response);
		//LOG.info("Logging Response :{}", res.getContentType());
		
		
		 Iterator<String> responseHeaderIterator = res.getHeaderNames().iterator();
		 
		 while(responseHeaderIterator.hasNext()) {
			// String headerType = responseHeaderIterator.next();
			// LOG.info(" Header :{} : {}",headerType, res.getHeader(headerType));
		 }

		    

	}

}
