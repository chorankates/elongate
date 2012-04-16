package com.rc.elongate;

public class Parser {
	
	public String parse (String line) {
		
		if (line.matches("^https?://.*")) {
		
			Resolver r = new Resolver(line);
			
			return r.resolve();
			
		} else {
			
			if (line.matches("^test:.*")) {
				return line.toUpperCase();
			}
			
			return "invalid URL";
		}
		
	}

}
