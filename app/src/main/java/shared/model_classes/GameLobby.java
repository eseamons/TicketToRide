package shared.model_classes;

import java.util.ArrayList;
import java.util.List;

public class GameLobby {

    private List<Player> players;
    private int max_players;
    private int ID;
    private String name;
    private List<String> comment_list;

    public GameLobby() {
        players = new ArrayList<>();
    }

    public int playerJoined(){ return players.size();};
    public List<Player> getPlayers() {
        return players;
    }
    public void addNewPlayers(Player player) {
        players.add(player);
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
        for(Player player : players){
            if (player.authCodeMatchesAccount(auth)) {
                authCodeExists = true;
            }
        }
        return authCodeExists;
    }


}
