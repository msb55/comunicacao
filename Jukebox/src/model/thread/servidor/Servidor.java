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
		Socket socket;
		ModelLocator.initPorta();
		try {
			aceita = new ServerSocket(3493);
			serverLog = new ServerSocket(3502);
			
			while(true){
				socket = aceita.accept();				
				new Thread(new Conexao(socket,serverLog,tabela)).start();			
				
				
			}
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}

}
