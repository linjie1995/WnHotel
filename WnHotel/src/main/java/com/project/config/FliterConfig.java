package com.project.config;

import javax.servlet.Filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.project.filter.UrlFilter;

@Configuration
public class FliterConfig  {
	@Bean
	public FilterRegistrationBean<Filter> urlFilter() {
		FilterRegistrationBean<Filter> filterBean = new FilterRegistrationBean<Filter>();
		filterBean.setFilter(new UrlFilter());
		filterBean.addUrlPatterns("/*"); //设置拦截路径
		filterBean.setOrder(0); //设置启动级别，值越小级别越高
		
		return filterBean;
		
	}
}
