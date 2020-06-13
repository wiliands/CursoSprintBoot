package br.com.springboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.springboot.converters.NumberConverter;
import br.com.springboot.exceptions.UnsupportedMathOperationException;
import br.com.springboot.math.SimpleMath;

@RestController
public class MathController {
	
	@Autowired
	private SimpleMath math;
	
	@Autowired
	private NumberConverter numberConverter;
	
	@RequestMapping(value = "/sum/{numberOne}/{numberTwo}", method = RequestMethod.GET)
	public Double sum(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo) {

		if (!numberConverter.isNumeric(numberOne) || !numberConverter.isNumeric(numberTwo)) {
			throw new UnsupportedMathOperationException("Please set a numeric value!");
		}

		return math.sum(numberConverter.convertToDouble(numberOne), numberConverter.convertToDouble(numberTwo));
	}

	@RequestMapping(value = "/sub/{numberOne}/{numberTwo}", method = RequestMethod.GET)
	public Double sub(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo) {

		if (!numberConverter.isNumeric(numberOne) || !numberConverter.isNumeric(numberTwo)) {
			throw new UnsupportedMathOperationException("Please set a numeric value!");
		}

		return math.sub(numberConverter.convertToDouble(numberOne), numberConverter.convertToDouble(numberTwo));
	}

	@RequestMapping(value = "/plus/{numberOne}/{numberTwo}", method = RequestMethod.GET)
	public Double plus(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo) {

		if (!numberConverter.isNumeric(numberOne) || !numberConverter.isNumeric(numberTwo)) {
			throw new UnsupportedMathOperationException("Please set a numeric value!");
		}

		return math.plus(numberConverter.convertToDouble(numberOne), numberConverter.convertToDouble(numberTwo));
	}
	
	@RequestMapping(value = "/divided/{numberOne}/{numberTwo}", method = RequestMethod.GET)
	public Double divided(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo) {

		if (!numberConverter.isNumeric(numberOne) || !numberConverter.isNumeric(numberTwo)) {
			throw new UnsupportedMathOperationException("Please set a numeric value!");
		}

		return numberConverter.convertToDouble(numberOne) / numberConverter.convertToDouble(numberTwo);
	}
	
	@RequestMapping(value = "/avg/{numberOne}/{numberTwo}", method = RequestMethod.GET)
	public Double avg(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo) {

		if (!numberConverter.isNumeric(numberOne) || !numberConverter.isNumeric(numberTwo)) {
			throw new UnsupportedMathOperationException("Please set a numeric value!");
		}

		return math.avg(numberConverter.convertToDouble(numberOne), numberConverter.convertToDouble(numberTwo));
	}
	
	@RequestMapping(value = "/squareRoot/{number}", method = RequestMethod.GET)
	public Double squareRoot(@PathVariable("number") String number) {

		if (!numberConverter.isNumeric(number)) {
			throw new UnsupportedMathOperationException("Please set a numeric value!");
		}

		return math.squareRoot(numberConverter.convertToDouble(number));
	}

}
