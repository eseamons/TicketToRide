package shared.command_data_classes;

import shared.command_classes.GetCurrentGameCommand;

/**
 * Created by mepeter on 4/17/17.
 */

public class GetCurrentGameCommandData
{
    String auth;

    public void setAuth(String a)
    {
        auth = a;
    }

    public String getAuth()
    {
        return auth;
    }

    public GetCurrentGameCommandData()
    {

    }
}
