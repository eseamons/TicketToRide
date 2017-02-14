package shared.interfaces;
import shared.Result;

public interface ICommand {

    public void executeOnClient();
    public Result execute();
    public String getType();
    public ICommand getCommand();
}
