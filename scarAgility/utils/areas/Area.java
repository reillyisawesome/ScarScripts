package scripts.scarAgility.utils.areas;

import org.tribot.api2007.types.RSArea;
import org.tribot.api2007.types.RSObject;
import org.tribot.api2007.types.RSTile;

public interface Area {

    Area getNextArea();
    RSArea getRSArea();
    RSObject getObstacle();
    boolean containsPlayer();
    RSTile getOptimalLocation();
}
