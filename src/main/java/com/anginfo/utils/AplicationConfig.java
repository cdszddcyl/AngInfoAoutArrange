package com.anginfo.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:/spring/config.properties")
public class AplicationConfig {
	 @Value("${rootPath}")  String rootPath ;
}
