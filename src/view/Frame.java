package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.*;

import model.Parser;

public class Frame extends JFrame
{
  Frame frame;
  Panel p = new Panel();
  Object[][] newData, oldData;
  
  public Frame(){
    setVisible(true);
    setSize(new Dimension(100,100));
    setPreferredSize(new Dimension(550,250));
    setMaximumSize(new Dimension(100,100));
    setMinimumSize(new Dimension(100,100));
    createJMenuBar();
    //p.setOpaque(true);
    add(p);
    setContentPane(p);
    pack();
    this.frame=this;
  }

  private void createJMenuBar()
  {
    JMenuBar mainMenu = new JMenuBar();
    JMenu fileMenu = new JMenu("File");
    JMenuItem imp = new JMenuItem("Import");
    imp.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        p.removeAllStations();
        JFileChooser fc = new JFileChooser();
        int returnVal = fc.showOpenDialog(Frame.this);
        if(returnVal == JFileChooser.APPROVE_OPTION){
          File file = fc.getSelectedFile();
          Parser parser = new Parser(file);
          for(int i=0;i<parser.getListOfStations().size();i++){
            p.addStation(parser.getListOfStations().get(i));
          }
              }
      }
    });
    JMenuItem close = new JMenuItem("Close");
    close.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        frame.dispose();
      }
    });
    fileMenu.add(imp);
    fileMenu.add(close);
    JMenu editMenu = new JMenu("Edit");
    JMenuItem addTrain = new JMenuItem("Add Train");
    addTrain.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
      JTextField dest = new JTextField(5);
      JTextField call = new JTextField(5);
      JTextField deptime = new JTextField(5);
      JTextField arrtime = new JTextField(5);
      JPanel popup = new JPanel(new GridLayout(2,4));
      popup.add(new JLabel("Destination: "));
      popup.add(dest);
      popup.add(new JLabel("Departure time: "));
      popup.add(deptime);
      popup.add(new JLabel("Calling at: "));
      popup.add(call);
      popup.add(new JLabel("Arrival time: "));
      popup.add(arrtime);
      int returnVal = JOptionPane.showConfirmDialog(frame,popup,"Train Details",JOptionPane.OK_CANCEL_OPTION);
      if(returnVal== JOptionPane.OK_OPTION){
        p.addTrain(dest.getText(), call.getText(), deptime.getText(), arrtime.getText());
      }
      }
    });
    JMenuItem addStation = new JMenuItem("Add Station");
    addStation.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        String s = null;
        while(s==null){
        s = (String)JOptionPane.showInputDialog(frame,"Enter the new station");
        }
        p.addStation(s);
      }
    });
    JSeparator js1 = new JSeparator();
    JMenuItem removeTrain = new JMenuItem("Remove Train");
    removeTrain.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        p.removeTrain();
      }
    });
    JMenuItem removeStation = new JMenuItem("Remove Station");
    removeStation.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        JTextField xField = new JTextField(5);
        JTextField yField = new JTextField(5);

        JPanel myPanel = new JPanel();
        myPanel.add(new JLabel("x:"));
        myPanel.add(xField);
        myPanel.add(Box.createHorizontalStrut(15)); // a spacer
        myPanel.add(new JLabel("y:"));
        myPanel.add(yField);

        int result = JOptionPane.showConfirmDialog(null, myPanel, 
                 "Please Enter X and Y Values", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
           System.out.println("x value: " + xField.getText());
           System.out.println("y value: " + yField.getText());
        }
      }
    });
    editMenu.add(addTrain);
    editMenu.add(addStation);
    editMenu.add(js1);
    editMenu.add(removeTrain);
    editMenu.add(removeStation);
    JMenu helpMenu = new JMenu("Help");
    JMenuItem about = new JMenuItem("About");
    helpMenu.add(about);
    mainMenu.add(fileMenu);
    mainMenu.add(editMenu);
    mainMenu.add(helpMenu);
    setJMenuBar(mainMenu);
  }
}
