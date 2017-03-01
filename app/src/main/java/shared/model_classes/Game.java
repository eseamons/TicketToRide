package shared.model_classes;

import java.util.ArrayList;
import java.util.List;

import shared.ColorNum;
import shared.command_classes.Command;
import shared.model_classes.model_list_classes.PlayersList;
import shared.model_classes.model_list_classes.RoutesList;

public class Game {

    private int gameID;
    private PlayersList players;
    private TrainCardDeck trainCardDeck;
    private List<ColorNum> faceUpCards;
    private RoutesList routes;
    private List<Command> commands;

    public Game(GameLobby gameLobby) {
        gameID = gameLobby.getID();
        players = new PlayersList(gameLobby.getPlayers());
        trainCardDeck = TrainCardDeck.getInstance();
        faceUpCards = new ArrayList<ColorNum>(5);
    }

    public int getGameID() {
        return gameID;
    }

    public void endTurn() {
        players.endTurn();
    }

    public List<Player> getPlayers() {
        return players.getAllPlayers();
    }


    //Train Deck Methods
    public ColorNum drawCard() {
        return trainCardDeck.drawCard();
    }
    public void addCardsToDiscard(List<ColorNum> cardColors) {
        trainCardDeck.addCardsToDiscard(cardColors);
    }
    public void reShuffleTrainCards(){
        trainCardDeck.reShuffleDeck();
    }
}
