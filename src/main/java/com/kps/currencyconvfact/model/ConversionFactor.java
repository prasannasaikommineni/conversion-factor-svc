package com.kps.currencyconvfact.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ConversionFactor {
@Id
String countryCode;
Double conversionFactor;

public String getCountryCode() {
	return countryCode;
}
public void setCountryCode(String countryCode) {
	this.countryCode = countryCode;
}
public Double getConversionFactor() {
	return conversionFactor;
}
public void setConversionFactor(Double conversionFactor) {
	this.conversionFactor = conversionFactor;
}

}
