package gui.servidor;


import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;



import model.ModelLocator;
import model.thread.servidor.Servidor;

import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;
import java.awt.Toolkit;



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
		setIconImage(Toolkit.getDefaultToolkit().getImage(InicioServidor.class.getResource("/gui/imagens/headphones.png")));
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
		
		
		File raiz = new File("C:\\Users\\Public\\Documents\\JukeBox");
		try {
			File file = new File("C:\\Users\\Public\\Documents\\JukeBox\\log.txt");
			BufferedWriter bf = new BufferedWriter(new FileWriter(file));
		
			for(File f: raiz.listFiles()) {
				if(f.isFile() && !f.getName().equals("log.txt")) {
					bf.write(f.getName());
					bf.newLine();
					bf.write(f.length()+"");
					bf.newLine();
				}
			}
			bf.close();		
		} catch (IOException e1) {
			System.out.println(e1);
		}
		
		
		table.addMouseListener(new MouseAdapter() { // ABRE O JFRAME STATUS
		    public void mouseClicked(MouseEvent e) {  
		        if (e.getClickCount() == 2) { 
		        	ModelLocator.setCliente(ModelLocator.getClientes(table.getSelectedRow()));
		        	StatusCliente status = new StatusCliente();
		        	status.setLocationRelativeTo(null);
		        	status.setModal(true);
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
