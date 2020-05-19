package com.luv2code.springsecurity.demo.config;




import java.beans.PropertyVetoException;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages="com.luv2code.springsecurity.demo")
@PropertySource("classpath:persistence-mysql.properties")
public class DemoAppConfig 
{
	
	@Autowired
	private Environment env;
	
	
	//set up a logger
	private Logger logger = Logger.getLogger(getClass().getName());
	@Bean
	public ViewResolver viewResolver()
	{
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/view/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}
	
	@Bean
	public DataSource securityDataSource()
	{
		
		ComboPooledDataSource securityDataSource = new ComboPooledDataSource();
		
		try {
			securityDataSource.setDriverClass(env.getProperty("jdbc.driver"));
		} catch (PropertyVetoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		logger.info(">>>> jdbc.url="+env.getProperty("jdbc.url"));
		logger.info(">>>> jdbc.user="+env.getProperty("jdbc.user"));
		
		securityDataSource.setJdbcUrl(env.getProperty("jdbc.url"));
		securityDataSource.setUser(env.getProperty("jdbc.user"));
		securityDataSource.setPassword(env.getProperty("jdbc.password"));
		
		
		
		securityDataSource.setInitialPoolSize(getInproperty("connection.pool.initialPoolSize"));
		securityDataSource.setMinPoolSize(getInproperty("connection.pool.minPoolSize"));
		securityDataSource.setMaxPoolSize(getInproperty("connection.pool.maxPoolSize"));
		securityDataSource.setMaxIdleTime(getInproperty("connection.pool.maxIdleTime"));
		
		return securityDataSource();
		
		
	}
	private int getInproperty(String propName)
	{
		String propVal= env.getProperty(propName);
		
		int intpropVal= Integer.parseInt(propVal);
		return intpropVal;
	}
	
	
}
