package br.com.springboot.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpMethod;

public class HttpMethodsUtil {

	public static String[] getHttpMethods( ) {
		List<String> methodos = new ArrayList<>();
		for (HttpMethod method: HttpMethod.values()) {
			methodos.add(method.toString());
		}
		return methodos.toArray(new String[methodos.size()]);
	}
	
}
