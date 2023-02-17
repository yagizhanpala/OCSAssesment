package com.ocs.helper;

import java.io.File;

import com.ocs.conf.Config;

public class Validator {
	
	public boolean isInputValid(String[] s) {
		
		if(s.equals(null))
			return false;
		if(s.length !=1 && s.length != 3)
			return false;
		if(!s[0].equals(Config.APPLICATION_NAME.label))
			return false;
		
		return true;
	}
	
	public boolean isFileValid(String filePath) {
		
		var file = new File(filePath);
		
		return file.exists();
	}
}
