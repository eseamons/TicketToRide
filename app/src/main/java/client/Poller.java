package client;

import android.os.AsyncTask;
import android.util.Log;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Poller
{
    int times = 0;
//    public void runGetNonGameCommands()
//    {
//        ScheduledExecutorService scheduleTaskExecutor;
//
//        scheduleTaskExecutor= Executors.newScheduledThreadPool(5);
//
//            // This schedule a task to run every 1 second:
//        scheduleTaskExecutor.scheduleAtFixedRate(new Runnable()
//        {
//            public void run()
//            {
//                ClientFacade client = new ClientFacade();
//                client.getNewCommands();
//                //client.getServerGamesList(ClientModel.getInstance().getAuthorization());
//                Log.i("l", " T: " + times);
//                times++;
//            }
//        }, 3,2, TimeUnit.SECONDS);
//    }


    public void runGetLobbyCommands()
    {
//        ScheduledExecutorService scheduleTaskExecutor;
//
//        scheduleTaskExecutor= Executors.newScheduledThreadPool(5);
//
//        // This schedule a task to run every 1 second:
//        scheduleTaskExecutor.scheduleAtFixedRate(new Runnable()
//        {
//            public void run()
//            {
//                LobbyPolling l = new LobbyPolling();
//                l.doInBackground();
//            }
//        }, 3,2, TimeUnit.SECONDS);
    }


    public class LobbyPolling extends AsyncTask<Void, Void, Void>
    {
        @Override
        protected Void doInBackground(Void... params)
        {
            ClientFacade client = new ClientFacade();
            client.getNewCommands();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            ClientFacade client = new ClientFacade();
            client.getServerGamesList(ClientModel.getInstance().getAuthorization());
        }


    }

}
