package com.oodles.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.annotations.Api;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
@Configuration
@EnableSwagger2
public class SwaggerConfig {
	/*@Bean
	public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any()).build();
	}*/

	
public Docket api() {
		
		
		ParameterBuilder parameterBuilder = new ParameterBuilder();
		parameterBuilder.name("authToken").modelRef(new ModelRef("string")).parameterType("header").required(false)
				.build();
		List<Parameter> aParameters = new ArrayList<Parameter>();
		aParameters.add(parameterBuilder.build());
		
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.withClassAnnotation(Api.class)).paths(PathSelectors.any()).build()
				.pathMapping("/").globalOperationParameters((aParameters)).apiInfo(apiInfo()).useDefaultResponseMessages(true);
	}
	
		
/**
* This method is use for get apiInfo
* 
* @param
* @return ApiInfo.
*/
public ApiInfo apiInfo() {
final ApiInfoBuilder builder = new ApiInfoBuilder();
builder.title("Trade Exchange Order Validation Service API").version("1.0").license("(C) Copyright Oodles")
.description("The API provides a platform to query build Trade exchange api");
return builder.build();
}

}