package model;

import java.awt.Container;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import view.Table;

public class Parser
{
  public Parser(File file){
    parseXmlFile(file);
    parseDocument();
  }
  Document doc;
  private ArrayList<Table> stations = new ArrayList<Table>();
  private String d;
  private String c;
  private String a;
  private String dt;
  public void parseXmlFile(File file){
    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
    try{
      DocumentBuilder db = dbf.newDocumentBuilder();
      doc = db.parse(file);
    }catch(ParserConfigurationException pce){
      pce.printStackTrace();
    }catch(SAXException se){
      se.printStackTrace();
    }catch(IOException ioe){
      ioe.printStackTrace();
    }      
  }
  
  public void parseDocument(){
    Element docEle = doc.getDocumentElement();
    NodeList nl = docEle.getElementsByTagName("Station");
      if(nl != null && nl.getLength()>0){
        for(int i=0;i<nl.getLength();i++){
        Element e1 = (Element)nl.item(i);
        Table t = getStation(e1);
        stations.add(t);
        NodeList nl2 = e1.getElementsByTagName("Train");
        if(nl2 != null && nl2.getLength()>0){
          for(int j=0;j<nl2.getLength();j++){
            Element e2 = (Element)nl2.item(j);
            getTrain(e2);
           
            t.addTrain(d,c,dt,a);
          }
        }
        
        
        }
      }
    }

  public Table getStation(Element staEl){
    String name = getTextValue(staEl,"Name");
    
    Table t = new Table(name);
    return t;
  }
  public void getTrain(Element traEl){
    d = getTextValue(traEl,"Destination");
    c = getTextValue(traEl,"Calling");
    dt = getTextValue(traEl,"DestTime");
    a = getTextValue(traEl,"ArrTime");
    }
  public ArrayList<Table> getListOfStations(){
    return stations;
  }
  private String getTextValue(Element ele, String tagName){
    String textVal = null;
    NodeList nl = ele.getElementsByTagName(tagName);
    if(nl != null && nl.getLength()>0){
      Element el = (Element)nl.item(0);
      textVal = el.getFirstChild().getNodeValue();
    }
    return textVal;
  }
}
