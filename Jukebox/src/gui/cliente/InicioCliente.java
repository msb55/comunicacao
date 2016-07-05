package gui.cliente;

import java.awt.event.*;
import java.awt.*;
import java.net.Socket;

import javax.swing.*;

import model.ModelLocator;

public class InicioCliente extends JFrame implements ActionListener{

	//ActionListener - interface para adicionar ações

	JTextField tfIP; //Caixas
	JButton btEntrar;

	public JTextField ip;
	public JLabel textoIp;
	public ImageIcon imagem;
	public JLabel label;
	
	public InicioCliente() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(InicioCliente.class.getResource("/gui/imagens/headphones.png")));
		
		setSize(446, 296); //tamanho da tela
		setTitle("Jukebox");
		setDefaultCloseOperation(EXIT_ON_CLOSE); //Fechar o programa
		
		
		//instanciando os componentes
		tfIP = new JTextField(15);
		tfIP.setBounds(161, 8, 126, 23);
		getContentPane().setLayout(null);

		textoIp = new JLabel("Nome do Servidor:");
		textoIp.setHorizontalAlignment(SwingConstants.RIGHT);
		textoIp.setBounds(36, 12, 115, 14);
		getContentPane().add(textoIp);

		
		//Adicionar os objetos a tela
		getContentPane().add(tfIP);
		btEntrar = new JButton("In");
		btEntrar.setBounds(288, 7, 46, 23);
		getContentPane().add(btEntrar);
		
				//Adicionar ações aos componentes
				btEntrar.addActionListener(this);


		imagem = new ImageIcon(getClass().getResource("/gui/imagens/musica.png"));

		label = new JLabel(new ImageIcon(InicioCliente.class.getResource("/gui/imagens/cat.PNG")));
		label.setBounds(0, 0, 447, 272);
		getContentPane().add(label);
	}

	public static void main(String [] args) {
		
		InicioCliente framePrincipal = new InicioCliente();
		
		framePrincipal.setVisible(true); //Para a tela ficar visivel
		framePrincipal.setLocationRelativeTo(null);
		framePrincipal.setResizable(false);
		framePrincipal.getContentPane().setBackground(Color.WHITE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		try{

			//Tudo que digitar na caixinha TextField vai ser armazenado aqui
			ip = tfIP;

			if(e.getSource()==btEntrar) {
				Socket socket = new Socket(ip.getText(), 3493);
				ModelLocator.setSocketPrincipal(socket);
				ModelLocator.setIpServidor(ip.getText());
				ModelLocator.setPorta(3493);
			
				Login frameSecundario = new Login();
				frameSecundario.setLocationRelativeTo(null);
				frameSecundario.setVisible(true);
				dispose();
			}

			

		} catch(Exception e2) {
			JOptionPane.showMessageDialog(null, "Digite um endereço IP válido");
		}
	}

}