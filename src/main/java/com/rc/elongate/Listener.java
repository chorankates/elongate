package com.rc.elongate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Listener {

	private final static int PORT = 4242;
	
	public static void main (String[] args) {
		// TODO need to use the same logger across com.rc.httplisten 
		Logger l = new Logger(true);
		Parser p = new Parser();
		
		try {
			l.info("starting server..");
			ServerSocket server = new ServerSocket(PORT);
			
			try {
				server.getInetAddress();
				l.info("server started on [" + InetAddress.getLocalHost() + ":" + PORT + "]");
				
				while (true) {
					Socket client = server.accept();
					l.info("[" + client.getInetAddress().getHostAddress() + "] connection initiated");
					
					try {
						BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
						
						try {
							// do work
							String line = in.readLine();
							l.info("[" + client.getInetAddress().getHostAddress() + "] sent: " + line);
							
							//String response = p.parse(line);
							
						} finally {
							l.debug("[" + client.getInetAddress().getHostAddress() + "] input stream closing");
							in.close();
						}
						
					} finally {
						l.info("[" + client.getInetAddress().getHostAddress() + "] connection closing");
						client.close();
					}
					
				}
			} finally {
				l.info("server connection closing..");
				server.close();
			}
			
		} catch (IOException e) {
			System.err.println("failed to initialize server socket: " + PORT + ", exception:" + e.toString());

			l.fatal("caught an exception during startup: " + e.toString());
			
			System.exit(1);
		}
	}
	
	
	
}
