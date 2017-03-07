package shared;

public class Result
{

    private boolean success;
    private Object info;

    public boolean isSuccess() {
        return success;
    }

    public Object getInfo() {
        return info;
    }

    public Result(boolean success, Object info)
    {
        this.success = success;
        this.info = info;
    }
}
