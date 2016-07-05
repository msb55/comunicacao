package model.thread.cliente;

import gui.cliente.Musicas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.Socket;

import jaco.mp3.player.MP3Player;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.ModelLocator;

public class PerfilCliente implements Runnable{
	
	private DefaultTableModel modelo;
	private JButton btnPlay;
	private JButton btnPause;
	private JButton btnStop;
	private MP3Player mpp;
	private JButton btnBaixarMusicas;
	private JTable table;

	public PerfilCliente(DefaultTableModel modelo, JButton btnPlay,
			JButton btnPause, JButton btnStop, MP3Player mpp,
			JButton btnBaixarMusicas, JTable table) {
		this.modelo = modelo;
		this.btnPlay = btnPlay;
		this.btnPause = btnPause;
		this.btnStop = btnStop;
		this.mpp = mpp;
		this.btnBaixarMusicas = btnBaixarMusicas;
		this.table = table;
	}
	
	@Override
	public void run() {
		carregarMusicas();
		this.btnBaixarMusicas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(btnBaixarMusicas.isEnabled()){
					Musicas musicas = new Musicas();
					musicas.setLocationRelativeTo(null);
					musicas.setVisible(true);
					
					new Thread(new GerenciaBotaoBaixar(musicas, btnBaixarMusicas)).start();
				}				
			}
		});
		
		this.table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 1) {
					String nome = table.getValueAt(table.getSelectedRow(), 0).toString();

					if(mpp != null) mpp.stop();
					mpp = new MP3Player(new File("C:\\Users\\Public\\Documents\\"+nome+".mp3"));				
				}			
			}
		});
		
		this.btnPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mpp.play();
			}
		});
		
		this.btnPause.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mpp.pause();
			}
		});
		
		btnStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mpp.stop();
			}
		});
		
		
	}
	
	public void carregarMusicas(){		
		try {
			File f = new File("C:\\Users\\Public\\Documents\\log.txt");
			FileReader ler = new FileReader(f);
			BufferedReader lerArquivo = new BufferedReader(ler);
			
			String nome = lerArquivo.readLine();
			while(nome != null){
				String album = lerArquivo.readLine();
				String artista = lerArquivo.readLine();
				String tamanho = lerArquivo.readLine();
				
				Object[] obj = {nome, album, artista, String.format("%.2f",((Double.parseDouble(tamanho)/1024)/1024))+" MB"};
				
				this.modelo.addRow(obj);
				
				nome = lerArquivo.readLine();
			}
			
			lerArquivo.close();
			ler.close();
		} catch (FileNotFoundException e) {
			try {
				File f = new File("C:\\Users\\Public\\Documents\\log.txt");
				f.createNewFile();
			} catch (IOException e1) {
				System.out.println(e1.getMessage());
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}		
	}
}