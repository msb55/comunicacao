package model.thread.servidor;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.table.DefaultTableModel;

import model.ModelLocator;

public class Servidor implements Runnable {
	
	DefaultTableModel tabela;

	public Servidor(DefaultTableModel tabela) {
		this.tabela = tabela;
	}

	@Override
	public void run() {
		ServerSocket aceita;
		ServerSocket serverLog;
		ServerSocket aceitaOnline;
		Socket socket;
		Socket socketOnline;
		ModelLocator.initPorta();
		try {
			aceita = new ServerSocket(3493);
			serverLog = new ServerSocket(3502);
			aceitaOnline = new ServerSocket(3000);
			
			while(true){
				socket = aceita.accept();	
				socketOnline = aceita.accept();
				new Thread(new Conexao(socket,socketOnline,serverLog,tabela)).start();			
				
				
			}
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}

}
