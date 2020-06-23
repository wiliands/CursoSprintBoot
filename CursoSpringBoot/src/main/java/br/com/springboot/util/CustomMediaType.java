package br.com.springboot.util;

import org.springframework.http.MediaType;

public class CustomMediaType extends MediaType {

	private static final long serialVersionUID = 1L;

	public static final String APPLICATION_YAML_VALUE = "application/x-yaml";
	
	public static final MediaType APPLICATION_YAML = MediaType.valueOf(APPLICATION_YAML_VALUE);
	
	public CustomMediaType(String type) {
		super(type);
	}

}
