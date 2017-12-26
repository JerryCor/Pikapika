package com.pikapika.app.config;

import java.util.Arrays;

import org.apache.cxf.Bus;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;

import com.pikapika.app.mapper.BbsMapper;
import com.pikapika.app.mb.service.impl.MbBbsServiceImpl;

@Configuration
public class CxfConfiguration {
	@Autowired
	private BbsMapper bbsMapper;
	
	@Autowired
    private JavaMailSender mailSender; //自动注入的Bean
	
	@Value("${spring.mail.username}")
    private String sender; //读取配置文件中的参数
	
	@Autowired
    private Bus bus;
	
	@Bean
	public Server rsServer() {
		JAXRSServerFactoryBean endpoint = new JAXRSServerFactoryBean();
		endpoint.setBus(bus);
		endpoint.setAddress("/ws");
		MbBbsServiceImpl mbBbsServiceImpl = new MbBbsServiceImpl(bbsMapper, mailSender);
		mbBbsServiceImpl.setSender(sender);
		// Register 2 JAX-RS root resources supporting "/sayHello/{id}" and "/sayHello2/{id}" relative paths
		endpoint.setServiceBeans(Arrays.<Object>asList(mbBbsServiceImpl));
		//endpoint.setServiceBeans(Arrays.<Object>asList(bbsMapper));
		//endpoint.setServiceClass(MbBbsService.class);
		return endpoint.create();
	}
}
