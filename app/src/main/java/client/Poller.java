package client;

import android.util.Log;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Poller
{
    int times = 0;
    public void runGetNonGameCommands()
    {
        ScheduledExecutorService scheduleTaskExecutor;

        scheduleTaskExecutor= Executors.newScheduledThreadPool(5);

            // This schedule a task to run every 1 second:
        scheduleTaskExecutor.scheduleAtFixedRate(new Runnable()
        {
            public void run()
            {
                ClientFacade client = new ClientFacade();
                client.getNewCommands();
                Log.i("l", " T: " + times);
            }
        }, 1, 1, TimeUnit.SECONDS);
    }

}
