package com.pikapika.app.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import org.springframework.core.annotation.Order;
/**
 * Spring boot添加自定义filter
 * 与org.springframework.web.filter.CharacterEncodingFilter
 * 重复？
 * @author issuser
 *
 */
@Order(60)
@WebFilter(filterName="EncodeFiler", urlPatterns="/*")
public class EncodeFilter implements Filter {
	
	public static final String ENCODE = "UTF-8";
	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String encode_req = request.getCharacterEncoding();
		if( encode_req == null || !ENCODE.equals(encode_req.toLowerCase())){
			request.setCharacterEncoding(ENCODE);
		}
		
		String encode_rep = response.getCharacterEncoding();
		if( encode_rep == null || !ENCODE.equals(encode_rep.toLowerCase())){
			response.setCharacterEncoding(ENCODE);
		}
		
		chain.doFilter(request, response);

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
