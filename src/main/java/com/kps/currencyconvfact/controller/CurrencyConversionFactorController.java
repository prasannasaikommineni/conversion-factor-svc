package com.kps.currencyconvfact.controller;

import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.kps.currencyconvfact.dao.ConversionFactorRepository;
import com.kps.currencyconvfact.model.ConversionFactor;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
public class CurrencyConversionFactorController {
	Logger logger = LoggerFactory.getLogger(CurrencyConversionFactorController.class);
	
	@Autowired
	ConversionFactorRepository conversionFactorRepository;
	@GetMapping("/")
	public String get() {
		return "Welcome!!!";
	}
	
	@PostMapping("/create")
	public ResponseEntity<ConversionFactor> createConversionFactor(@RequestBody ConversionFactor cf) {
		ConversionFactor  res  = new ConversionFactor();
		try {
		  res = conversionFactorRepository.save(cf);
		}
		catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
					e.getMessage());
		  } 
		logger.info("in createConversionFactor {} ,{}" ,res.getCountryCode(),res.getConversionFactor());
		return ResponseEntity.ok(res);
		
	}
	@PutMapping(value="/update")
	public ResponseEntity<ConversionFactor> updateConversionFactor(@RequestBody ConversionFactor cf) {
		ConversionFactor  res  = new ConversionFactor();
		try {
		  res = conversionFactorRepository.save(cf);
		}
		catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
					e.getMessage());
		  } 
		return ResponseEntity.ok(res);
		
	}
	
	
	@GetMapping(value="/get")
	@HystrixCommand(fallbackMethod = "defaultConvFactor")
	public ResponseEntity<Double> getConversionFactor(@RequestParam String countryCode) {
		Optional<ConversionFactor>  res ;
		try {
		  res = conversionFactorRepository.findById(countryCode);
		}
		catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
					e.getMessage());
		  } 
		logger.info("getConversionFactor {} {}",countryCode , res);
		return ResponseEntity.ok(res.get().getConversionFactor());
		
	}
	
	private ResponseEntity<Double> defaultConvFactor(String  command) {
		return ResponseEntity.ok(0.0);
	}
}
