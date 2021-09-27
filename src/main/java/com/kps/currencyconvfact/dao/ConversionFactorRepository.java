package com.kps.currencyconvfact.dao;



import org.springframework.data.repository.CrudRepository;

import com.kps.currencyconvfact.model.ConversionFactor;

public interface ConversionFactorRepository extends CrudRepository<ConversionFactor,String> {

}
