package br.com.springboot.converters;

import java.util.ArrayList;
import java.util.List;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;

public class DozerConverter {
	
	private static Mapper mapper = DozerBeanMapperBuilder.buildDefault();
	
	public static <O, D> D parseObject(O origin, Class<D> destinationClass) {
		return mapper.map(origin, destinationClass);
	}

	public static <O, D> List<D> parseListObjects(List<O> origins, Class<D> destinationClass) {
		List<D> destinationObjects = new ArrayList<>();
		origins.forEach(o -> {
			destinationObjects.add(parseObject(o, destinationClass));
		});
		return destinationObjects;
	}
	
}
