package Backend;


import java.awt.*;

public class AlarmThread extends Thread{
    private Thread thread;
    private int _time;
    private String _name;

    public AlarmThread(int time, String name)
    {
        _time = time;
        _name = name;
    }
    public void run()
    {
        try
        {
            Thread.sleep(_time * 1000);
        } catch (Exception e) {}
        ((Runnable) Toolkit.getDefaultToolkit().getDesktopProperty("win.sound.default")).run();
    }
    public void start()
    {
        if(thread == null)
        {
            thread = new Thread(this, _name);
            thread.start();
        }
    }
}
