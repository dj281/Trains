package view;

import java.awt.BorderLayout;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.File;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableRowSorter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import model.TrainTableModel;


public class Panel extends JPanel {
    /* JPanel containing the JTabbedPane stuff */
    JTabbedPane jtp = new JTabbedPane();
    private Document doc;
    public Panel(){
      super(new GridLayout(1,0));
      Table t = new Table("Haymarket");
      jtp.add(t);
      jtp.setTitleAt(0, "Haymarket");
      add(jtp);
       }
 
    public void addTrain(String d, String c, String dep, String a)
    {
      /* Receives 4 parameters to control the new entry required for a train */
      Table t = (Table) jtp.getComponentAt(jtp.getSelectedIndex());
      t.addTrain(d,c,dep,a);
    }
    public void addStation(String s){
      /* Adds a station, after prompting the user for a station name */
      jtp.add(new Table(s));
      jtp.setTitleAt(jtp.getTabCount()-1, s);
      jtp.setSelectedIndex(jtp.getTabCount()-1);
    }
    public void addStation(Table t){
      jtp.add(t);
      jtp.setTitleAt(jtp.getTabCount()-1,t.getStation());
    }
    
    public void removeAllStations(){
      jtp.removeAll();
    }
    
    
    public void removeStation()
    {
      /* Removes currently selected station */
      jtp.remove(jtp.getSelectedIndex());
    }

    public void removeTrain()
    {
      /* WIP: Removes a train */
      Table t = (Table) jtp.getComponentAt(jtp.getSelectedIndex());
      t.removeTrain();
    }
}