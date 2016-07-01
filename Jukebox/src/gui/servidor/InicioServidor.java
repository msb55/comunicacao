package gui.servidor;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.Cliente;
import model.ModelLocator;
import model.thread.Conexao;
import model.thread.Servidor;

import javax.swing.ListSelectionModel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import java.awt.CardLayout;

import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.FlowLayout;

import javax.swing.BoxLayout;

import java.awt.GridLayout;

import javax.swing.JLabel;


public class InicioServidor extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InicioServidor frame = new InicioServidor();
					frame.setLocationRelativeTo(null);
					frame.setResizable(false);
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
	public InicioServidor() {
		setTitle("Servidor");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 507, 379);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);	
		
		
		DefaultTableModel df = new DefaultTableModel(null,	new String[] {"Login", "IP" });
		

		table = new JTable(){
			  public boolean isCellEditable(int row,int column){
			    return false;
			  }
		};
		
		table.setModel(df);
	
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 481, 285);
		
		
		table.addMouseListener(new MouseAdapter() { // ABRE O JFRAME STATUS
		    public void mouseClicked(MouseEvent e) {  
		        if (e.getClickCount() == 2) { 
		        	ModelLocator.setCliente(ModelLocator.getClientes(table.getSelectedRow()));
		        	StatusCliente status = new StatusCliente();
		        	status.setLocationRelativeTo(null);
		        	status.setVisible(true);
		        	
		        	
		        }  
		    }  
		}); 
		contentPane.setLayout(null);
		scrollPane.setViewportView(table);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);		
		
		
		contentPane.add(scrollPane);
		
		new Thread(new Servidor(df)).start(); //INICIA O SERVIDOR
	}
}
