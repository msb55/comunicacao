package model.thread;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import model.ModelLocator;

public class ClienteOnline implements Runnable {
	
	private Socket servidor;

	public ClienteOnline() {
		
	}

	@Override
	public void run() {
		
		try {
			ServerSocket aceita = new ServerSocket(3000);
			servidor = aceita.accept();
		} catch (IOException e1) {			
			e1.printStackTrace();
		}
		
		
		BufferedReader socketEntrada = null;
		DataOutputStream socketSaida = null;
		try {
			socketEntrada = new BufferedReader(new InputStreamReader(this.servidor.getInputStream()));
			socketSaida = new DataOutputStream(this.servidor.getOutputStream());
			while(true){
				socketEntrada.readLine();
				socketSaida.writeBytes("ACK\n");	
				Thread.sleep(10000);
			}
		} catch (IOException e) {
			
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
