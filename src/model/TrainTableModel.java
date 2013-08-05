package model;

import java.awt.Container;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.JTableHeader;

@SuppressWarnings("serial")
public class TrainTableModel extends AbstractTableModel
/*AbstractTableModel representing the trains and stuff */
{
  private String[] columnNames = {"From","To", "Calling At", "Departure Time","Arrival Time"};
  private Object[][] haymarket = {{"Haymarket", "Glasgow Queen Street","Falkirk High", "13:19", "14:06"}, {"Haymarket", "Edinburgh", "", "13:22", "13:28"}};
  private Object[][] data;
  private Object[][] newData;
  public TrainTableModel(){
  }
  public TrainTableModel(String s){
    /* Predefined names will present a default list of trains */
    if(s!=null){
    switch (s){
      case "Haymarket": 
        data = haymarket;
        break;
        default:
          break;
    }
    }
  }
  public Object[][] getData(){
    return data;
  }
  public void setData(Object[][] data){
    this.data = data;
  }
  @Override
  public int getColumnCount()
  {
    return columnNames.length;
  }

  @Override
  public String getColumnName(int index) {
      return columnNames[index];
  }
  
  @Override
  public int getRowCount()
  {
    if(data!=null)
    return data.length;
    else
      return 0;
  }

  @Override
  public Object getValueAt(int row, int col)
  {
    if(data!=null)
    return data[row][col];
    else
      return 0;
  }
  
  @SuppressWarnings("unchecked")
  public Class getColumnClass(int c){
    return getValueAt(0,c).getClass();
  }

  @Override
  public void setValueAt(Object value, int row, int col){
    data[row][col] = value;
    fireTableCellUpdated(row,col);
  }
  public void remove(int selectedRow)
  {
    /* WIP: Still to complete. Delete a row */
    //System.out.println(getColumnCount() + " " + getRowCount());
    Object[][] newData = new Object[0][4];
    int p = 0;
    for(int x=0;x<2;x++){
      if(x != selectedRow){
        for(int y = 0;y<4;y++){
          System.out.println(p + " " + x + " " + y);
          System.out.println(data[0][0]);
        newData[0][0] = data[0][0];
        
        }p++;
      }
    }
  }
  
}