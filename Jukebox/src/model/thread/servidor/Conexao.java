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

public class Conexao implements Runnable {
	
	private Cliente cliente;
	private Socket servidor;
	private DefaultTableModel tabela;
	private ServerSocket serverLog;
	private Socket socketOnline;

	public Conexao(Socket servidor,Socket socketOnline, ServerSocket serverLog, DefaultTableModel tabela) {
		this.tabela = tabela;
		this.servidor = servidor;
		this.serverLog = serverLog;
		this.socketOnline = socketOnline;
	}

	@Override
	public void run() {
		BufferedReader socketEntrada = null;
		DataOutputStream socketSaida = null;
		try {
			socketEntrada = new BufferedReader(new InputStreamReader(this.servidor.getInputStream()));
			String nome = socketEntrada.readLine();
			cliente = new Cliente(this.servidor.getInetAddress().toString(), nome);		
			
			new Thread(new ClienteOnline(cliente,socketOnline, tabela)).start();			
			
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
				Download download = new Download(socketDownload,socketAck,musica);	
				cliente.addDownload(download);
				
			}
		} catch (IOException e) {
			System.out.println("ERRO DE CONEXÃO");
		}

	}

}
