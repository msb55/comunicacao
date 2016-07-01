package model.thread.cliente;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

import model.ModelLocator;

public class RecebeMusica implements Runnable {
	
	private JButton btnCancelar;
	private JButton btnReiniciar;
	private JButton btnPlayOrPause;
	private JProgressBar progressBarDownload;
	private JLabel lblTempo;
	
	private int portaTransferencia;
	private int portaTed;
	private String nome;
	private double tamanho;
	
	public RecebeMusica(int portaTransferencia, int portaTed, JButton btnCancelar, JButton btnReiniciar, 
			JButton btnPlayOrPause, JProgressBar progressBarDownload, JLabel lblTempo, String nome, double tamanho) {
		
		this.portaTransferencia = portaTransferencia;
		this.portaTed = portaTed;
		this.btnCancelar = btnCancelar;
		this.btnReiniciar = btnReiniciar;
		this.btnPlayOrPause = btnPlayOrPause;
		this.progressBarDownload = progressBarDownload;
		this.lblTempo = lblTempo;
		this.nome = nome;
		this.tamanho = tamanho;
		
	}

	@Override
	public void run() {
		btnPlayOrPause.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (btnPlayOrPause.getText().equals("Pausar")) {
					btnPlayOrPause.setText("Continuar");
					/*try {
						while(true){
							if(!btnPlayOrPause.getText().equals("Pausar")) break;
							Thread.currentThread().sleep(Long.MAX_VALUE);
						}
					} catch (InterruptedException e) {
						System.out.println(e.getMessage());
					}*/
				} else {
					btnPlayOrPause.setText("Pausar");
				}
			}
		});
		
		try {
			Socket transferencia = new Socket(ModelLocator.getIpServidor(), this.portaTransferencia);
			Socket ted = new Socket(ModelLocator.getIpServidor(), this.portaTed);
			
			DataInputStream in = new DataInputStream(transferencia.getInputStream());
			DataOutputStream socketOut = new DataOutputStream(ted.getOutputStream());
			FileOutputStream file = new FileOutputStream("C:\\Users\\Public\\Documents\\" + nome+".mp3");
			
			byte[] buffer = new byte[512];
			int lidos, aux=0, cont=0;
			long tempoIda, tempoVolta, tempoTotal;
			double tempo=0;
			
			this.progressBarDownload.setMaximum((int) tamanho);
			
			
			tempoIda = System.nanoTime();
			while((lidos = in.read(buffer)) != -1){
				 
				file.write(buffer, 0, lidos);
				file.flush();
				
				tempoVolta = System.nanoTime();				
				tempoTotal = (tempoVolta - tempoIda)/1000;
				
				aux += lidos;
				
				this.progressBarDownload.setValue((int)aux);
				
				if(cont % 10000 == 0){
					tempo = ((tamanho-aux)*tempoTotal)/1024;
					tempo /= 1000000;
					int minuto = (int) tempo/60;
					int segundo = (int) tempo%60;
					this.lblTempo.setText(minuto + " min. " + segundo + " seg.");
				}
				
				cont++;	
				socketOut.write(1);
				tempoIda = System.nanoTime();
			}
			
			file.close();
			transferencia.close();
			ted.close();			
		} catch (UnknownHostException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}