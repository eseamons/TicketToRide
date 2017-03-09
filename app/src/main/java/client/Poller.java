package client;

import android.os.AsyncTask;

import java.util.Timer;
import java.util.TimerTask;

public class Poller
{
    Timer LobbyListTimer = new Timer();
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
        }, 1, 1);
    }


    public void stopLobbyListTimer()
    { LobbyListTimer.cancel();}

    public void runGetGameCommands()
    {
        LobbyListTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                GamePolling gamePoller = new GamePolling();
                gamePoller.execute();
            }
        }, 1, 1);
    }


    //Async task that has the code that was originally in the poller (Thank you Michael)
    public class LobbyPolling extends AsyncTask<Void, Void, Void>
    {

        //this gets all the info from the server(MODEL DOES NOT UPDATE)
        @Override
        protected Void doInBackground(Void... params)
        {
            ClientFacade client = new ClientFacade();
            client.getNewCommands();
            //client.getServerGamesList(ClientModel.getInstance().getAuthorization());
            return null;
        }

        //If there are any lobbies to show then the Model updates...
        //Would be better if it had a way to update only if the sixe of the list increased.
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            ClientModel clientModel = ClientModel.getInstance();
            if(clientModel.getListOfLobbies().size() > 0) {
                clientModel.update();
            }
        }
    }


    public class GamePolling extends AsyncTask<Void, Void, Void>
    {

        //this gets all the info from the server(MODEL DOES NOT UPDATE)
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
            if(clientModel.getListOfLobbies().size() > 0) {
                clientModel.update();
            }
        }
    }
}
