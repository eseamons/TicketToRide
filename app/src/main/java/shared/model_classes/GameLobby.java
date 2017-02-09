package shared.model_classes;

import java.util.List;

import shared.interfaces.ICommand;

public class GameLobby {

    private List<Player> players;
    private int max_players;
    private int ID;
    private String name;
    private List<ICommand> command_list;

    public List<Player> getPlayers() {
        return players;
    }
    public void setPlayers(List<Player> players) {
        this.players = players;
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
}
