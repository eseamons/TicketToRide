package shared.model_classes;

import java.util.ArrayList;
import java.util.List;

import shared.CardColor;
import shared.ColorNum;

public class Player {

    private Account account;
    private ColorNum color;
    private int points;
    private List<CardColor> trainCards;
    private List<DestinationCard> destinationCards;
    private int trainsRemaining;
    private int playerID;  // 1-5


    public Player() {
        destinationCards = new ArrayList<>();
        trainCards = new ArrayList<>();
    }

    public Account getAccount() {
        return account;
    }
    public void setAccount(Account account) {
        this.account = account;
    }
    public ColorNum getColor() {
        return color;
    }
    public void setColor(ColorNum color) {
        this.color = color;
    }
    public List<CardColor> getTrainCards() {
        return trainCards;
    }
    public void setTrainCards(List<CardColor> trainCards) {
        this.trainCards = trainCards;
    }

    public int getPlayerID() {
        return playerID;
    }

    public void setPlayerID(int playerID) {
        this.playerID = playerID;
    }

    public boolean authCodeMatchesAccount(String auth) {
        return account.getAuthentication() == auth;
    }


    public int getPoints() {
        return points;
    }
    public void setPoints(int points) {
        this.points = points;
    }

    public void addDestinationCard(DestinationCard destinationCard) {
        destinationCards.add(destinationCard);
    }

    public void removeDestinationCard(String destinationCardName) {
        int index = 0;
        for(int i = 0; i < destinationCards.size(); i++) {
            if(destinationCards.get(i).getDestinationCardName() == destinationCardName) {
                index = i;
            }
        }
        destinationCards.remove(index);
    }

}
