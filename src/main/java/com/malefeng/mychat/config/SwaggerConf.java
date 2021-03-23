package com.malefeng.mychat.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**  
* @ClassName: SwaggerConf.java  
* @Description: swagger配置
* @author mlf
* @date 2020/2/10
*/
@Configuration
public class SwaggerConf {
	
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo()).select().apis(RequestHandlerSelectors.basePackage("com.malefeng.mychat.controller"))
				.paths(PathSelectors.any()).build();
	}
	
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("mychat api文档")
				.description("----------designed by mlf----------").version("1.0").build();
	}

}
