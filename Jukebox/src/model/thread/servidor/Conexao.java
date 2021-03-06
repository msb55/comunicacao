package model.thread.servidor;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import model.Cliente;
import model.ModelLocator;

public class Conexao implements Runnable {
	
	private Cliente cliente;
	private Socket servidor;
	private ServerSocket serverLog;	

	public Conexao(Socket servidor, ServerSocket serverLog) {
		this.servidor = servidor;
		this.serverLog = serverLog;		
	}

	@Override
	public void run() {
		BufferedReader socketEntrada = null;
		DataOutputStream socketSaida = null;
		try {
			System.out.println("Chegou aqui");
			socketEntrada = new BufferedReader(new InputStreamReader(this.servidor.getInputStream()));
			String nome = socketEntrada.readLine();
			System.out.println("leu o nome");
			cliente = new Cliente(this.servidor.getInetAddress().toString(), nome);		
			
			ModelLocator.addClientes(this.cliente);
			
		} catch (IOException e1) {
			e1.printStackTrace();
		}	
		
		new Thread(new EnviaLog(this.serverLog)).start();	
		
		ServerSocket aceita;
		ServerSocket aceita2;
		Socket socketDownload;
		Socket socketAck;
		String musica;
		int porta1, porta2;
				
		try {				
			while(true){				
				musica = socketEntrada.readLine();
				
				
				porta1 = ModelLocator.newPorta();
				porta2 = ModelLocator.newPorta();				
				socketSaida = new DataOutputStream(this.servidor.getOutputStream());	
				
				socketSaida.writeBytes(""+porta1+ "-"+porta2+"\n");
				
				aceita = new ServerSocket(porta1);
				aceita2 = new ServerSocket(porta2);	
				
				socketDownload = aceita.accept();
				socketAck = aceita2.accept();			
				Download download = new Download(socketDownload,socketAck,musica, cliente);	
				cliente.addDownload(download);
				
			}
		} catch (IOException e) {
			System.out.println("IOException Conex�o");
			//e.printStackTrace();
			System.out.println(e.getMessage());
			ModelLocator.removeByIpClientes(this.cliente.getIp());
		}

	}

}
