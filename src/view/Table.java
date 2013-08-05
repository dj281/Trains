package view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableRowSorter;

import model.TrainTableModel;

@SuppressWarnings("serial")
public class Table extends JPanel
{
  /* The Table class has an instance for each station */
  private TableRowSorter<TrainTableModel> sorter;
  private JTable table;
  private JTextField filterText;
  TrainTableModel model;
  String station;
  public Table(String s){
    station = s;
    model = new TrainTableModel(s);
    sorter = new TableRowSorter<TrainTableModel>(model);
    table = new JTable(model);
    sorter.toggleSortOrder(3);
    table.setRowSorter(sorter);
    table.setPreferredScrollableViewportSize(new Dimension(500,70));
    table.setFillsViewportHeight(true);
    JScrollPane scrollPane = new JScrollPane(table);
    add(scrollPane);
    JPanel form = new JPanel();
        JLabel l1 = new JLabel("Filter Text:");
        form.add(l1, BorderLayout.WEST);
        filterText = new JTextField();
        filterText.setPreferredSize(new Dimension(400,30));
        //Whenever filterText changes, invoke newFilter.
        filterText.getDocument().addDocumentListener(
                new DocumentListener() {
                    public void changedUpdate(DocumentEvent e) {
                        newFilter();
                    }
                    public void insertUpdate(DocumentEvent e) {
                        newFilter();
                    }
                    public void removeUpdate(DocumentEvent e) {
                        newFilter();
                    }
                });
        
        form.add(filterText, BorderLayout.SOUTH);
        add(form,BorderLayout.SOUTH);
  }
  private void newFilter() {
        RowFilter<TrainTableModel, Object> rf = null;
        //If current expression doesn't parse, don't update.
        try {
            rf = RowFilter.regexFilter("(?i)" + filterText.getText());
        } catch (java.util.regex.PatternSyntaxException e) {
            return;
        }
        sorter.setRowFilter(rf);
        
    }
  public void addTrain(String d, String c, String dep, String a)
  {
    /* Adds a train to the end of the currently selected station */
    Object[][] oldData = model.getData();
   // Object[][] newdata = {{station,d,c,dep,a}};
    Object[][] newData = new Object[oldData.length+1][];
    
    for(int x=0;x<oldData.length;x++){
      newData[x] = oldData[x];
    }
    newData[oldData.length] = new Object[]{station,d,c,dep,a};
    
    model.setData(newData);
    
    table.revalidate();
    table.repaint();
    
    model.fireTableStructureChanged();
  }
  public void removeTrain()
  {
    /* WIP: Remove Train */
    model.remove(table.getSelectedRow());
  }
}
