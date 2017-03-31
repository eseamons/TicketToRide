//package client;
//
//import org.junit.Test;
//
//import shared.model_classes.Account;
//import shared.model_classes.GameLobby;
//
//import static org.junit.Assert.*;
//
///**
// * Created by erics on 2/16/2017.
// */
//public class ClientFacadeTest {
//    @Test
//    public void joinGame() throws Exception {
//        ClientFacade facade = new ClientFacade();
//        facade.register("test", "test");
//        Account acnt = facade.login("test", "test");
//        facade.createGameLobby("TestGame", 4);
//        GameLobby lobby = facade.joinGame(1);
//        assertEquals(1, lobby.getID());
//        assertEquals("TestGame", lobby.getName());
//        assertEquals(1, lobby.getPlayers().size());
//    }
//
//    @Test
//    public void beginGame() throws Exception {
//        ClientFacade facade = new ClientFacade();
//        facade.register("test3", "test3");
//        Account acnt = facade.login("test3", "test3");
//        facade.createGameLobby("TestGame", 4);
//        GameLobby lobby = facade.joinGame(1);
//        facade.beginGame();
//    }
//
//}