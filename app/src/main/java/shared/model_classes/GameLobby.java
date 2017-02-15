package shared.model_classes;

import java.util.ArrayList;
import java.util.List;

import shared.interfaces.ICommand;

public class GameLobby {

    private List<Player> players;
    private int max_players;
    private int current_number_of_players = 0;
    private int ID;
    private String name;
    private List<ICommand> command_list;
    private List<String> comment_list;

    public Game changeIntoGame()
    {
        return new Game();
    }

    public GameLobby() {
        players = new ArrayList<>();
    }

    public void playerJoined(){ current_number_of_players++;}
    public List<Player> getPlayers() {
        return players;
    }
    public void addNewPlayers(Player player) {
        players.add(player);
    }
    public int getMax_players() {
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
    public List<ICommand> getCommand_list() {
        return command_list;
    }
    public void setCommand_list(List<ICommand> command_list) {
        this.command_list = command_list;
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
