/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package substu4;

/**
 *
 * @author CrazyCoder
 */
public class MyTime 
{
    long hr, min, sec, ms;
    public MyTime()
    {
        hr = min = sec = ms = 0;
    }
    
    public MyTime(long ms1)
    {
        ms = ms1;
        sec = ms/1000;
        ms = ms%1000;
        min = sec/60;
        sec = sec%60;
        hr = min/60;
        min = min%60;
    }
    
    MyTime toTime(long ms1)
    {
        MyTime temp = new MyTime();
        temp.ms = ms1;
        temp.sec = temp.ms/1000;
        temp.ms = temp.ms%1000;
        temp.min = temp.sec/60;
        temp.sec = temp.sec%60;
        temp.hr = temp.min/60;
        temp.min = temp.min%60;
        return temp;
    }
    
    @Override
    public String toString()
    {
        String temp = "";
        if(hr == 0)
            temp = "00:";
        else if(hr < 10)
            temp = "0"+hr+":";
        else
            temp = hr+":";
        if(min == 0)
            temp = temp + "00:";
        else if(min < 10)
            temp = temp + "0" + min + ":";
        else
            temp = temp + min + ":";
        if(sec == 0)
            temp = temp + "00,";
        else if(sec < 10)
            temp = temp + "0" + sec + ",";
        else
            temp = temp + sec + ",";
        if(ms == 0)
            temp = temp + "000";
        else if(ms < 10)
            temp = temp + "00" + ms;
        else if(ms < 100)
            temp = temp + "0" + ms;
        else
            temp = temp + ms;
        return temp;
    }
    public static String toSSA(String temp)
    {
        String ans = temp.subSequence(1, 8) + ".";
        ans = ans + temp.charAt(9) + temp.charAt(10) + ",";
        ans = ans + temp.substring(18, 25) + ".";
        ans = ans + temp.charAt(26) + temp.charAt(27) + ",";
        return ans;
    }
}
