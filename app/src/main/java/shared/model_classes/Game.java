package shared.model_classes;

import java.util.ArrayList;
import java.util.List;

import shared.CardColor;
import shared.ColorNum;
import shared.command_classes.Command;
import shared.model_classes.model_list_classes.DestinationCardsList;
import shared.model_classes.model_list_classes.PlayersList;
import shared.model_classes.model_list_classes.RoutesList;

public class Game {

    private int gameID;
    private PlayersList players;
    private TrainCardDeck trainCardDeck;
    private List<ColorNum> faceUpCards;
    private RoutesList routes;
    private DestinationCardsList destinationCardsList;
    private List<Command> commands;
    private List<String> comments = new ArrayList<>();

    public Game(GameLobby gameLobby) {
        gameID = gameLobby.getID();
        players = new PlayersList(gameLobby.getPlayers());
        trainCardDeck = TrainCardDeck.getInstance();
        faceUpCards = new ArrayList<ColorNum>(5);
        destinationCardsList = new DestinationCardsList();
        routes = new RoutesList();
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
    public CardColor drawCard() {
        return trainCardDeck.drawCard();
    }
    public void addCardsToDiscard(List<CardColor> cardColors) {
        trainCardDeck.addCardsToDiscard(cardColors);
    }
    public void reShuffleTrainCards(){
        trainCardDeck.reShuffleDeck();
    }

    //other actions for game
    public boolean claimRoute(Route route, String auth) {
        boolean successful =  routes.claimRoute(route,auth);
        if(successful)
        {
            int pointIncrease = route.getPointValue();
            players.increasePlayerScore(auth, pointIncrease );
        }
        return successful;
    }

    public boolean destinationCardIsOwned(String destinationCardName) {
        return false;
    }

    public DestinationCard getDestinationCardByName(String destinationCardName) {
        return null;
    }

    public boolean setDestinationCardOwnership(String destinationCardName, String auth) {
        DestinationCard destinationCard = destinationCardsList.getDestinationCardByName(destinationCardName);
        boolean destinationCardDrawnSuccessfully = false;
        if(destinationCard.getOwnership() == null) {
            destinationCard.setOwnership(auth);
            destinationCardDrawnSuccessfully = true;
        }

        return destinationCardDrawnSuccessfully;
    }

    public boolean playerInGame(String auth) {
        return players.playerFound(auth);
    }

    public void addNewComment(String message) {
        comments.add(message);
    }
    public List<String> getAllComments() {
        return comments;
    }
}
