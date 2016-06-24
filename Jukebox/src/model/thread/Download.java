package model.thread;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;


public class Download implements Runnable {
	
	private Socket socketDownload;
	private Socket socketAck;
	private String musica;

	public Download(Socket socketDownload, Socket socketAck, String musica) {		
		this.socketDownload = socketDownload;
		this.socketAck = socketAck;
		this.musica = musica;
	}

	@Override
	public void run() {
		DataOutputStream socketOut = null;
		//BufferedReader socketIn = null;
		DataInputStream socketIn = null;
		try {
		
			socketOut = new DataOutputStream(socketDownload.getOutputStream());			
			
			socketIn = new DataInputStream(socketAck.getInputStream());
			
			
			byte[] buffer = new byte[512]; //BUFER DE 512 BYTES
	        FileInputStream file = new FileInputStream("C:/Users/Public/Documents/Jukebox/"+ this.musica);       

			DataInputStream arq = new DataInputStream(file);			
			
			 int leitura = 0;
			
	         while((leitura = arq.read(buffer)) > 0) {         	  
	        	         
	        	
	              socketOut.write(buffer,0,leitura);
	              socketOut.flush();	
	            
	              socketIn.read(); 
	             
	              
	          }         
	         
	         arq.close();
	         socketOut.close();
	         socketIn.close();
		} catch (IOException e) {			
			e.printStackTrace();
		}		
		
	}

}
