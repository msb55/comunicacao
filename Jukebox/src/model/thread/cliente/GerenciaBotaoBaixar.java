package model.thread.cliente;

import gui.cliente.Musicas;

import javax.swing.JButton;

public class GerenciaBotaoBaixar implements Runnable{
	
	private Musicas tela;
	private JButton botao;

	public GerenciaBotaoBaixar(Musicas tela, JButton botao) {
		this.tela = tela;
		this.botao = botao;
	}
	
	@Override
	public void run() {
		while(true){
			if(this.tela.isVisible()) this.botao.setEnabled(false);
			else{
				this.botao.setEnabled(true);
				Thread.currentThread().interrupt();
				break;
			}
		}
	}
}