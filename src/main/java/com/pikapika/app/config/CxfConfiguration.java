package com.pikapika.app.config;

import java.util.Arrays;

import org.apache.cxf.Bus;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.pikapika.app.mapper.BbsMapper;
import com.pikapika.app.mb.service.impl.MbBbsServiceImpl;

@Configuration
public class CxfConfiguration {
	@Autowired
	private BbsMapper bbsMapper;
	
	@Autowired
    private Bus bus;
	
	@Bean
	public Server rsServer() {
		JAXRSServerFactoryBean endpoint = new JAXRSServerFactoryBean();
		endpoint.setBus(bus);
		endpoint.setAddress("/ws");
		// Register 2 JAX-RS root resources supporting "/sayHello/{id}" and "/sayHello2/{id}" relative paths
		endpoint.setServiceBeans(Arrays.<Object>asList(new MbBbsServiceImpl(bbsMapper)));
		//endpoint.setServiceBeans(Arrays.<Object>asList(bbsMapper));
		//endpoint.setServiceClass(MbBbsService.class);
		return endpoint.create();
	}
}
