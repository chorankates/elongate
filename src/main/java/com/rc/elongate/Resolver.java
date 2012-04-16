package com.rc.elongate;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;
import java.util.List;

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
			// TODO add some logging here
			e.printStackTrace();
		} catch (IOException e) {
			// TODO add some logging here
			e.printStackTrace();
		}
		
		Map<String, List<String>> headers = lwj.getHeaderFields();
		
		for (int i = 0; i < headers.size(); i++)
			System.out.println("h[" + i + "]:" + headers.get(i)); 

		longURL = shortURL;
		
		return longURL;
	}
	
	public String getShortURL () {
		return shortURL;
	}
	
	public String getLongURL () {
		return longURL;
	}
	
	
	
}
