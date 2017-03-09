package client.interfaces;

import shared.model_classes.model_list_classes.RoutesList;

/**
 * Created by Michaels on 3/8/2017.
 */

public interface IMapView
{
    void setPurpleNumText(String set);
    void setWhiteNumText(String set);
    void setBlueNumText(String set);
    void setYellowNumText(String set);
    void setOrangeNumText(String set);
    void setBlackNumText(String set);
    void setRedNumText(String set);
    void setGreenNumText(String set);
    void setWildNumText(String set);
    void setDestViewText(String set);
    void drawRoutes(RoutesList routes);

}
