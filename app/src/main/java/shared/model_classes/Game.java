package shared.model_classes;

import java.util.List;

import shared.model_classes.model_list_classes.PlayersList;

public class Game {

    private int gameID;
    private PlayersList players;
    private TrainCardDeck trainCardDeck;

    public Game(GameLobby gameLobby)
    {
        gameID = gameLobby.getID();
        players = new PlayersList(gameLobby.getPlayers());
        trainCardDeck = TrainCardDeck.getInstance();
    }

    //added this while working on endturnCommand(2/28)
    public int getGameID() {
        return gameID;
    }

    public void endTurn() {
        players.endTurn();
    }

    public List<Player> getPlayers() {
        return players.getAllPlayers();
    }
}
