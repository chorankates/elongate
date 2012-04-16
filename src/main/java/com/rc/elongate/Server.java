package com.rc.elongate;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static Logger l;
	public static Parser p;
	
	private final static int PORT = 4242;
	
	public static void main (String[] args) {
		// TODO need to use the same logger across com.rc.httplisten 
		l = new Logger(true);
		p = new Parser();
		
		try {
			l.info("starting server..");
			ServerSocket server = new ServerSocket(PORT);
			
			try {
				server.getInetAddress();
				String serverLogAddr = InetAddress.getLocalHost().toString();
				
				l.info("server started on [" + serverLogAddr + ":" + PORT + "]");
				
				while (true) {
					Socket client = server.accept();
					
					String clientLogAddr = client.getInetAddress().getHostAddress();
					
					l.info("[" + clientLogAddr + "] connection initiated");
					
					try {
						BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
						
						try {
							// do work
							String line = in.readLine();
							l.info("[" + clientLogAddr + "] sent: [" + line + "]");
							
							String response = p.parse(line);
							l.debug("[" + line + "] resolved to [" + response + "]");
							
							DataOutputStream out = new DataOutputStream(client.getOutputStream());
							l.debug("opening output stream");
							
							out.writeBytes(response + "\n");
							l.info("[" + serverLogAddr + "] sent [" + response + "] to [" + clientLogAddr + "]");
							
							
						} finally {
							l.debug("[" + clientLogAddr + "] input stream closing");
							in.close();
						}
						
					} finally {
						l.info("[" + clientLogAddr + "] connection closing");
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
