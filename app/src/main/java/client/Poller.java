package client;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Poller
{
    public static void run()
    {
        ScheduledExecutorService scheduleTaskExecutor;

        scheduleTaskExecutor= Executors.newScheduledThreadPool(5);

            // This schedule a task to run every 10 minutes:
        scheduleTaskExecutor.scheduleAtFixedRate(new Runnable()
        {
            public void run()
            {
                ClientFacade client = new ClientFacade();
                client.getNewCommands();
            }
        }, 0, 1, TimeUnit.SECONDS);
    }

    public static void main(String[] args)
    {
        run();
    }
}
