package com.rc.elongate;

/*
 * TODO
 * 
 * figure out how to do a DESTROY {} to cleanup logfiles
 */

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.Random;

public class Logger {

	// TODO this is not the right way to segment log levels, i want them to be comparable
	private enum LOG_LEVELS {
		DEBUG,
		INFO,
		WARN,
		FATAL,
	}
	
	private String file;
	private boolean stdout;
	
	public Logger () { 
		this.file   = generateFileName();
		this.stdout = false;
	}
	
	public Logger (boolean stdout) {
		this.file   = generateFileName();
		this.stdout = stdout;
	}

	public String getFile() {
		return file;
	}

	public boolean isStdout() {
		return stdout;
	}

	public void debug (String message) {
		log(LOG_LEVELS.DEBUG, message);
	}
	
	public void info (String message) {
		log(LOG_LEVELS.INFO, message);
	}
	
	public void warn (String message) {
		log(LOG_LEVELS.WARN, message);
	}
	
	public void fatal (String message) {
		log(LOG_LEVELS.FATAL, message);
	}
	
	public void log (LOG_LEVELS level, String message) {
		String timestamp = Calendar.getInstance().getTime().toString();
		String prefix    = timestamp + " LOG::" + level.toString() + "::";
		String postfix   = System.getProperty("line.separator");
		
		String entry = prefix + message + postfix;
		
		// writing to a file
		try {
			FileWriter fstream = new FileWriter(file);
			BufferedWriter out = new BufferedWriter(fstream);
			
			out.write(entry);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		

		if(level.equals(LOG_LEVELS.FATAL) || level.equals(LOG_LEVELS.WARN))
			System.err.print(entry);
		else if (stdout) 		// outputting to STDOUT -- verbosity, TODO gate on severity 
			System.out.print(entry);
		
	}
	
	private String generateFileName() {
		/*
		// this is going to give us invalid filenames
		 * 
		Calendar now = Calendar.getInstance();
		return "/tmp/logger.java_log" + now.getTime(); 
		*/
		
		return "/tmp/logger.java_log" + new Random().nextInt();
	}
	

	
}
