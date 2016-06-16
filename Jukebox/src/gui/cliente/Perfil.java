package gui.cliente;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTable;

import model.ModelLocator;

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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 573, 399);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnBaixarMusicas = new JButton("Baixar");
		btnBaixarMusicas.setBounds(458, 326, 89, 23);
		contentPane.add(btnBaixarMusicas);
		
		JLabel lblOl = new JLabel("Ol\u00E1, " + ModelLocator.getCliente() + "! Seja bem-vindx!");
		lblOl.setBounds(10, 11, 414, 14);
		contentPane.add(lblOl);
		
		df.addColumn("Nome");
		df.addColumn("Artista");
		df.addColumn("Duração");
		
		carregarMusicas();
		
		table = new JTable(df);
		table.setBounds(10, 36, 537, 279);
		contentPane.add(table);
		
		barraRolagem = new JScrollPane(table);
		barraRolagem.setBounds(10, 36, 537, 279);
		contentPane.add(barraRolagem);		
	}
	
	public void carregarMusicas(){		
		try {
			File f = new File("C:\\Users\\Public\\Documents\\log.txt");
			FileReader ler = new FileReader(f);
			BufferedReader lerArquivo = new BufferedReader(ler);
			
			do{
				String nome = lerArquivo.readLine();
				String artista = lerArquivo.readLine();
				String duracao = lerArquivo.readLine();
				
				Object[] obj = {nome, artista, duracao};
				
				df.addRow(obj);				
			}while(lerArquivo.readLine() != null);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
}
