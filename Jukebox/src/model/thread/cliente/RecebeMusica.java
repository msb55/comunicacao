package model.thread.cliente;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JDialog;
import org.blinkenlights.jid3.ID3Exception;
import org.blinkenlights.jid3.MP3File;
import org.blinkenlights.jid3.v1.ID3V1Tag;

import gui.cliente.Download;
import model.ModelLocator;

public class RecebeMusica implements Runnable {
	
	private String nome;
	private double tamanho;	
	private Download tela;	
	private int transferencia;
	private int ted;	
	private boolean pausa;
	private boolean cancelar;
	private boolean reiniciar;
	

	
	public RecebeMusica(int portaTransferencia, int portaTed, String nome, double tamanho, JDialog tela) {
		this.transferencia = portaTransferencia;
		this.ted = portaTed;
		this.nome = nome;
		this.tamanho = tamanho;		
		this.tela = (Download) tela;
		this.pausa = false;
		this.cancelar = false;
		this.reiniciar = false;
	}

	@Override
	public void run() {
		this.tela.getBtnPlayOrPause().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (tela.getBtnPlayOrPause().getText().equals("Pausar")) {
					tela.getBtnPlayOrPause().setText("Continuar");
					pausa = true;
					System.out.println(pausa);
				} else {
					tela.getBtnPlayOrPause().setText("Pausar");
					pausa = false;
					System.out.println(pausa);
				}
			}
		});
		
		this.tela.getBtnCancelar().addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				cancelar = true;
			}
		});
		
		this.tela.getBtnReiniciar().addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				reiniciar = true;
			}
		});
		
		try {
			Socket transferencia = new Socket(ModelLocator.getIpServidor(), this.transferencia);
			Socket ted = new Socket(ModelLocator.getIpServidor(), this.ted);
			
			transferencia.setSoTimeout(180000);
			ted.setSoTimeout(180000);
			
			DataInputStream in = new DataInputStream(transferencia.getInputStream());
			DataOutputStream socketOut = new DataOutputStream(ted.getOutputStream());
			FileOutputStream file = new FileOutputStream("C:\\Users\\Public\\Documents\\" + nome);
			
			byte[] buffer = new byte[1024*15];
			int lidos, aux=0, cont=0;
			long tempoIda = 0, tempoVolta, tempoTotal,TempoAcumulado=0;
			double tempo=0;
			boolean concluido = false;
			this.tela.getProgressBarDownload().setMaximum((int) tamanho);			
			
			
			tempoIda = System.nanoTime();
			
			while((lidos = in.read(buffer)) != -1){
				
				
				
				file.write(buffer, 0, lidos);
				file.flush();
				
							
				cont++;
				tempoVolta = System.nanoTime();	
				TempoAcumulado += (tempoVolta - tempoIda)/1000;
				tempoTotal = TempoAcumulado/cont;
				
				aux += lidos;
				if(aux >= tamanho)concluido = true;
				this.tela.getProgressBarDownload().setValue((int)aux);
				
				if(cont % 2 == 0){
					tempo = ((tamanho-aux)*tempoTotal)/(1024*15);
					tempo /= 1000000;
					int minuto = (int) tempo/60;
					int segundo = (int) tempo%60;
					this.tela.getLblTempo().setText(minuto + " min. " + segundo + " seg.");
				}
				
				
				while(pausa & !cancelar &!reiniciar){
					System.out.print("");
				}
				
				if(reiniciar){
					socketOut.write(2);
					file.close();
					new File("C:\\Users\\Public\\Documents\\" + nome).delete();
					file = new FileOutputStream("C:\\Users\\Public\\Documents\\" + nome);
					buffer = new byte[1024*15];
					lidos = 0; aux = 0; cont = 0; tempo = 0;TempoAcumulado=0;
					reiniciar = false;
					continue;
				}				
				
				if(cancelar){					
					transferencia.close();
					ted.close();
					file.close();
					in.close();
					socketOut.close();
					
					new File("C:\\Users\\Public\\Documents\\" + nome).delete();
					this.tela.dispose();
					
					break;
				}
				tempoIda = System.nanoTime();
				socketOut.write(1);
				
			}
			
			
			if(concluido){
				
				try {
					File musica = new File("C:\\Users\\Public\\Documents\\" + nome);
					ID3V1Tag tag = new MP3File(musica).getID3V1Tag();
					Object[] obj = null;
					
					try{
						tag.getArtist();
						tag.getAlbum();
						Object[] array = {nome, tag.getAlbum(), tag.getArtist(), musica.length()};
						obj = array;
					}catch(NullPointerException n){
						Object[] array = {nome, "","", musica.length()};
						obj = array;
					}
					ModelLocator.getModel().addRow(obj);
					
					File f = new File("C:\\Users\\Public\\Documents\\log.txt");
					BufferedWriter bf = new BufferedWriter(new FileWriter(f));
					
					int i = ModelLocator.getModel().getRowCount();
					
					while(i>0){
						bf.write(ModelLocator.getModel().getValueAt(i-1, 0).toString());
						bf.newLine();
						bf.write(ModelLocator.getModel().getValueAt(i-1, 1).toString());
						bf.newLine();
						bf.write(ModelLocator.getModel().getValueAt(i-1, 2).toString());
						bf.newLine();
						bf.write(ModelLocator.getModel().getValueAt(i-1, 3).toString());
						bf.newLine();
						
						i--;
					}
					
					bf.close();
				} catch (ID3Exception e) {					
					System.out.println(e.getMessage());
				}
				
				file.close();
				
			}
			
			transferencia.close();
			ted.close();
			
			Thread.currentThread().interrupt();
		} catch (UnknownHostException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}