package scripts.ScarAPI.Utility;

import org.tribot.api.interfaces.Positionable;
import org.tribot.api2007.types.RSAnimableEntity;

public class Utility {
	
    public static boolean isLookingTowards(RSAnimableEntity animableEntity, Positionable positionable) {
        final int orientation = (int) Math.round(animableEntity.getOrientation() / 256.0);

        final int dx = animableEntity.getPosition().getX() -  positionable.getPosition().getX();
        final int dy = animableEntity.getPosition().getY() -  positionable.getPosition().getY();

        switch (orientation) {
            case 0:     //south
                return  dx == 0 && dy > 0;
            case 1:     //south - west
                return dx > 0 && dy > 0 && dx == dy;
            case 2:     //west
                return dx > 0 && dy == 0;
            case 3:     //north - west
                return dx > 0 && dy < 0 && Math.abs(dx) == Math.abs(dy);
            case 4:     //north
                return dx == 0 && dy < 0;
            case 5:     //north - east
                return dx < 0 && dy < 0 && dx == dy;
            case 6:     //east
                return dx < 0 && dy == 0;
            case 7:     //south-east
                return dx < 0 && dy > 0 && Math.abs(dx) == Math.abs(dy);
        }
        return false;
    }

}
