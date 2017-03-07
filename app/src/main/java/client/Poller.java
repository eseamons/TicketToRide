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

    //I haven't tested this yet but this should stop the Timer from calling.
    // We can use this to stop polling the Lobby List when we start a game.
    //After that we could create another timer? Or something else if you guys have a better solution!
    public void stopLobbyListTimer()
    { LobbyListTimer.cancel();}



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
}
