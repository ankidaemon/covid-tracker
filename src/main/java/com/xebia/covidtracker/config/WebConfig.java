package com.xebia.covidtracker.config;

import java.util.List;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * WebConfig
 * @author ankit.mishra@xebia.com
 */
@EnableWebMvc
@Configuration
@ComponentScan("com.xebia.covidtracker")
public class WebConfig implements WebMvcConfigurer {
 
  @Override
  public void extendMessageConverters (List<HttpMessageConverter<?>> converters) {
      converters.add(new CsvHttpMessageConverter<>());
  }
    
}