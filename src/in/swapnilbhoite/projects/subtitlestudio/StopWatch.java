package in.swapnilbhoite.projects.subtitlestudio;

/*
 * StopWatch.java
 */

public class StopWatch extends Thread 
{
    
    private long startTime = 0;
    private long stopTime = 0;
    private boolean running = false;
    public static int instance = 0;

    public void sstart() {
        this.startTime = System.currentTimeMillis() - correction;
        this.running = true;
    }

    public void sstop() {
        this.stopTime = System.currentTimeMillis();
        this.running = false;
    }

    //elaspsed time in milliseconds
    public long getElapsedTime() {
        long elapsed;
        if (running) {
             elapsed = (System.currentTimeMillis() - startTime);
        }
        else {
            elapsed = (stopTime - startTime);
        }
        return elapsed;
    }
    
    //elaspsed time in seconds
    public long getElapsedTimeSecs() {
        long elapsed;
        if (running) {
            elapsed = ((System.currentTimeMillis() - startTime) / 1000);
        }
        else {
            elapsed = ((stopTime - startTime) / 1000);
        }
        return elapsed;
    }
    
    public void displayTime()
    {
        this.start();
    }
    
    public void pauseSW()
    {
        correction = this.getElapsedTime();
        this.sstop();
    }
    
    public void resumeSW()
    {
        this.sstart();
    }
    
    @Override
    public void run()
    {
        this.sstart();
        
        if(instance == 0)
        {
            time1 = new MyTime();
            time2 = new MyTime();
            while(getElapsedTime() <= Creator.fileLength)
            {
                time1 = time1.toTime(getElapsedTime());
                Creator.jProgressBarPlayback.setValue((int)((100*getElapsedTime())/Creator.fileLength));
                Creator.jLabelDuration.setText(""+time1+" / "+time2.toTime(Creator.mediaPlayer.getLength()));
            }
        }
        else if(instance == 1)
        {
            //while(true)
                //Edit.jLabel5.setText(""+new MyTime().toTime(getElapsedTime()));
        }
    }
    MyTime time1, time2;
    long correction = 0;
}