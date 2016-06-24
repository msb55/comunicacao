package model.thread;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Observable;


public class Download extends Observable implements Runnable {
	
	private Socket socketDownload;
	private Socket socketAck;
	private String musica;
	private Thread  thisThread;
    private long  filesize;
	private float  progress;

	public Download(Socket socketDownload, Socket socketAck, String musica) {		
		this.socketDownload = socketDownload;
		this.socketAck = socketAck;
		this.musica = musica;
		this.thisThread = new Thread(this);
	    this.thisThread.start();
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
	        File f = new File("C:/Users/Public/Documents/Jukebox/"+ this.musica);
			DataInputStream arq = new DataInputStream(file);			
			filesize = f.length();
			 int leitura = 0;
			 int baixado = 0;
			
	         while((leitura = arq.read(buffer)) > 0) {         	  
	        	         
	        	  baixado += leitura;
	        	  progress = ((float) baixado / filesize) * 100;
	        	  
	        	  setChanged();
	              notifyObservers(this); 
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

	public String getMusica() {
		return musica;
	}

	public void setMusica(String musica) {
		this.musica = musica;
	}

	public long getFilesize() {
		return filesize;
	}

	public void setFilesize(long filesize) {
		this.filesize = filesize;
	}

	public float getProgress() {
		return progress;
	}

	public void setProgress(float progress) {
		this.progress = progress;
	}
	
	

}
