package gui.cliente;

import java.awt.event.*;
import java.awt.*;
import java.net.Socket;

import javax.swing.*;

import model.ModelLocator;

public class InicioCliente extends JFrame implements ActionListener{

	//ActionListener - interface para adicionar ações

	JTextField tfIP; //Caixas
	JButton btEntrar, btSair;

	public JTextField ip;
	public JLabel textoIp;
	public ImageIcon imagem;
	public JLabel label;
	
	public InicioCliente() {
		
		setSize(600, 285); //tamanho da tela
		setTitle("Jukebox");
		setDefaultCloseOperation(EXIT_ON_CLOSE); //Fechar o programa
		
		
		//instanciando os componentes
		tfIP = new JTextField(15);
		btSair = new JButton("Sair");

		//Definindo o Layout
		getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER)); 

		textoIp = new JLabel("Digite seu endereço IP:");
		getContentPane().add(textoIp);

		
		//Adicionar os objetos a tela
		getContentPane().add(tfIP);
		btEntrar = new JButton("Entrar");
		getContentPane().add(btEntrar);
		
				//Adicionar ações aos componentes
				btEntrar.addActionListener(this);
		getContentPane().add(btSair);
		btSair.addActionListener(this);


		imagem = new ImageIcon(getClass().getResource("/gui/imagens/musica.png"));

		label = new JLabel(new ImageIcon(InicioCliente.class.getResource("/gui/imagens/musica.png")));
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

			if(e.getSource()==btSair) {

				System.exit(0);

			}

		} catch(Exception e2) {
			JOptionPane.showMessageDialog(null, "Digite um endereço IP válido");
		}
	}

}