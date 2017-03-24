package client.interfaces;

import shared.model_classes.Player;

/**
 * Created by Maynson on 3/24/2017.
 */

public interface IGameOverView {

    String getPlayer1();

    String getPlayer2();

    String getPlayer3();

    String getPlayer4();

    String getPlayer5();

    void setPlayer1Name(String s);
    void setPlayer2Name(String s);
    void setPlayer3Name(String s);
    void setPlayer4Name(String s);
    void setPlayer5Name(String s);

    void setPlayer1Score(int points);
    void setPlayer2Score(int points);
    void setPlayer3Score(int points);
    void setPlayer4Score(int points);
    void setPlayer5Score(int points);

    void player3Invis();
    void player4Invis();
    void player5Invis();

    void setVictory();
    void setDefeat();

    void setRibbon1();
    void setRibbon2();
    void setRibbon3();
    void setRibbon4();
    void setRibbon5();

}
