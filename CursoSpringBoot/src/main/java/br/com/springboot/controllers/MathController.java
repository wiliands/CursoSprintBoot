package br.com.springboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import br.com.springboot.converters.NumberConverter;
import br.com.springboot.exceptions.UnsupportedMathOperationException;
import br.com.springboot.math.SimpleMath;

@RestController
public class MathController {
	
	private static final String MSG_NUMERIC_ERROR = "Please set a numeric value!";

	@Autowired
	private SimpleMath math;
	
	@Autowired
	private NumberConverter numberConverter;
	
	@GetMapping("/sum/{numberOne}/{numberTwo}")
	public Double sum(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo) {

		if (!numberConverter.isNumeric(numberOne) || !numberConverter.isNumeric(numberTwo)) {
			throw new UnsupportedMathOperationException(MSG_NUMERIC_ERROR);
		}

		return math.sum(numberConverter.convertToDouble(numberOne), numberConverter.convertToDouble(numberTwo));
	}

	@GetMapping("/sub/{numberOne}/{numberTwo}")
	public Double sub(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo) {

		if (!numberConverter.isNumeric(numberOne) || !numberConverter.isNumeric(numberTwo)) {
			throw new UnsupportedMathOperationException(MSG_NUMERIC_ERROR);
		}

		return math.sub(numberConverter.convertToDouble(numberOne), numberConverter.convertToDouble(numberTwo));
	}

	@GetMapping("/plus/{numberOne}/{numberTwo}")
	public Double plus(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo) {

		if (!numberConverter.isNumeric(numberOne) || !numberConverter.isNumeric(numberTwo)) {
			throw new UnsupportedMathOperationException(MSG_NUMERIC_ERROR);
		}

		return math.plus(numberConverter.convertToDouble(numberOne), numberConverter.convertToDouble(numberTwo));
	}
	
	@GetMapping("/divided/{numberOne}/{numberTwo}")
	public Double divided(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo) {

		if (!numberConverter.isNumeric(numberOne) || !numberConverter.isNumeric(numberTwo)) {
			throw new UnsupportedMathOperationException(MSG_NUMERIC_ERROR);
		}

		return numberConverter.convertToDouble(numberOne) / numberConverter.convertToDouble(numberTwo);
	}
	
	@GetMapping("/avg/{numberOne}/{numberTwo}")
	public Double avg(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo) {

		if (!numberConverter.isNumeric(numberOne) || !numberConverter.isNumeric(numberTwo)) {
			throw new UnsupportedMathOperationException(MSG_NUMERIC_ERROR);
		}

		return math.avg(numberConverter.convertToDouble(numberOne), numberConverter.convertToDouble(numberTwo));
	}
	
	@GetMapping("/squareRoot/{number}")
	public Double squareRoot(@PathVariable("number") String number) {

		if (!numberConverter.isNumeric(number)) {
			throw new UnsupportedMathOperationException(MSG_NUMERIC_ERROR);
		}

		return math.squareRoot(numberConverter.convertToDouble(number));
	}

}
