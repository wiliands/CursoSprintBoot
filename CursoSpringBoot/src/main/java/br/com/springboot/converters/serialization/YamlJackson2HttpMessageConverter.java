package br.com.springboot.converters.serialization;

import org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter;

import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import br.com.springboot.util.CustomMediaType;

public final class YamlJackson2HttpMessageConverter extends AbstractJackson2HttpMessageConverter {

	public YamlJackson2HttpMessageConverter() {
		super(new YAMLMapper(), CustomMediaType.APPLICATION_YAML);
	}

}
