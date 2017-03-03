package client;

import android.os.AsyncTask;

import java.util.Timer;
import java.util.TimerTask;

public class Poller
{
    public void runGetLobbyCommands()
    {

        //this calls the Async task over and over again. The numbers 1,1 tell it how often to run
        //the higher the numbers the SLOWER it runs.
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                LobbyPolling l = new LobbyPolling();
                l.execute();
            }
        }, 1, 1);
    }




    public class LobbyPolling extends AsyncTask<Void, Void, Void>
    {
        @Override
        protected Void doInBackground(Void... params)
        {
            ClientFacade client = new ClientFacade();
            client.getNewCommands();
            client.getServerGamesList(ClientModel.getInstance().getAuthorization());
            return null;
        }

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
