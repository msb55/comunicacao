package model.thread;

import java.net.Socket;

import javax.swing.table.DefaultTableModel;

public class Conexao implements Runnable {
	
	String nome;
	Socket servidor;
	DefaultTableModel tabela;	

	public Conexao(String nome, Socket servidor,DefaultTableModel tabela) {
		this.nome = nome;
		this.tabela = tabela;
		this.servidor = servidor;
	}

	@Override
	public void run() {
		
		this.tabela.addRow(	new String[] {this.nome, this.servidor.getInetAddress().toString() });
		
		

	}

}
