package server.plugin;

import java.util.Set;

import shared.Result;
import shared.command_classes.Command;

/**
 * Created by eseamons on 4/11/2017.
 */

public interface ICommandDao {
    Set<CommandDTO> getAll();

    Result addCommand(CommandDTO commandDTO);

    Result clearData();
}
