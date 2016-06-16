package model.thread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Servidor implements Runnable {
	
	DefaultTableModel tabela;

	public Servidor(DefaultTableModel tabela) {
		this.tabela = tabela;
	}

	@Override
	public void run() {
		ServerSocket aceita;
		String nome;
		Socket socket;
		try {
			aceita = new ServerSocket(3493);		
			
			while(true){
				socket = aceita.accept();				
				BufferedReader socketEntrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				
				nome = socketEntrada.readLine();
				
				new Thread(new Conexao(nome, socket,tabela)).start();	
				
				
				
				
			}
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}

}
