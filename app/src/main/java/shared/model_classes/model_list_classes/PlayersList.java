package shared.model_classes.model_list_classes;

import java.util.List;

import shared.command_classes.Command;
import shared.model_classes.Player;

/**
 * Created by rebeccaredd on 2/28/17.
 * created so we could start working on the commands
 */

public class PlayersList {
    private List<Player> players;
    private int numOfPlayers;
    private int currentPlayerID;
    private Player currentPlayer;

    private List<Command> gameCommands;



    public PlayersList(List<Player>listOfPlayers)
    {
        players = listOfPlayers;
        numOfPlayers = listOfPlayers.size();
        currentPlayerID =0;
        currentPlayer = players.get(0);
    }

    public int getCurrentPlayerID()
    {return currentPlayerID;}

    public Player getCurrentPlayer()
    {return currentPlayer;}

    public List<Player> getAllPlayers()
    {return players;}

    public void endTurn()
    {
        currentPlayerID++;

        if(currentPlayerID== numOfPlayers)
        {currentPlayerID =0;}

        currentPlayer = players.get(currentPlayerID);
    }



}
