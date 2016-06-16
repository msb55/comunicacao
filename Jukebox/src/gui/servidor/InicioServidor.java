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
		
		ArrayList<Cliente> clientes = new ArrayList<Cliente>();
		clientes.add(new Cliente("232.2323","leo","las3"));
		clientes.add(new Cliente("232.2323","marcos","msb5"));
		clientes.add(new Cliente("232.2323","pedro","plal"));
		clientes.add(new Cliente("232.2323","leo","las3"));
		
		
		DefaultTableModel df = new DefaultTableModel(null,	new String[] {"Login", "IP" });
		for (Cliente cliente : clientes) {
            df.addRow(new Object[] { cliente.getLogin(),cliente.getIp()});
        }
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 481, 285);
		
		table = new JTable(){
			  public boolean isCellEditable(int row,int column){
			    return false;
			  }
			};
		
		
		
		table.addMouseListener(new MouseAdapter() { // ABRE O JFRAME STATUS
		    public void mouseClicked(MouseEvent e) {  
		        if (e.getClickCount() == 2) { 
		        	StatusCliente status = new StatusCliente();
		        	status.setLocationRelativeTo(null);
		        	status.setVisible(true);
		        	
		        	ModelLocator.setCliente(clientes.get(table.getSelectedRow()));		        	
		        	System.out.println("Nome " + ModelLocator.getCliente().getNome());
		        	
		        }  
		    }  
		}); 
		contentPane.setLayout(null);
		scrollPane.setViewportView(table);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);		
		
		table.setModel(df);
		contentPane.add(scrollPane);
		
		JButton btnAdicionarMusicas = new JButton("Adicionar Musicas");
		btnAdicionarMusicas.setBounds(10, 306, 152, 23);
		btnAdicionarMusicas.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				UploadMusica uploadMusica = new UploadMusica();
				uploadMusica.setLocationRelativeTo(null);
				uploadMusica.setVisible(true);
			}
		});
		btnAdicionarMusicas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		contentPane.add(btnAdicionarMusicas);
	}
}
