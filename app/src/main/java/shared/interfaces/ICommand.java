package shared.interfaces;
import shared.Result;

public interface ICommand {

    void executeOnClient();
    Result execute();
    String getType();
    ICommand getCommand();
}
