package server.plugin;

/**
 * Created by eseamons on 4/11/2017.
 */

public interface IDaoFactory {
    IAccountDao createAccountDao();
    ICommandDao createCommandDao();
    IGameDao createGameDao();
}
