package gui.cliente;

import java.awt.EventQueue;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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
import model.thread.cliente.PerfilCliente;

import jaco.mp3.player.*;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

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
		setTitle("Jukebox");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Perfil.class.getResource("/gui/imagens/headphones.png")));
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 399);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnBaixarMusicas = new JButton("");		
		btnBaixarMusicas.setIcon(new ImageIcon(Perfil.class.getResource("/gui/imagens/cloud.png")));
		btnBaixarMusicas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnBaixarMusicas.setBounds(592, 319, 82, 41);
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
		
		btnPlay = new JButton("");
		btnPlay.setIcon(new ImageIcon(Perfil.class.getResource("/gui/imagens/play.png")));
		btnPlay.setBounds(10, 319, 82, 41);
		contentPane.add(btnPlay);
		
		btnPause = new JButton("");
		btnPause.setIcon(new ImageIcon(Perfil.class.getResource("/gui/imagens/pause.png")));
		btnPause.setBounds(102, 319, 82, 41);
		contentPane.add(btnPause);
		
		btnStop = new JButton("");
		btnStop.setIcon(new ImageIcon(Perfil.class.getResource("/gui/imagens/stop.png")));
		btnStop.setBounds(194, 319, 82, 41);
		contentPane.add(btnStop);
		
		ModelLocator.setModel(df);
		
		new Thread(new PerfilCliente(df, btnPlay, btnPause, btnStop, mpp, btnBaixarMusicas, table)).start();
	}	
}