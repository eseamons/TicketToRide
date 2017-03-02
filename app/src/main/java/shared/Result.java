package shared;

public class Result
{

    private boolean success;
    private String info;

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
