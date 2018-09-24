package com.oodles.config;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;

import com.google.common.base.Predicates;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.ApiKeyVehicle;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	/*
	 * @Bean public Docket productApi() { return new
	 * Docket(DocumentationType.SWAGGER_2) .select()
	 * .apis(RequestHandlerSelectors.any()) .paths(PathSelectors.any()) .build(); }
	 */
	/*
	 * public Docket api() {
	 * 
	 * 
	 * ParameterBuilder parameterBuilder = new ParameterBuilder();
	 * parameterBuilder.name("authToken").modelRef(new
	 * ModelRef("string")).parameterType("header").required(false) .build();
	 * List<Parameter> aParameters = new ArrayList<Parameter>();
	 * aParameters.add(parameterBuilder.build());
	 * 
	 * return new Docket(DocumentationType.SWAGGER_2).select()
	 * .apis(RequestHandlerSelectors.withClassAnnotation(Api.class)).paths(
	 * PathSelectors.any()).build()
	 * .pathMapping("/").globalOperationParameters((aParameters)).apiInfo(apiInfo())
	 * .useDefaultResponseMessages(true); }
	 * 
	 * 
	 *//**
		 * This method is use for get apiInfo
		 * 
		 * @param
		 * @return ApiInfo.
		 *//*
			 * public ApiInfo apiInfo() { final ApiInfoBuilder builder = new
			 * ApiInfoBuilder();
			 * builder.title("Scaffold Exchange Order Validation Service API").version("1.0"
			 * ).license("(C) Copyright Scaffold")
			 * .description("The API provides a platform to query build Scaffold exchange api"
			 * ); return builder.build(); } private ApiKey apiKey() { return new
			 * ApiKey("authkey", "Authorization", "header"); }
			 */

	/*
	 * private ApiInfo apiInfo() { ApiInfo apiInfo = new ApiInfo( "My REST API",
	 * "Some custom description of API.", "API TOS", "Terms of service",
	 * "myeaddress@company.com", "License of API", "API license URL"); return
	 * apiInfo; }
	 */

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
	}
}