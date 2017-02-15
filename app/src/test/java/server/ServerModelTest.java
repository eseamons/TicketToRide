package server;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import shared.model_classes.Account;
import shared.model_classes.GameLobby;

import static org.junit.Assert.*;

/**
 * Created by erics on 2/14/2017.
 */

public class ServerModelTest {

    static String usernameOne;
    static String passwordOne;
    static Account accountOne;

    @BeforeClass
    public static void setup() throws Exception{
        usernameOne = "mytest";
        passwordOne = "mypassword";
    }

    @Test
    public void register() throws Exception {
        ServerModel model = ServerModel.getInstance();
        boolean registerSuccessful = model.Register(usernameOne, passwordOne);
        assertTrue(registerSuccessful);
    }

    @Test
    public void login() throws Exception {
        ServerModel model = ServerModel.getInstance();
        accountOne = model.Login(usernameOne, passwordOne);
        assertEquals(accountOne.getPassword(), passwordOne);
        assertEquals(accountOne.getUsername(), usernameOne);
        assertTrue(accountOne.getAuthentication() instanceof String);
    }

    @Test
    public void createGame() throws Exception {
        String authcode = accountOne.getAuthentication();
        int max_player_num = 4;
        String gameNameOne = "Game One";
        String gameNameTwo = "Coolest Game Ever";
        String gameNameThree = "Ultimate Game";


        ServerModel serverModel = ServerModel.getInstance();
        GameLobby gameLobby = serverModel.CreateGame(gameNameOne, max_player_num,authcode);

        assertEquals(gameLobby.getID(),1);
        assertEquals(gameLobby.getMax_players(),4);
        assertEquals(gameLobby.getName(),gameNameOne);

        //create second game with user account
        max_player_num = 3;
        gameLobby = serverModel.CreateGame(gameNameTwo, max_player_num, authcode);

        assertEquals(gameLobby.getID(),2);
        assertEquals(gameLobby.getMax_players(),3);
        assertEquals(gameLobby.getName(),gameNameTwo);

        //create second game with user account
        max_player_num = 5;
        gameLobby = serverModel.CreateGame(gameNameThree, max_player_num, authcode);

        assertEquals(gameLobby.getID(),3);
        assertEquals(gameLobby.getMax_players(),5);
        assertEquals(gameLobby.getName(),gameNameThree);
    }


}