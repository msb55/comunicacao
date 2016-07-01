package model.thread.servidor;

import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

import javax.swing.JProgressBar;
import javax.swing.table.AbstractTableModel;


public class DownloadTableModel extends AbstractTableModel implements Observer {
   // holds the strings to be displayed in the column headers of our table
   final String[] columnNames = {"Musica", "Tamanho", "Progresso", "Tempo Estimado"};
  
   // holds the data types for all our columns
   final Class[] columnClasses = {String.class, String.class, JProgressBar.class, String.class};
  
   // holds our data
   final Vector data = new Vector();
   
   // adds a row
   public void addDownload(Download d) {
      data.addElement(d);
    
      // the table model is interested in changes of the rows
      d.addObserver(this);
      fireTableRowsInserted(data.size()-1, data.size()-1);
   }
  
   // is called by a download object when its state changes
   public void update(Observable observable, Object o) {
      int index = data.indexOf(o);
      if (index != -1)
         fireTableRowsUpdated(index, index);
   }
  
   public int getColumnCount() {
      return columnNames.length;
   }
          
   public int getRowCount() {
      return data.size();
   }
  
   public String getColumnName(int col) {
      return columnNames[col];
   }
  
   public Class getColumnClass(int c) {
      return columnClasses[c];
   }
  
   public Object getValueAt(int row, int col) {
      Download download = (Download) data.elementAt(row);
      if (col == 0)      return download.getMusica();
      else if (col == 1){
    	  Double tamanho = Double.parseDouble(((download.getFilesize()/1024)/1024)+"");
    	  return new String(String.format("%.2f",tamanho)+"MB");
      }
      else if (col == 2) return new Float(download.getProgress());
      else if (col == 3) return new String(download.getTempoEstimado()+"");
      else return null;
   }
  
   public boolean isCellEditable(int row, int col) {
      return false;
   }
}