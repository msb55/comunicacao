package model.thread;

import java.net.Socket;

public class Conexao implements Runnable {
	
	String nome;
	Socket servidor;
	

	public Conexao(String nome, Socket servidor) {
		this.nome = nome;
	}

	@Override
	public void run() {
		
		

	}

}
