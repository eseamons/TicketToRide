package server;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import dalvik.annotation.TestTargetClass;
import server.testObjects.ServerModelTestObject;
import shared.model_classes.Account;
import shared.model_classes.GameLobby;

import static org.junit.Assert.*;

/**
 * Created by erics on 2/14/2017.
 */

public class ServerModelTest {

    @Test
    public void register() throws Exception {
        ServerModelTestObject testObject = new ServerModelTestObject();
        boolean registerSuccessful = testObject.register("mytest", "mypassword");
        assertTrue(registerSuccessful);
    }

    @Test
    public void login() throws Exception {
        ServerModelTestObject testObject = new ServerModelTestObject();
        testObject.register("mytest", "mypassword");
        Account account = testObject.login("mytest", "mypassword");
        assertTrue(account.getAuthentication() != null);
    }

    @Test
    public void joinGame() throws Exception{
        String gameName = "Ultimate Game";
        String gameNameTwo = "Test Game";
        int max_player_num = 4;
        Account account = null;
        GameLobby lobbyOne = null;
        GameLobby lobbyTwo = null;

        ServerModelTestObject testObject = new ServerModelTestObject();
        testObject.register("mytest", "mypassword");
        account = testObject.login("mytest", "mypassword");
        testObject.createGame(gameName, max_player_num, account.getAuthentication());

        max_player_num = 3;
        testObject.createGame(gameNameTwo, max_player_num, account.getAuthentication());


        lobbyOne = testObject.joinGame(1, account.getAuthentication());
        lobbyTwo = testObject.joinGame(2, account.getAuthentication());


        assertEquals(1, lobbyOne.getID());
        assertEquals(4, lobbyOne.getMax_players());
        assertEquals(lobbyOne.getName(), gameName);
        assertEquals(1, lobbyOne.playerJoined());
//
//        assertEquals(2, lobbyTwo.getID());
//        assertEquals(3, lobbyTwo.getMax_players());
//        assertEquals(lobbyTwo.getName(), gameNameTwo);
    }


}