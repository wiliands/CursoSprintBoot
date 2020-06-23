package br.com.springboot.config;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import br.com.springboot.converters.serialization.YamlJackson2HttpMessageConverter;
import br.com.springboot.util.CustomMediaType;

@Configuration
public class WebConfig implements WebMvcConfigurer {
	
	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
		/* Via extensão na URL */
		/*
		configurer.favorParameter(false)
				  .ignoreAcceptHeader(false)
				  .defaultContentType(CustomMediaType.APPLICATION_JSON)
				  .mediaType("json", CustomMediaType.APPLICATION_JSON)
				  .mediaType("xml", CustomMediaType.APPLICATION_XML);
		*/
		
		/* Via parametro */
		/*
		configurer.favorParameter(true)
				  .ignoreAcceptHeader(true)
				  .useRegisteredExtensionsOnly(false)
				  .parameterName("mediaType")
				  .defaultContentType(CustomMediaType.APPLICATION_JSON)
				  .mediaType("json", CustomMediaType.APPLICATION_JSON)
				  .mediaType("xml", CustomMediaType.APPLICATION_XML);
		*/
		
		/* Via header */
		configurer.favorParameter(false)
				  .ignoreAcceptHeader(false)
				  .useRegisteredExtensionsOnly(false)
				  .defaultContentType(CustomMediaType.APPLICATION_JSON)
				  .mediaType("json", CustomMediaType.APPLICATION_JSON)
				  .mediaType("xml", CustomMediaType.APPLICATION_XML)
				  .mediaType("yaml", CustomMediaType.APPLICATION_YAML);
	}

	@Override
	public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.add(new YamlJackson2HttpMessageConverter());
	}

}
