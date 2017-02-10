package client.interfaces;

import android.widget.Button;
import android.widget.EditText;

import shared.model_classes.Player;

public interface IGameLobbyView {

    public Player getPlayer(int index);

    public String getMessage();

    public int getColor();


    public void setMessage(EditText msgEditText);

    public Player[] getPlayers();

    public void setPlayers(Player[] p);

    public String getChat();

    public void setChat(String chat);

}
