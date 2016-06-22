package gui.cliente;

import java.awt.Component;
import java.awt.EventQueue;

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
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import model.ModelLocator;

public class Musicas extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JScrollPane barraRolagem;
	private DefaultTableModel modelo = new DefaultTableModel();
	
	//private Socket socket;

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
		modelo.addColumn("Tamanho");
		modelo.addColumn("Download");
		
		carregarMusicas();
		atualizaLista();
		
		table = new JTable(modelo){
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row,int column){
				    return false;
			  }
			};
			
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
	        public void valueChanged(ListSelectionEvent event) {
	        	if(table.getSelectedColumn() == 1){
	        		ModelLocator.setNomeMusicas(modelo.getValueAt(table.getSelectedRow(), 0).toString());
	        		ModelLocator.setTamanhoMusicas(Double.parseDouble(modelo.getValueAt(table.getSelectedRow(), 1).toString()));
	        		
	        		try {
						DataOutputStream socketOut = new DataOutputStream(ModelLocator.getSocketPrincipal().getOutputStream());
						socketOut.writeBytes(ModelLocator.getNomeMusicas() + ".mp3" + "\n");
						
						BufferedReader socketIn = new BufferedReader(new InputStreamReader(ModelLocator.getSocketPrincipal().getInputStream()));
						String portas = socketIn.readLine();
						
						String p[] = portas.split("-");
						ModelLocator.setPorta1(Integer.parseInt(p[0]));
						ModelLocator.setPorta2(Integer.parseInt(p[1]));
					} catch (IOException e) {
						JOptionPane.showMessageDialog(null, "Erro na solicitação da música.");
					}
	        		
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
	
	public void carregarMusicas(){
		try {
			Socket conexao = new Socket(ModelLocator.getIpServidor(), 3502);
			
			DataInputStream in = new DataInputStream(conexao.getInputStream());
			FileOutputStream file = new FileOutputStream("C:\\Users\\Public\\Documents\\logServidor.txt");
			
			byte[] buffer = new byte[512];
			int lidos;
			while((lidos = in.read(buffer)) != -1){
				file.write(buffer, 0, lidos);
				file.flush();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void atualizaLista(){
		try {
			File f = new File("C:\\Users\\Public\\Documents\\logServidor.txt");
			FileReader ler = new FileReader(f);
			BufferedReader lerArquivo = new BufferedReader(ler);
			
			String nome = lerArquivo.readLine();
			while(nome != null){
				String tamanho = lerArquivo.readLine();
				Object[] obj = {nome, tamanho, null};
				
				modelo.addRow(obj);
				
				nome = lerArquivo.readLine();
			}
			
			lerArquivo.close();
			ler.close();
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Não existe músicas no servidor.");
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}
