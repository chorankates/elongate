package com.rc.elongate;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;


import static com.rc.elongate.Server.l;

public class Resolver {

	private String shortURL; // incoming
	private String longURL;  // outgoing
	
	public Resolver () {
		// not really necessary, but whatever
	}
	
	public Resolver (String url) {
		shortURL = url;
	}
	
	public String resolve () {
		
		URLConnection lwj = null;
		
		try {
			lwj = new URL(shortURL).openConnection();
		} catch (MalformedURLException e) {
			// shouldn't ever really get here since we're regex validating them first
			l.warn(e.getMessage());
			l.warn(e.getStackTrace().toString());	
		} catch (IOException e) {
			l.warn(e.getMessage());
			l.warn(e.getStackTrace().toString());
		}
		
		HttpURLConnection http = (HttpURLConnection) lwj;
		
		try {
			http.getResponseMessage(); // this is the key
		} catch (IOException e) {
			l.warn(e.getMessage());
			l.warn(e.getStackTrace().toString());
		} 
		
				
		URL requestURL = lwj.getURL();

		// logging handled on the other side
		longURL = requestURL.toString();
		
		return longURL;
	}
	
	public String getShortURL () {
		return shortURL;
	}
	
	public String getLongURL () {
		return longURL;
	}
	
	
	
}
