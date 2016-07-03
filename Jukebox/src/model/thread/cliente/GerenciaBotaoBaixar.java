package model.thread.cliente;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import gui.cliente.Musicas;

import javax.swing.JButton;
import javax.swing.JFrame;

public class GerenciaBotaoBaixar implements Runnable{
	
	private Musicas tela;
	private JButton botao;

	public GerenciaBotaoBaixar(Musicas tela, JButton botao) {
		this.tela = tela;
		this.botao = botao;
	}
	
	@Override
	public void run() {
		this.botao.setEnabled(false);
		
		this.tela.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		this.tela.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				botao.setEnabled(true);
				tela.dispose();
				
				Thread.currentThread().interrupt();
			}
		});
	}
}