package com.ocs.business;

import java.io.FileNotFoundException;

import com.ocs.helper.Validator;

public class ValidationBusiness {

	public void validateInputAndFile(String[] s) throws FileNotFoundException, IllegalArgumentException {
		
		var validator = new Validator();
		
		boolean isInputValid = validator.isInputValid(s);
		if(!isInputValid) {
			throw new IllegalArgumentException();
		}
		if(s.length == 3 && !validator.isFileValid(s[1])) {
			throw new FileNotFoundException();
		}		
	}
	
}
