package model;

public class ModelLocator {
	private static String ipServidor;
	private static int porta;
	private static Cliente cliente;
	
	
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
	public static Cliente getCliente() {
		return cliente;
	}
	public static void setCliente(Cliente cliente) {
		ModelLocator.cliente = cliente;
	}
	
	
}
