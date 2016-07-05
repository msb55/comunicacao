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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.awt.Toolkit;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

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
			dialog.setLocationRelativeTo(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Login() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/gui/imagens/headphones.png")));
		setTitle("Login");
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		setSize(446, 296); //tamanho da tela
		getContentPane().setLayout(null);
		
		txtNome = new JTextField();
		txtNome.setBounds(70, 11, 259, 23);
		getContentPane().add(txtNome);
		txtNome.setColumns(10);
		
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				try {
					ModelLocator.getSocketPrincipal().close();
					System.exit(0);
				} catch (IOException e1) {
					System.out.println(e1.getMessage());
				}
			}
		});
		
		JButton btnEntrar = new JButton("In");
		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {				
					DataOutputStream out = new DataOutputStream(ModelLocator.getSocketPrincipal().getOutputStream());
					out.writeBytes(txtNome.getText() + "\n");
					
					ModelLocator.setNome(txtNome.getText());
					
					Perfil perfil = new Perfil();
					perfil.setLocationRelativeTo(null);
					perfil.setVisible(true);
					dispose();					
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnEntrar.setBounds(339, 11, 46, 23);
		getContentPane().add(btnEntrar);
				
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNome.setBounds(14, 15, 46, 14);
		getContentPane().add(lblNome);
		
		JLabel label = new JLabel(new ImageIcon(Login.class.getResource("/gui/imagens/cat.PNG")));
		label.setBounds(0, 0, 447, 272);
		getContentPane().add(label);
	}
}
