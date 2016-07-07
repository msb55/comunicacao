package model.thread.servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.Socket;
import java.net.SocketException;
import java.util.Observable;
import model.Cliente;

public class Download extends Observable implements Runnable {
	
	private Socket socketDownload;
	private Socket socketAck;
	private String musica;
	private Thread  thisThread;
    private long  filesize;
	private float  progress;
	private String tempoEstimado;
	private Cliente cliente;

	public Download(Socket socketDownload, Socket socketAck, String musica, Cliente cliente) {		
		this.socketDownload = socketDownload;
		this.socketAck = socketAck;
		this.musica = musica;
		this.tempoEstimado = "";
		this.cliente = cliente;
		this.thisThread = new Thread(this);
		this.thisThread.start();	  
	}

	@Override
	public void run() {
		DataOutputStream socketOut = null;		
		DataInputStream socketIn = null;
		
		try {		
			socketOut = new DataOutputStream(socketDownload.getOutputStream());			
			socketIn = new DataInputStream(socketAck.getInputStream());		
			
			byte[] buffer = new byte[1024*15]; //BUFER DE 512 BYTES     
	        File f = new File("C:/Users/Public/Documents/Jukebox/"+ this.musica);
			RandomAccessFile d = new RandomAccessFile("C:/Users/Public/Documents/Jukebox/"+ this.musica, "rw");
			filesize = f.length();
			
			int leitura = 0; int baixado = 0; int  cont=0;
			long tempoIda, tempoVolta, tempoTotal;
			double tempo=0;
			
			
			try{
				while((leitura = d.read(buffer)) > 0) {         	  
	        	         
	        	baixado += leitura;
	        	progress = ((float) baixado / filesize) * 100;	        	  
	        	 
	            tempoIda = System.nanoTime();
	            socketOut.write(buffer,0,leitura);
	            socketOut.flush();	
	            
	            int x = socketIn.read(); 
	             
	            System.out.println(baixado);
	              
	            tempoVolta = System.nanoTime();				
				tempoTotal = (tempoVolta - tempoIda)/1000;
	              
	              
	            if(cont % 2 == 0){
	            	tempo = ((filesize-baixado)*tempoTotal)/1024;
					tempo /= 1000000;
					int minuto = (int) tempo/60;
					int segundo = (int) tempo%60;
					tempoEstimado  = minuto + " min. " + segundo + " seg.";
	            }
	              
	            if(x == 2){
	            	tempo=0; baixado = 0;cont =0;
	            	d.seek(0);   	  
	            }
					
	            setChanged();
	            notifyObservers(this); 
				cont++;		          
				}
			}catch(SocketException e){
				System.out.println(e.getMessage());
				System.out.println("AQUI");
				cliente.RemoveDownload(this);				
			}
			
	        d.close();
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

	public String getTempoEstimado() {
		return tempoEstimado;
	}

	public void setTempoEstimado(String tempoEstimado) {
		this.tempoEstimado = tempoEstimado;
	}

}
