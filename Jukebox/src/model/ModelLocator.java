package model;

import java.net.Socket;
import java.util.ArrayList;

public class ModelLocator {
	private static ArrayList<Socket> transferencia = new ArrayList<Socket>();
	private static ArrayList<Socket> ted = new ArrayList<Socket>();
	private static ArrayList<Cliente> clientes =  new ArrayList<Cliente>();	
	
	private static Socket socketPrincipal;
	private static String ipServidor;
	private static int porta = 0;
	private static String cliente;
	
	private static String nomeMusicas;
	private static double tamanhoMusicas;
	private static int porta1;
	private static int porta2;
	
	public static int getPorta1() {
		return porta1;
	}
	public static void setPorta1(int porta1) {
		ModelLocator.porta1 = porta1;
	}
	public static int getPorta2() {
		return porta2;
	}
	public static void setPorta2(int porta2) {
		ModelLocator.porta2 = porta2;
	}
	public static void addTransferencia(Socket socket){
		ModelLocator.transferencia.add(socket);
	}
	public static Socket getTransferencia(int i){
		return ModelLocator.transferencia.get(i);
	}
	public static void addTed(Socket socket){
		ModelLocator.ted.add(socket);
	}
	public static Socket getTed(int i){
		return ModelLocator.ted.get(i);
	}
	
	public static void addClientes(Cliente cliente){
		ModelLocator.clientes.add(cliente);
		
	}
	public static Cliente getClientes(int i){
		return ModelLocator.clientes.get(i);
	}	
	
	public static String getIpServidor() {
		return ipServidor;
	}
	public static void setIpServidor(String ipServidor) {
		ModelLocator.ipServidor = ipServidor;
	}
	public static int getPorta() {
		return porta;
	}
	public static void setPorta(int porta) {
		ModelLocator.porta = porta;
	}
	public static String getCliente() {
		return cliente;
	}
	public static void setCliente(String cliente) {
		ModelLocator.cliente = cliente;
	}	
	public static Socket getSocketPrincipal() {
		return socketPrincipal;
	}
	public static void setSocketPrincipal(Socket socketPrincipal) {
		ModelLocator.socketPrincipal = socketPrincipal;
	}
	
	public static int newPorta(){		
		return ModelLocator.porta++;
	}
	
	public static void initPorta(){
		ModelLocator.porta = 5000;
	}
	public static String getNomeMusicas() {
		return nomeMusicas;
	}
	public static void setNomeMusicas(String nomeMusicas) {
		ModelLocator.nomeMusicas = nomeMusicas;
	}
	public static double getTamanhoMusicas() {
		return tamanhoMusicas;
	}
	public static void setTamanhoMusicas(double tamanhoMusicas) {
		ModelLocator.tamanhoMusicas = tamanhoMusicas;
	}
	
	
}