package shared.model_classes;

import java.util.ArrayList;
import java.util.List;

import shared.CardColor;
import shared.ColorNum;

public class Player {

    private Account account;
    private ColorNum color;
    private int points;
    private List<CardColor> trainCards = new ArrayList<>();
    private List<DestinationCard> destinationCards;
    private int trainsRemaining;
    private int playerID;  // 1-5

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

}
