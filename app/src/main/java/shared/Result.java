package shared;

public class Result
{

    boolean success;
    String info;

    public boolean isSuccess() {
        return success;
    }

    public String getInfo() {
        return info;
    }

    public Result(boolean suc, String inf)
    {
        success = suc;
        info = inf;
    }
}
