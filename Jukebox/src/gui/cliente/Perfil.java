package gui.cliente;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTable;

import model.ModelLocator;
import model.thread.cliente.PerfilCliente;

import jaco.mp3.player.*;

public class Perfil extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5952965218116490586L;
	private JPanel contentPane;
	private DefaultTableModel df = new DefaultTableModel();
	private JTable table;
	private JScrollPane barraRolagem;
	private JButton btnPlay;
	private JButton btnPause;
	private JButton btnStop;
	private MP3Player mpp;
	private JButton btnBaixarMusicas;

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
		
		btnBaixarMusicas = new JButton("Baixar");		
		btnBaixarMusicas.setBounds(585, 326, 89, 23);
		contentPane.add(btnBaixarMusicas);
		
		JLabel lblOl = new JLabel("Ol\u00E1, " + ModelLocator.getNome() + "! Seja bem-vindx!");
		lblOl.setBounds(10, 11, 414, 14);
		contentPane.add(lblOl);
		
		df.addColumn("Nome");
		df.addColumn("Álbum");
		df.addColumn("Artista");
		df.addColumn("Tamanho");
				
		table = new JTable(df){
			public boolean isCellEditable(int rowIndex, int colIndex) {
	            return false;
		}};
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
		
		btnPlay = new JButton("Play");
		btnPlay.setBounds(20, 326, 89, 23);
		contentPane.add(btnPlay);
		
		btnPause = new JButton("Pause");
		btnPause.setBounds(135, 326, 89, 23);
		contentPane.add(btnPause);
		
		btnStop = new JButton("Stop");
		btnStop.setBounds(249, 326, 89, 23);
		contentPane.add(btnStop);
		
		ModelLocator.setModel(df);
		
		new Thread(new PerfilCliente(df, btnPlay, btnPause, btnStop, mpp, btnBaixarMusicas, table)).start();
	}	
}