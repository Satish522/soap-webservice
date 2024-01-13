package com.satish.webservice.soap.config;

import java.util.List;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.server.endpoint.adapter.DefaultMethodEndpointAdapter;
import org.springframework.ws.server.endpoint.adapter.method.MethodArgumentResolver;
import org.springframework.ws.soap.addressing.server.AnnotationActionEndpointMapping;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@Configuration
@EnableWs
public class WebserviceConfig extends WsConfigurerAdapter{

	@Override
    public void addArgumentResolvers(List<MethodArgumentResolver> argumentResolvers) {
		argumentResolvers.add(new org.springframework.ws.server.endpoint.adapter.method.dom.DomPayloadMethodProcessor());
	}
	
	@Bean
	public ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet(ApplicationContext context){
		MessageDispatcherServlet servlet = new MessageDispatcherServlet();
		servlet.setApplicationContext(context);
		servlet.setTransformWsdlLocations(true);
		
		return new ServletRegistrationBean<MessageDispatcherServlet>(servlet, "/ws/*");
	}
	
	@Bean(name = "eligibility")
	public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema schema) {
		DefaultWsdl11Definition defaultWsdl11Definition = new DefaultWsdl11Definition();
		defaultWsdl11Definition.setPortTypeName("LoanEligibilityEndpoint");
		defaultWsdl11Definition.setLocationUri("/ws");
		defaultWsdl11Definition.setTargetNamespace("http://www.satish.com/webservice/soap/eligibility");
		defaultWsdl11Definition.setSchema(schema);
		return defaultWsdl11Definition;
	}
	
	@Bean(name = "checkloan")
	public DefaultWsdl11Definition checkLoanWsdl11Definition(XsdSchema schema1) {
		DefaultWsdl11Definition defaultWsdl11Definition = new DefaultWsdl11Definition();
		defaultWsdl11Definition.setPortTypeName("LoanEligibilityEndpoint");
		defaultWsdl11Definition.setLocationUri("/ws");
		defaultWsdl11Definition.setTargetNamespace("http://www.satish.com/webservice/soap/checkloan");
		defaultWsdl11Definition.setSchema(schema1);
		return defaultWsdl11Definition;
	}
	
	@Bean
	public XsdSchema schema() {
		return new SimpleXsdSchema(new ClassPathResource("eligibility.xsd"));
		
	}
	
	@Bean
	public XsdSchema schema1() {
		return new SimpleXsdSchema(new ClassPathResource("checkloan.xsd"));
		
	}
}
