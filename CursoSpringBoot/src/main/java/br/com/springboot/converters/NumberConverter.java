package br.com.springboot.converters;

import org.springframework.stereotype.Service;

@Service
public class NumberConverter {
	
	public double convertToDouble(String strNumber) {
		if (!isNumeric(strNumber))
			return 0d;
		return Double.parseDouble(strNumber);
	}

	public boolean isNumeric(String strNumber) {
		if (strNumber == null)
			return false;
		String number = strNumber.replaceAll(",", ".");
		return number.matches("[-+]?[0-9]*\\.?[0-9]+");
	}

}
