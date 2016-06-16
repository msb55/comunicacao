package gui.servidor;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import model.Cliente;
import model.ModelLocator;
import model.Musica;

public class UploadMusica extends JFrame {

	private JPanel contentPane;
	private JTable table; 
	private JFileChooser fileChooser;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UploadMusica frame = new UploadMusica();
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
	public UploadMusica() {
		setTitle("Musicas");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 507, 379);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		
		ArrayList<Musica> musicas = new ArrayList<Musica>();
		musicas.add(new Musica("Reckless Serenade","Arctic Monkeys","Suck It And See"));
		musicas.add(new Musica("Eskimo Kiss","The Kooks","Junk Of The Heart"));
		musicas.add(new Musica("Empire","Of Monsters And Men","Beneath The Skin"));
				
		
		DefaultTableModel df =  new DefaultTableModel();
		
		
		df.addColumn("Nome");
		df.addColumn("Artista");
		df.addColumn("Duração");
		
		for (Musica musica : musicas) {
            df.addRow(new Object[] { musica.getNome(),musica.getArtista(),musica.getAlbum()});
        }
		
		table = new JTable(df){
			  public boolean isCellEditable(int row,int column){
			    return false;
			  }
			};
		table.setBounds(10, 36, 537, 279);		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 481, 285);
		
		
		JButton btnArquivo = new JButton("Adicionar");
	
		btnArquivo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {			
			       
			        searchFile();			       
			
			}			
		
		});
		
		
		btnArquivo.setBounds(10, 306, 89, 23);
		contentPane.add(btnArquivo);
		
		
		contentPane.setLayout(null);
		contentPane.setLayout(null);
		scrollPane.setViewportView(table);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);		
		
		table.setModel(df);
		contentPane.add(scrollPane);
	}
	
	
	
	private void searchFile(){
		
		fileChooser = new JFileChooser();
			
			switch(fileChooser.showOpenDialog(this)){
				case JFileChooser.APPROVE_OPTION:				
					//txtPathFile.setText(fileChooser.getSelectedFile().getPath());				
					break;					
			}			
	}
	

}
