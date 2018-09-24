package com.oodles.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
/*
	@Bean
	public Docket api() {
		List<SecurityScheme> schemeList = new ArrayList<>();
		schemeList.add(new ApiKey(HttpHeaders.AUTHORIZATION, "JWT", "header"));
		return new Docket(DocumentationType.SWAGGER_2).produces(Collections.singleton("application/json"))
				.consumes(Collections.singleton("application/json")).ignoredParameterTypes(Authentication.class)
				.securitySchemes(schemeList).useDefaultResponseMessages(false).select()
				.apis(Predicates.not(RequestHandlerSelectors.basePackage("org.springframework.boot")))
				.paths(PathSelectors.any()).build().apiInfo(apiInfo()).useDefaultResponseMessages(true);
	}

	public ApiInfo apiInfo() {
		final ApiInfoBuilder builder = new ApiInfoBuilder();
		builder.title("Scaffold Exchange Order Validation Service API").version("1.0").license("(C) Copyright Scaffold")
				.description("The API provides a platform to query build Scaffold exchange api");
		return builder.build();
	}

	private ApiKey apiKey() {
		return new ApiKey("authkey", "Authorization", "header");
	}*/
	 @Bean
	    public Docket api() {
		 return new Docket(DocumentationType.SWAGGER_2)  
		          .select()                                  
		          .apis(RequestHandlerSelectors.any())              
		          .paths(PathSelectors.any())                          
		          .build()
	         .apiInfo(apiInfo())
	          .securitySchemes(Arrays.asList(apiKey()));
	    }

	    private ApiInfo apiInfo() {
	        return new ApiInfoBuilder().title("REST API")
	                .description("The REST API for Trade Exchange.").termsOfServiceUrl("")
	                .contact(new Contact("Shubham Singh", "", "shubhamsinghgu@gmail.com"))
	                /*.license("Apache License Version 2.0")
	                .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0")
	                .version("0.0.1")*/
	                .build();
	    }

	    private ApiKey apiKey() {
	        return new ApiKey("authkey", "Authorization", "header");
	      }
}