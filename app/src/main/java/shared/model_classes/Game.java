package shared.model_classes;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import client.ClientFacade;
import server.ServerFacade;
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
    private CardColor[] faceUpCards;
    private RoutesList routes;
    private DestinationCardsList destinationCardsList;
    private List<Command> commands;
    private List<String> comments = new ArrayList<>();
    private List<Command> gameCommands;

    public Game(){}

    public Game(GameLobby gameLobby) {
        gameID = gameLobby.getID();
        players = new PlayersList(gameLobby.getPlayers());
        LastTurnsLeft = players.getSize() + 1;
        trainCardDeck = TrainCardDeck.getInstance();
        faceUpCards = new CardColor[5];//new CardColor[]{null, null, null, null, null};
        destinationCardsList = new DestinationCardsList();
        routes = new RoutesList();
        gameCommands = new ArrayList<>();
    }

    public void setFaceUpCard(int index, CardColor c)
    {
        faceUpCards[index] =c;
    }

    public CardColor getFaceUpCard(int cardIndex)
    {
        return faceUpCards[cardIndex];
    }


    public List<CardColor> getFaceUpCards()
    {
        return Arrays.asList(faceUpCards);
    }

    public int getCurrentPlayer() {
        return players.getCurrentPlayerID();
    }

    public RoutesList getRoutes(){return routes;}
    public int getGameID() {
        return gameID;
    }

    public List<String> getChat()
    {
        return comments;
    }

    private boolean lastTurn = false;
    private int LastTurnsLeft;
    private boolean gameIsOver = false;

    public boolean isGameOver()
    {
        return gameIsOver;
    }

    public void endTurn(String auth)
    {
        players.endTurn(auth);
        calculateLastTurns();
    }

    public void calculateLastTurns()
    {
        if(lastTurn)
        {
            LastTurnsLeft--;
            if(LastTurnsLeft == 0)
            {
                gameIsOver = true;
            }
        }
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
    public int getDiscardPileSize(){return trainCardDeck.getDiscardPileSize();}
    public void reShuffleTrainCards(){
        trainCardDeck.reShuffleDeck();
    }

    //other actions for game
    public boolean claimRoute(Route route, String auth, CardColor colorOfCardUsed) {

        int playerID = getPlayerIndexByAuthCode(auth);
        boolean successful =  routes.claimRoute(route,auth, playerID);
        if(successful)
        {
            int pointIncrease = route.getPointValue();
            players.increasePlayerScore(auth, pointIncrease );
            players.decreasePlayerTrainsRemaining(auth, route.length);
            List <CardColor> cardsUsed = players.removeCards(auth, colorOfCardUsed, route);
            trainCardDeck.addCardsToDiscard(cardsUsed);
            if(players.twoOrLessTrains(auth))
            {
                lastTurn = true;
            }
        }
        return successful;
    }



    public void addComment(String message)
    {
        comments.add(message);
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

    public void performEndGameCalculations()
    {
        routes.performEndGameCalculations();
    }


    public int getPlayerIndexByAuthCode(String auth)
    {
        List<Player> players = getPlayers();
        for(int i = 0; i < players.size(); i++)
        {
            if(players.get(i).getPlayerAuthCode().equals(auth))
            {
                return i;
            }
        }
        return -1;
    }

    public void addGameCommand(Command cmd) {
        gameCommands.add(cmd);
    }

    public List<Command> getNewGameCommands(int commandID) {
        return gameCommands.subList(commandID+1, gameCommands.size());
    }






//stupid functions.

    public boolean stupidClaimRoute(int route_num, int player_num) {
        Route r = routes.getRoute(route_num);
        r.ownership = player_num;
        Player p = getPlayerbyIndex(player_num-1);
        p.setPoints(p.getPoints() + r.getPointValue());
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
}
