package gui.servidor;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import model.DownloadTableModel;
import model.ModelLocator;
import model.ProgressBarRenderer;

import java.awt.Toolkit;

public class StatusCliente extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			StatusCliente dialog = new StatusCliente();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
			dialog.setResizable(false);
			dialog.setLocationRelativeTo(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public StatusCliente() {
		setTitle("Downloads");
		setIconImage(Toolkit.getDefaultToolkit().getImage(StatusCliente.class.getResource("/gui/imagens/headphones.png")));
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		DownloadTableModel downloadModel = ModelLocator.getCliente().getTableModelDownloads();
		JTable table = new JTable(downloadModel);
		  
		
		  
		      // render the columns with class JProgressBar as such
		      ProgressBarRenderer pbr = new ProgressBarRenderer(0, 100);
		      pbr.setStringPainted(true);
		      table.setDefaultRenderer(JProgressBar.class, pbr);
		    
		      // increase the height of the rows a bit
		      table.setRowHeight((int) pbr.getPreferredSize().getHeight());
		  
		      // create the scroll pane and add the table to it.
		      JScrollPane scrollPane = new JScrollPane(table);
		  
		      // add the scroll pane to this window.
		      getContentPane().add(scrollPane, BorderLayout.CENTER);
	}

}
