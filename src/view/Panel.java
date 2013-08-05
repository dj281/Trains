package view;
import java.awt.GridLayout;

import javax.swing.JPanel;

import javax.swing.JTabbedPane;

public class Panel extends JPanel implements Runnable {
    /* JPanel containing the JTabbedPane stuff */
    JTabbedPane jtp = new JTabbedPane();
    public Panel(){
      super(new GridLayout(1,0));
      Table t = new Table("Haymarket");
      jtp.add(t);
      jtp.setTitleAt(0, "Haymarket");
      add(jtp);
      run();
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

    @Override
    public void run()
    {
      System.out.println("hi");
      
    }
}