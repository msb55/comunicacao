package model.thread.servidor;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.table.DefaultTableModel;

import model.Cliente;
import model.ModelLocator;

public class ClienteOnline implements Runnable {
	
	private Socket servidor;
	private Cliente cliente;
	

	public ClienteOnline(Cliente cliente, Socket socketOnline) {
		this.cliente = cliente;		
		this.servidor = socketOnline;		
	}

	@Override
	public void run() {
		
		System.out.println("vai add");
		
		ModelLocator.addClientes(this.cliente);
		
		System.out.println("Adicionou");
				
		BufferedReader socketEntrada = null;
		DataOutputStream socketSaida = null;
		try {
			socketEntrada = new BufferedReader(new InputStreamReader(this.servidor.getInputStream()));
			socketSaida = new DataOutputStream(this.servidor.getOutputStream());
			while(true){
				socketEntrada.readLine();
				socketSaida.writeBytes("ACK\n");	
				Thread.sleep(5000);
			}
		} catch (IOException e) {
			ModelLocator.removeByIpClientes(this.cliente.getIp());
			System.out.println("Cliente ON IOException");
			e.printStackTrace();			
		} catch (InterruptedException e) {	
			System.out.println("Cliente ON InterruptedException");
			e.printStackTrace();
		}
		
	}
	


}
