package gui.cliente;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.ScrollPane;
import java.awt.Toolkit;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Font;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.net.Socket;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.ListSelectionModel;

public class Musicas extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JScrollPane barraRolagem;
	private DefaultTableModel modelo = new DefaultTableModel();
	
	private Socket socket;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Musicas frame = new Musicas();
					frame.setLocationRelativeTo(null);
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
	public Musicas() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 531, 490);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblMsicasDoJukebox = new JLabel("Músicas do JukeBox");
		lblMsicasDoJukebox.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblMsicasDoJukebox.setHorizontalAlignment(SwingConstants.CENTER);
		lblMsicasDoJukebox.setBounds(10, 11, 505, 40);
		contentPane.add(lblMsicasDoJukebox);
		
		modelo.addColumn("Nome");
		modelo.addColumn("Download");
		
		modelo.addRow(new Object[]{"test",null});
		modelo.addRow(new Object[]{"teste",null});
		
		table = new JTable(modelo){
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row,int column){
				    return false;
			  }
			};
			
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
	        public void valueChanged(ListSelectionEvent event) {
	        	if(table.getSelectedColumn() == 1){
	        		Download download = new Download();
	        		download.setLocationRelativeTo(null);
	        		download.setVisible(true);
	        	}
	        }
	    });
		table.getColumnModel().getColumn(0).setPreferredWidth(400);
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(1).setCellRenderer(new TableCellRenderer() {			
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value,
					boolean isSelected, boolean hasFocus, int row, int column) {
				JLabel lbl = new JLabel();
				ImageIcon icone = new ImageIcon(getClass().getResource("/gui/imagens/download.png"));
				lbl.setIcon(icone);
				lbl.setHorizontalAlignment(0);
				return lbl;
			}
		});
		table.setBounds(10, 62, 495, 378);
		
		barraRolagem = new JScrollPane(table);
		barraRolagem.setBounds(10, 62, 505, 388);
		contentPane.add(barraRolagem);
	}
}
