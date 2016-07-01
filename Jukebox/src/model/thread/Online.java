package model.thread;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import model.ModelLocator;

public class Online implements Runnable{

	private Socket servidor;

	public Online(Socket servidor) {
		this.servidor = servidor;
	}

	@Override
	public void run() {
		BufferedReader socketEntrada = null;
		DataOutputStream socketSaida = null;
		
		try {
			socketEntrada = new BufferedReader(new InputStreamReader(this.servidor.getInputStream()));
			socketSaida = new DataOutputStream(this.servidor.getOutputStream());
			while(true){				
				socketSaida.writeBytes("ACK\n");
				socketEntrada.readLine();
			}
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}

}
