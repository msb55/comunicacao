package gui.cliente;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JProgressBar;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Download extends JDialog {

	private final JPanel contentPanel = new JPanel();

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
		setBounds(100, 100, 587, 388);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JProgressBar progressBarDownload = new JProgressBar();
		progressBarDownload.setBounds(35, 91, 500, 37);
		progressBarDownload.setValue(0);
		contentPanel.add(progressBarDownload);
		
		JLabel lblNomeArquivo = new JLabel("Progresso...");
		lblNomeArquivo.setBounds(35, 51, 87, 16);
		contentPanel.add(lblNomeArquivo);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(438, 156, 97, 25);
		contentPanel.add(btnCancelar);
		
		final JButton btnPlayOrPause = new JButton("Pausar"); //play \u25BA pause \u23F8
		btnPlayOrPause.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (btnPlayOrPause.getText().equals("Pausar")) {
					btnPlayOrPause.setText("Continuar");
				} else {
					btnPlayOrPause.setText("Pausar");
				}
			}
		});
		btnPlayOrPause.setBounds(35, 156, 97, 25);
		contentPanel.add(btnPlayOrPause);
	}
}
