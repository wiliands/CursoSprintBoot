package br.com.springboot.math;

import org.springframework.stereotype.Service;

@Service
public class SimpleMath {
	
	public Double sum(Double numberOne, Double numberTwo) {
		return Double.sum(numberOne, numberTwo);
	}

	public Double sub(Double numberOne, Double numberTwo) {
		return numberOne - numberTwo;
	}

	public Double plus(Double numberOne, Double numberTwo) {
		return numberOne * numberTwo;
	}
	
	public Double divided(Double numberOne, Double numberTwo) {
		return numberOne  / numberTwo;
	}
	
	public Double avg(Double numberOne, Double numberTwo) {
		return sum(numberOne, numberTwo) / 2;
	}
	
	public Double squareRoot(Double number) {
		return Math.sqrt(number);
	}

}
