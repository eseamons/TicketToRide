package shared.interfaces;
import shared.Result;

public interface ICommand {

    public Result execute();
    public String getType();
}
