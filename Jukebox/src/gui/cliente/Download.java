package gui.cliente;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JProgressBar;
import javax.swing.JLabel;

import model.ModelLocator;
import model.thread.RecebeMusica;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Download extends JDialog {

	private final JPanel contentPanel = new JPanel();

	private JButton btnCancelar;
	private JButton btnReiniciar;
	private JButton btnPlayOrPause;
	private JProgressBar progressBarDownload;
	private JLabel lblTempo;
	
	private JLabel lblNomeMusica;
	private JLabel lblTamanhoMusica;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Download dialog = new Download();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Download() {	
		setTitle("Download");
		setBounds(100, 100, 587, 258);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		progressBarDownload = new JProgressBar();
		progressBarDownload.setBounds(35, 91, 500, 37);
		progressBarDownload.setValue(0);
		contentPanel.add(progressBarDownload);
		
		JLabel lblNomeArquivo = new JLabel("Progresso...");
		lblNomeArquivo.setBounds(35, 62, 87, 16);
		contentPanel.add(lblNomeArquivo);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(438, 156, 97, 25);
		contentPanel.add(btnCancelar);
		
		btnPlayOrPause = new JButton("Pausar"); //play \u25BA pause \u23F8
		
		btnPlayOrPause.setBounds(35, 156, 97, 25);
		contentPanel.add(btnPlayOrPause);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(35, 13, 56, 16);
		contentPanel.add(lblNome);
		
		lblNomeMusica = new JLabel("...");
		lblNomeMusica.setBounds(101, 13, 195, 16);
		contentPanel.add(lblNomeMusica);
		
		JLabel lblTamanho = new JLabel("Tamanho:");
		lblTamanho.setBounds(35, 33, 71, 16);
		contentPanel.add(lblTamanho);
		
		lblTamanhoMusica = new JLabel("...");
		lblTamanhoMusica.setBounds(104, 33, 192, 16);
		contentPanel.add(lblTamanhoMusica);
		
		JLabel lblTempoRestante = new JLabel("Tempo Restante:");
		lblTempoRestante.setBounds(35, 129, 117, 16);
		contentPanel.add(lblTempoRestante);
		
		lblTempo = new JLabel("0");
		lblTempo.setBounds(158, 129, 195, 16);
		contentPanel.add(lblTempo);
		
		btnReiniciar = new JButton("Reiniciar");
		btnReiniciar.setBounds(234, 156, 97, 25);
		contentPanel.add(btnReiniciar);
		
		iniciar();
		
		new Thread(new RecebeMusica(ModelLocator.getPorta1(), ModelLocator.getPorta2(), btnCancelar, btnReiniciar, btnPlayOrPause, 
				progressBarDownload, lblTempo, ModelLocator.getNomeMusicas(), ModelLocator.getTamanhoMusicas())).start();
	}
	
	public void iniciar(){
		lblNomeMusica.setText(ModelLocator.getNomeMusicas());
		lblTamanhoMusica.setText(""+ModelLocator.getTamanhoMusicas());		
	}
}
