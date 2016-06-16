package gui.cliente;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTable;

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
		
		String nome = "Paula"; //adicionar parâmetro
		JLabel lblOl = new JLabel("Ol\u00E1, " + nome + "! Seja bem-vinda!");
		lblOl.setBounds(10, 11, 414, 14);
		contentPane.add(lblOl);
		
		df.addColumn("Nome");
		df.addColumn("Artista");
		df.addColumn("Duração");
		
		table = new JTable(df);
		table.setBounds(10, 36, 537, 279);
		contentPane.add(table);
		
		barraRolagem = new JScrollPane(table);
		barraRolagem.setBounds(10, 36, 537, 279);
		contentPane.add(barraRolagem);
		
		
	}
}
