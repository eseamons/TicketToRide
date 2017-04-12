package server.plugin;

import shared.command_classes.Command;

/**
 * Created by eseamons on 4/11/2017.
 */

public interface ICommandDao {
    void insertCommand(Command command);
    void clearData();
}
