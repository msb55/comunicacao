package model;

import java.net.Socket;
import java.util.ArrayList;

import model.thread.Download;

public class Cliente {

	
	private String ip;
	private String nome;
	private ArrayList<Download> downloads;
	
	public Cliente(String ip, String nome) {	
		this.ip = ip;
		this.nome = nome;
		this.setDownloads(new ArrayList<Download>());	
	}


	public String getIp() {
		return ip;
	}


	public void setIp(String ip) {
		this.ip = ip;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public ArrayList<Download> getDownloads() {
		return downloads;
	}

	public void setDownloads(ArrayList<Download> downloads) {
		this.downloads = downloads;
	}
	
	public  void addDownload(Download download){
		this.downloads.add(download);
	}
	public  Download getDownload(int i){
		return this.downloads.get(i);
	}
}
