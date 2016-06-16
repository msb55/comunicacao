package model;

public class ModelLocator {
	private static String ipServidor;
	private static int porta;
	
	
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
}
