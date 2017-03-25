package client;

import android.os.AsyncTask;

import java.util.Timer;
import java.util.TimerTask;

public class Poller
{
    Timer LobbyListTimer = new Timer();
    Timer GameCommandTimer = new Timer();
    static Poller instance = new Poller();

    private Poller(){}

    public static Poller getInstance() {
        return  instance;
    }

    public void runGetLobbyCommands()
    {

        //this calls the Async task over and over again. The numbers 1,1 tell it how often to run
        //the higher the numbers the SLOWER it runs.
        LobbyListTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                LobbyPolling l = new LobbyPolling();
                l.execute();
            }
        }, 1, 1000);
    }

    public void stopLobbyListTimer()
    { if(LobbyListTimer != null)
        LobbyListTimer.cancel();
    }

    public void runGetGameCommands()
    {
        //GameCommandTimer = new Timer();
        GameCommandTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                GamePolling gamePoller = new GamePolling();
                gamePoller.execute();
            }
        }, 1, 1000);
    }


    //Async task that has the code that was originally in the poller (Thank you Michael)
    public class LobbyPolling extends AsyncTask<Void, Void, Integer>
    {

        //this gets all the info from the server(MODEL DOES NOT UPDATE)
        @Override
        protected Integer doInBackground(Void... params)
        {
            ClientFacade client = new ClientFacade();
            int NewCommands = client.getNewCommands();
            return NewCommands;
        }

        //If there are any lobbies to show then the Model updates...
        //Would be better if it had a way to update only if the sixe of the list increased.
        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
            //if(integer >0) {
                ClientModel clientModel = ClientModel.getInstance();
                if (clientModel.getListOfLobbies().size() > -1) {
                    clientModel.update();
                }
            //}
        }
    }


    public class GamePolling extends AsyncTask<Void, Void, Void>
    {

        @Override
        protected Void doInBackground(Void... params)
        {
            ClientFacade clientfacade = new ClientFacade();
            clientfacade.getNewGameCommands();
            return null;
        }

        //If there are any lobbies to show then the Model updates...
        //Would be better if it had a way to update only if the sixe of the list increased.
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            ClientModel clientModel = ClientModel.getInstance();
                clientModel.update();

        }
    }
}
