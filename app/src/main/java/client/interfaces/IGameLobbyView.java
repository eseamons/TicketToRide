package client.interfaces;

import android.widget.EditText;

import java.util.ArrayList;

import shared.model_classes.Player;

public interface IGameLobbyView {

    Player getPlayer(int index);

    String getMessage();

    int getColor();


    void setMessage(EditText msgEditText);

    Player[] getPlayers();

    void setPlayers(Player[] p);

    ArrayList<String> getChat();

}
