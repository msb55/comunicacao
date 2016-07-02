package gui.cliente;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTable;

import model.ModelLocator;
import model.thread.cliente.Online;
import model.thread.servidor.Servidor;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Perfil extends JFrame {

	private JPanel contentPane;
	private DefaultTableModel df = new DefaultTableModel();
	private JTable table;
	private JScrollPane barraRolagem;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Perfil frame = new Perfil();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Perfil() {
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 399);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JButton btnBaixarMusicas = new JButton("Baixar");
		btnBaixarMusicas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Musicas musicas = new Musicas();
				musicas.setLocationRelativeTo(null);
				musicas.setModal(true);
				musicas.setVisible(true);
				
				
			}
		});
		btnBaixarMusicas.setBounds(585, 326, 89, 23);
		contentPane.add(btnBaixarMusicas);
		
		JLabel lblOl = new JLabel("Ol\u00E1, " + ModelLocator.getNome() + "! Seja bem-vindx!");
		lblOl.setBounds(10, 11, 414, 14);
		contentPane.add(lblOl);
		
		df.addColumn("Nome");
		df.addColumn("Álbum");
		df.addColumn("Artista");
		df.addColumn("Tamanho");
		
		carregarMusicas();
		
		table = new JTable(df);
		table.setBounds(10, 36, 537, 279);
		contentPane.add(table);
		
		table.getColumnModel().getColumn(0).setPreferredWidth (150);
		table.getColumnModel().getColumn(1).setPreferredWidth (150);
		table.getColumnModel().getColumn(2).setPreferredWidth (150);
		table.getColumnModel().getColumn(3).setPreferredWidth (1);
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(3).setResizable(false);
		
		barraRolagem = new JScrollPane(table);
		barraRolagem.setBounds(10, 36, 664, 279);
		contentPane.add(barraRolagem);
		
		
		Socket transferencia = null;
		try {
			transferencia = new Socket(ModelLocator.getIpServidor(), 3000);
		} catch (IOException e) {
			e.printStackTrace();
		}
		new Thread(new Online(transferencia)).start();
	}
	
	public void carregarMusicas(){		
		try {
			File f = new File("C:\\Users\\Public\\Documents\\log.txt");
			FileReader ler = new FileReader(f);
			BufferedReader lerArquivo = new BufferedReader(ler);
			
			String nome = lerArquivo.readLine();
			while(nome != null){
				String album = lerArquivo.readLine();
				String artista = lerArquivo.readLine();
				String tamanho = lerArquivo.readLine();
				
				Object[] obj = {nome, album, artista, tamanho};
				
				df.addRow(obj);
				
				nome = lerArquivo.readLine();
			}
			
			lerArquivo.close();
			ler.close();
		} catch (FileNotFoundException e) {
			try {
				File f = new File("C:\\Users\\Public\\Documents\\log.txt");
				f.createNewFile();
			} catch (IOException e1) {
				System.out.println(e1.getMessage());
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}		
	}
}
