package client.interfaces;

import shared.CardColor;

/**
 * Created by sirla on 3/3/2017.
 */

public interface IGameDeckView {

    void setPurpleNum(int i);

    void setWhiteNum(int i);

    void setBlueNum(int i);

    void setYellowNum(int i);

    void setOrangeNum(int i);

    void setBlackNum(int i);

    void setRedNum(int i);

    void setGreenNum(int i);

    void setWildNum(int i);

    void setTrainsRemainingNum(int i);

    void setFaceUpCard0(CardColor c);

    void setFaceUpCard1(CardColor c);

    void setFaceUpCard2(CardColor c);

    void setFaceUpCard3(CardColor c);

    void setFaceUpCard4(CardColor c);

    void setDestinationTickets(String s);
}
