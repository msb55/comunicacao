package model.thread.servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class EnviaLog implements Runnable{
	
	private ServerSocket serverLog;

	public EnviaLog(ServerSocket serverLog) {
		this.serverLog = serverLog;
	}

	@Override
	public void run() {
		
		Socket socketDownload;		
		DataOutputStream socketOut = null;
				
		while(true){			
			
			try {
				socketDownload = this.serverLog.accept();
				socketOut = new DataOutputStream(socketDownload.getOutputStream());					
				
				byte[] buffer = new byte[512]; //BUFER DE 512 BYTES
		        FileInputStream file = new FileInputStream("C:/Users/Public/Documents/Jukebox/log.txt");       

				DataInputStream arq = new DataInputStream(file);			
				
				 int leitura = 0;
		         while((leitura = arq.read(buffer)) > 0) {   	        	         	  
		              socketOut.write(buffer,0,leitura);
		              socketOut.flush();	               
		              
		          }         
		        
		         arq.close();
		         socketOut.close();
			} catch (IOException e) {			
				e.printStackTrace();
			}
		
		}
		
	}

}
