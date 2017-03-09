package shared.model_classes;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import shared.CardColor;
import shared.ColorNum;
import shared.command_classes.Command;
import shared.command_classes.DrawDestinationCardCommand;
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
    private int currentPlayer;


    public Game(){}

    public Game(GameLobby gameLobby) {
        gameID = gameLobby.getID();
        players = new PlayersList(gameLobby.getPlayers());
        trainCardDeck = TrainCardDeck.getInstance();
        faceUpCards = new ArrayList<ColorNum>(5);
        destinationCardsList = new DestinationCardsList();
        routes = new RoutesList();
        currentPlayer = 0;
    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }
    public void setCurrentPlayer(int currentPlayer) {
        this.currentPlayer = currentPlayer;
    }
    public void incrementCurrentPlayer() { this.currentPlayer++; }

    public RoutesList getRoutes(){return routes;}
    public int getGameID() {
        return gameID;
    }

    public List<String> getChat()
    {
        return comments;
    }

    public void endTurn() {
        players.endTurn();
    }

    public int ThisPlayersTurn()
    {
        return players.getCurrentPlayerID();
    }
    public List<Player> getPlayers() {
        return players.getAllPlayers();
    }

    public Player getPlayerbyIndex(int ID){
        Player p = players.getPlayer(ID);
        return p;
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
            players.decreasePlayerTrainsRemaining(auth, route.length);
        }
        return successful;
    }

    public boolean stupidClaimRoute(int route_num, int player_num) {
        Route r = routes.getRoute(route_num);
        r.ownership = player_num;
        Player p = getPlayerbyIndex(player_num-1);
        p.setPoints(p.getPlayerID() + r.getPointValue());
        p.decreaseTrainsRemaining(r.length);
        return true;
    }

    public void stupidAddCard(CardColor card, int player_num)
    {
        getPlayerbyIndex(player_num-1).addTrainCard(card);
    }

    public void stupidAddDestinationCard(DestinationCard card, int player_num)
    {
        getPlayerbyIndex(player_num-1).addDestinationCard(card);
    }

    public void stupidAddComment(String name, String message)
    {
        String add = name + ": " + message;
        comments.add(add);
    }

    //Destination Card Methods
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
    public DestinationCard drawDestinationCard(String auth) {
        DestinationCard card = destinationCardsList.drawCard(auth);
        return card;
    }
    public void returnDestinationCard(DestinationCard destinationCard, int playerID) {
        destinationCardsList.discardCard(destinationCard);
        Player p = getPlayerbyIndex(playerID);
        p.removeDestinationCard(destinationCard);
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
