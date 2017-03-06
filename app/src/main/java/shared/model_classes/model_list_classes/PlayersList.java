package shared.model_classes.model_list_classes;

import java.util.List;

import shared.ColorNum;
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
    }

    public int getCurrentPlayerID()
    {return currentPlayerID;}

    public Player getCurrentPlayer()
    {return currentPlayer;}

    public List<Player> getAllPlayers()
    {return players;}

    public Player getPlayer(int index)
    {return players.get(index);}

    public int getSize()
    {return players.size();}

    public void addPlayer(Player p){
        if(players.size() == 0)
        {p.setColor(ColorNum.RED);}
        if(players.size() == 1)
        {p.setColor(ColorNum.GREEN);}
        if(players.size() == 2)
        {p.setColor(ColorNum.BLUE);}
        if(players.size() == 3)
        {p.setColor(ColorNum.YELLOW);}
        if(players.size() == 4)
        {p.setColor(ColorNum.BLACK);}
    }

    public void endTurn()
    {
        currentPlayerID++;

        if(currentPlayerID== numOfPlayers)
        {currentPlayerID =0;}

        currentPlayer = players.get(currentPlayerID);
    }

    public void increasePlayerScore(String auth, int points) {
        for(int i = 0; i < players.size(); i ++)
        {
            Player tempPlayer = players.get(i);
            String tempAuth = tempPlayer.getAccount().getAuthentication();
            if(tempAuth.equals(auth))
            {
                int newScore = tempPlayer.getPoints() + points;
                tempPlayer.setPoints(newScore);
            }
        }
    }


    public boolean playerFound(String auth) {
        boolean playerFound = false;
        for(Player p : players) {
            if(p.getPlayerAuthCode().equals(auth)) {
                playerFound = true;
            }
        }
        return playerFound;
    }

}
