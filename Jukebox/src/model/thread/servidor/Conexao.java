package model.thread.servidor;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
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

	public Conexao(Socket servidor, DefaultTableModel tabela) {
		this.tabela = tabela;
		this.servidor = servidor;
	}

	@Override
	public void run() {
		BufferedReader socketEntrada = null;
		DataOutputStream socketSaida = null;
		try {
			socketEntrada = new BufferedReader(new InputStreamReader(this.servidor.getInputStream()));
			String nome = socketEntrada.readLine();
			cliente = new Cliente(this.servidor.getInetAddress().toString(), nome);		
			
			new Thread(new ClienteOnline(cliente, tabela)).start();			
			
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		
		
		
		
		ServerSocket aceita;
		ServerSocket aceita2;
		Socket socketDownload;
		Socket socketAck;
		String musica;
		int porta1;
		int porta2;
		DataOutputStream socketOut = null;
		try {		
			aceita = new ServerSocket(3502);
			socketDownload = aceita.accept();
			
			try {
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
