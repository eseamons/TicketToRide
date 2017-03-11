package shared.model_classes;

import java.util.ArrayList;
import java.util.List;

import shared.model_classes.model_list_classes.PlayersList;

public class GameLobby {

   // private List<Player> playersList;
    private PlayersList playersList;
    private int max_players;
    private int ID;
    private String name;
    private ArrayList<String> comment_list;

    public GameLobby() {
        ArrayList<Player> newPlayersList = new ArrayList<>();
        playersList = new PlayersList(newPlayersList);
        comment_list = new ArrayList<>();
    }

    public int NumOfCurrentPlayers(){ return playersList.getSize();}
    public List<Player> getPlayers() {
        return playersList.getAllPlayers();
    }
    public int addNewPlayers(Account playerAccount) {
        for(Player p : playersList.getAllPlayers())
        {
            if (p.getPlayerAuthCode().equals( playerAccount.getAuthentication()))
            {
                return -1;
            }
        }
        Player player = new Player();
        player.setAccount(playerAccount);
        player.setPlayerID(playersList.getSize());
        playersList.addPlayer(player);
        return playersList.getSize()-1;
    }
    public Player getPlayer(int index){
        return playersList.getPlayer(index);
    }

    public ArrayList<String> getComment_list() {
        return comment_list;
    }

    public void setComment_list(ArrayList<String> comment_list) {
        this.comment_list = comment_list;
    }

    public int getMaxPlayers() {
        return max_players;
    }
    public void setMax_players(int max_players) {
        this.max_players = max_players;
    }
    public int getID() {
        return ID;
    }
    public void setID(int iD) {
        ID = iD;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void addNewComment(String comment) {
        comment_list.add(comment);
    }

    public boolean authCodeExistsInLobby(String auth) {
        boolean authCodeExists = false;
        for(Player player : playersList.getAllPlayers()){
            if (player.authCodeMatchesAccount(auth)) {
                authCodeExists = true;
            }
        }
        return authCodeExists;
    }


}
