package gui.servidor;


import java.awt.EventQueue;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
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
					frame.setLocationRelativeTo(null);
					frame.setResizable(false);
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
		setBounds(100, 100, 507, 343);
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
		
		
		contentPane.setLayout(null);
		contentPane.setLayout(null);
		scrollPane.setViewportView(table);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);		
		
		table.setModel(df);
		contentPane.add(scrollPane);
	}
	

}
