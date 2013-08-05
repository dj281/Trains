package controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class TimeChecker extends Thread
{

  private String timeStamp;

  public String getTimeStamp()
  {
    return timeStamp;
  }

  public void run(){
    while(true){
      try{
        Thread.sleep(2000);
        String dateStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
        timeStamp = dateStamp.substring(9,13);
        System.out.println(timeStamp);
      }catch(InterruptedException e){
        e.printStackTrace();
      }
    }
  }
}
