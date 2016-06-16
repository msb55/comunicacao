package gui.cliente;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;

import model.Cliente;
import model.ModelLocator;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.DataOutputStream;
import java.io.IOException;

public class Login extends JDialog {
	private JTextField txtNome;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Login dialog = new Login();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Login() {
		setBounds(100, 100, 450, 175);
		getContentPane().setLayout(null);
		
		txtNome = new JTextField();
		txtNome.setBounds(66, 57, 259, 20);
		getContentPane().add(txtNome);
		txtNome.setColumns(10);
		
		JButton btnEntrar = new JButton("Entrar");
		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					DataOutputStream out = new DataOutputStream(ModelLocator.getSocketPrincipal().getOutputStream());
					out.writeBytes(txtNome.getText() + "\n");
					
					ModelLocator.setCliente(txtNome.getText());
					
					Perfil perfil = new Perfil();
					perfil.setLocationRelativeTo(null);
					perfil.setVisible(true);
					dispose();					
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnEntrar.setBounds(335, 54, 89, 27);
		getContentPane().add(btnEntrar);
				
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(10, 60, 46, 14);
		getContentPane().add(lblNome);
	}
}
