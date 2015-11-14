package scripts.scarAgility.utils.areas;

import org.tribot.api.General;
import org.tribot.api2007.PathFinding;
import org.tribot.api2007.Player;
import org.tribot.api2007.types.RSArea;
import org.tribot.api2007.types.RSObject;
import org.tribot.api2007.types.RSTile;

public abstract class CourseDefinition {

    public abstract Area[] getCourseAreas();
    public abstract String toString();
    public abstract String getPlayerStatus();

    public RSArea getStartingArea(){
        Area closest = null;
        int closestDistance = Integer.MAX_VALUE;
        RSTile playerPosition = Player.getPosition();
        for (Area area : getCourseAreas()){
            RSTile tile = area.getRSArea().getRandomTile().getPosition();
            int distance = tile.distanceTo(playerPosition);
            if (tile.getPlane() == playerPosition.getPlane() && (distance < closestDistance) && PathFinding.canReach(tile.getPosition(), false)){
                closest = area;
                closestDistance = distance;
            }
        }
        return closest != null ? closest.getRSArea() : null;
    }

    public Area getNextArea(){
        Area current = getCurrentArea();
        return current == null ? null : current.getNextArea();
    }

    public Area getCurrentArea(){
        for (int i = 0; i < getCourseAreas().length; i++) {
            if (getCourseAreas()[i].containsPlayer()){
                return getCourseAreas()[i];
            }
        }
        return null;
    }

    public boolean isInCourse(){
        return getCurrentArea() != null;
    }

    public RSObject getNextObstacle(){
        Area area = getNextArea();
        return area != null ? area.getObstacle() : null;
    }

    public RSObject getCurrentObstacle(){
        Area area = getCurrentArea();
        return area != null ? area.getObstacle() : null;
    }

}