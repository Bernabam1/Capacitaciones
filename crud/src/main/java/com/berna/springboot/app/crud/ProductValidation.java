package com.berna.springboot.app.crud;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.berna.springboot.app.crud.entities.Product;

@Component // Para que lo levante Spring
public class ProductValidation implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Product.class.isAssignableFrom(clazz); // Acá va la clase referenciada así
	}

	@Override
	public void validate(Object target, Errors errors) { // Target y BindindResult
		Product product = (Product)target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, null, "-esta parte se personaliza empty name-");
		// ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "NotEmpty.product.description");
		// Se puede también validar programático
		if(product.getDescription() == null || product.getDescription().isBlank()) {
			errors.rejectValue("description", null, "-esta parte se personaliza empty description-");
		}
		// Price
		if(product.getPrice() == null) {
			errors.rejectValue("price", null, "-esta parte se personaliza null price-");
		} else if (product.getPrice() < 10) {
			errors.rejectValue("price", null, "-esta parte se personaliza null min price-");
		}
	}

}
