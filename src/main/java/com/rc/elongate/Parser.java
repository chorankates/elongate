package com.rc.elongate;

import static com.rc.elongate.Server.l;

public class Parser {
	
	public String parse (String line) {
		
		if (line.matches("^https?://.*")) {
		
			Resolver r = new Resolver(line);
			
			try {
				return r.resolve();
			} catch (Exception e) {
				l.warn(e.getMessage());
				l.warn(e.getStackTrace().toString());
			}
			
			return "error parsing"; // failover
			
		} else {
			
			if (line.matches("^test:.*")) {
				return line.toUpperCase();
			}
			
			return "invalid URL";
		}
		
	}

}
