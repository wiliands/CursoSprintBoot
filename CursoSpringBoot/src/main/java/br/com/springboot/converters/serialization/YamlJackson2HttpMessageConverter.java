package br.com.springboot.converters.serialization;

import org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import br.com.springboot.util.CustomMediaType;

public final class YamlJackson2HttpMessageConverter extends AbstractJackson2HttpMessageConverter {

	public YamlJackson2HttpMessageConverter() {
		super(new YAMLMapper()
				 .setSerializationInclusion(JsonInclude.Include.NON_NULL), CustomMediaType.APPLICATION_YAML);
	}

}
