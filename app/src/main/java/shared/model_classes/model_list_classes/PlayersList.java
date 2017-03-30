package shared.model_classes.model_list_classes;

import java.util.List;

import shared.CardColor;
import shared.ColorNum;
import shared.command_classes.Command;
import shared.model_classes.Player;
import shared.model_classes.Route;

/**
 * Created by rebeccaredd on 2/28/17.
 * created so we could start working on the commands
 */


/**
 * PlayerList stores a list of the players in the game,
 * and it contains methods for changing the active player and modifying the stats of the active player.
 *
 * @invariant players.size() <= 5
 * @invariant numOfPlayers <= 5
 * @invariant numOfPlayers > 0
 * @invariant currentPlayerID < numOfPlayers
 * @invariant currentPlayerID >= 0
 */

public class PlayersList {
    /**
     * players is a list of all the Players in the game
     */
    private List<Player> players;
    /**
     * numOfPlayers is the number of players currently in the game
     */
    private int numOfPlayers;
    /**
     * currentPlayerID gives the index in the list players of the player whose turn it is
     */
    private int currentPlayerID;
    /**
     * currentPlayer is the Player in the list players whose turn it is
     */
    private Player currentPlayer;

    private List<Command> gameCommands;


    /**
     * Constructs a new list of players for a game with an initial list of Players passed in
     *
     * @param listOfPlayers List of the initial players in the PlayersList
     *
     * @pre listOfPlayers.size() > 0
     *
     * @post players == listOfPlayers
     * @post numOfPlayers == listOfPlayers.size()
     * @post currentPlayerID == 0
     */
    public PlayersList(List<Player>listOfPlayers)
    {
        players = listOfPlayers;
        numOfPlayers = listOfPlayers.size();
        currentPlayerID =0;
    }

    /**
     * Returns the id of the player whose turn it is
     *
     * @pre none
     *
     * @return Current player ID
     */
    public int getCurrentPlayerID()
    {return currentPlayerID;}

    /**
     * Returns the player whose turn it is
     *
     * @pre none
     *
     * @return Current player
     */
    public Player getCurrentPlayer()
    {return currentPlayer;}

    /**
     * Returns a list of all the players in the game
     *
     * @pre none
     *
     * @return List of all players in the playerList
     */
    public List<Player> getAllPlayers()
    {return players;}

    /**
     * Returns the player in the List at the specified index
     *
     * @param index Index in list of player
     *
     * @pre none
     *
     * @return Player at location index in list of players
     */
    public Player getPlayer(int index)
    {return players.get(index);}

    /**
     * Returns the number of players in the game
     *
     * @pre none
     *
     * @return Size of list of players
     */
    public int getSize()
    {return players.size();}

    /**
     * Adds a player to the list of players in the game and assigns the player a color based on its position
     *
     * @param p The player to be added to the list
     *
     * @pre p != null
     *
     * @post p is in List players
     * @post players.size += 1
     */
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
        players.add(p);
    }

    /**
     * Switches the current player to the next player in the game/list
     *
     * @pre none
     *
     * @post currentPlayerID += 1 or == 0 if it was at the last index of the player in the list
     * @post currentPlayer == the player in list players at index currentPlayerID
     */
    public void endTurn()
    {
        currentPlayerID++;

        if(currentPlayerID == numOfPlayers)
        {currentPlayerID =0;}

        currentPlayer = players.get(currentPlayerID);
    }

    /**
     * Increases the score of the player with authentication token auth by int points
     *
     * @param auth The authentication token of the player to have his points increased
     * @param points The amount of points to increase the player's score by
     *
     * @pre points > 0
     * @pre auth is the authentication token of one of the players in the game/list
     *
     * @post getPoints() += points for the player with authentication token auth
     */
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

    /**
     * Decreases the number of trains remaining of the player with authentication token auth by int numOfTrains
     *
     * @param auth The authentication token of the player to have his number of trains decreased
     * @param numOfTrains The amount to decrease the player's number of trains by
     *
     * @pre numOfTrains > 0
     * @pre auth is the authentication token of one of the players in the game/list
     *
     * @post getTrainsRemaining() -= numOfTrains for the player with authentication token auth
     */
    public void decreasePlayerTrainsRemaining(String auth, int numOfTrains)
    {
        for(int i = 0; i < players.size(); i ++)
        {
            Player tempPlayer = players.get(i);
            String tempAuth = tempPlayer.getAccount().getAuthentication();
            if(tempAuth.equals(auth))
            {
                tempPlayer.decreaseTrainsRemaining(numOfTrains);
            }
        }
    }

    public boolean twoOrLessTrains(String auth)
    {
        return getPlayerByAuthCode(auth).twoOrLessTrains();
    }

    public Player getPlayerByAuthCode(String auth)
    {
        for(int i = 0; i < players.size(); i++)
        {
            Player p = players.get(i);
            if(p.getPlayerAuthCode().equals(auth))
            {
                return p;
            }
        }
        return null;
    }

    /**
     * Returns true if the player with authentication token auth is in the game/list. False otherwise
     *
     * @param auth The authentication token of the player to be searched for
     *
     * @pre none
     *
     * @return true if player with authentication token auth is in the game/list, false if not
     */
    public boolean playerFound(String auth) {
        boolean playerFound = false;
        for(Player p : players) {
            if(p.getPlayerAuthCode().equals(auth)) {
                playerFound = true;
            }
        }
        return playerFound;
    }

    public void removeCards(String auth, CardColor colorOfCardUsed, Route route) {
        for(int i = 0; i < players.size(); i ++)
        {
            Player tempPlayer = players.get(i);
            String tempAuth = tempPlayer.getAccount().getAuthentication();
            if(tempAuth.equals(auth))
            {
                tempPlayer.removeCards(colorOfCardUsed, route);
            }
        }
    }
}
