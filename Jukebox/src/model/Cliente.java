package model;

import java.util.ArrayList;


import model.thread.Download;
import model.thread.DownloadTableModel;

public class Cliente {

	
	private String ip;
	private String nome;
	private ArrayList<Download> downloads;
	private DownloadTableModel tableModelDownloads;
	

	public Cliente(String ip, String nome) {	
		this.ip = ip;
		this.nome = nome;
		this.tableModelDownloads = new DownloadTableModel();
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
		this.tableModelDownloads.addDownload(download);
		this.downloads.add(download);
	}
	
	public  Download getDownload(int i){
		return this.downloads.get(i);
	}	

	public DownloadTableModel getTableModelDownloads() {
		return tableModelDownloads;
	}

	public void setTableModelDownloads(DownloadTableModel tableModelDownloads) {
		this.tableModelDownloads = tableModelDownloads;
	}

}
