package com.embl.api.personservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author sajjohn
 * Swagger configuration for API docs
 */
@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

	
	/**
	 * @return
	 */
	/**
	 * @return
	 */
	@Bean
	public Docket api(){
		return new Docket(DocumentationType.SWAGGER_2)
			.select()
			.apis(RequestHandlerSelectors.basePackage("com.embl.api.personservice"))
			.paths(PathSelectors.any())
			.build();
	}
	
}
