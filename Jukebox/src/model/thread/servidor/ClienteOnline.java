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
	private DefaultTableModel tabela;
	private int row;

	public ClienteOnline(Cliente cliente, Socket socketOnline, DefaultTableModel tabela) {
		this.cliente = cliente;
		this.tabela = tabela;
		this.servidor = socketOnline;
		
		
	}

	@Override
	public void run() {
		
		ModelLocator.addClientes(this.cliente);
		this.tabela.addRow(	new String[] {this.cliente.getNome(), this.cliente.getIp() });
		row = this.tabela.getRowCount()-1;	
		
		
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
			tabela.removeRow(row);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	


}
