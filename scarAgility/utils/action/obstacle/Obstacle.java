package scripts.scarAgility.utils.action.obstacle;

import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.input.Mouse;
import org.tribot.api.types.generic.Condition;
import org.tribot.api2007.Camera;
import org.tribot.api2007.Player;
import org.tribot.api2007.Projection;
import org.tribot.api2007.Walking;
import org.tribot.api2007.types.*;
import scripts.scarAgility.utils.Course;
import scripts.scarAgility.utils.action.CameraPositioner;
import scripts.scarAgility.utils.areas.Area;

import java.awt.*;
import java.util.HashMap;
import java.util.Random;


public class Obstacle {

    private static final HashMap<Area, RSTile> CACHE = new HashMap<>();
    private static final String[] ACTIONS = {
            "Walk-across", "Walk-on", "Climb-down","Climb-over", "Squeeze-through", "Climb",
            "Swing-on", "Cross", "Balance", "Jump-up", "Jump"
    };

    public static void prepareForNext(final Course course, final boolean hover, CameraPositioner cameraPositioner){
        final RSObject nextObstacle = course.getNextObstacle();
        if (nextObstacle != null) {
            if (hover) {
                centerCamera(nextObstacle, cameraPositioner);
            }
            Timing.waitCondition(new Condition() {
                @Override
                public boolean active() {
                    General.sleep(100, 300);
                    if (course.getPlayerStatus() == null){
                        return true;
                    }
                    if (hover) {
                        Obstacle.hover(nextObstacle);
                    }
                    return false;
                }
            }, General.random(8500, 10000));
        } else {
            hoverCachedArea(course, hover, cameraPositioner);
        }
    }

    public static boolean maneuver(final Course course, CameraPositioner cameraPositioner, boolean hover){
        RSObject obstacle = course.getCurrentObstacle();
        if (obstacle != null){
            if (obstacle.isOnScreen() && obstacle.isClickable()){
                if (obstacle.click(ACTIONS) && Timing.waitCrosshair(1000) == 2
                        && Timing.waitCondition(Delays.playerIsActiveOrMoving(course), General.random(1700, 2200))){
                    if (Player.isMoving()) {
                        if (Timing.waitCondition(Delays.playerStoppedMovingOrActive(course), General.random(7700, 12000))) {
                            return Timing.waitCondition(Delays.playerIsActive(course), General.random(1300, 1900));
                        }
                    } else {
                        return true;
                    }
                }
            } else {
                RSTile destination = getValidWalkingTile(obstacle.getPosition(), course);
                if (destination != null && Walking.blindWalkTo(destination) && Timing.waitCondition(Delays.playerStoppedMovingOrActive(course), General.random(900, 1500))){
                    if (hover) {
                        centerCamera(obstacle, cameraPositioner);
                    }
                    Timing.waitCondition(Delays.isOnScreen(obstacle), General.random(4000, 6000));
                }
            }
        }
        return false;
    }

    private static void hoverCachedArea(final Course course, final boolean hover, CameraPositioner cameraPositioner){
        Area nextArea = course.getNextArea();
        if (nextArea != null){
            cacheObject(nextArea);
            if (CACHE.containsKey(nextArea)) {
                final RSTile destination = CACHE.get(nextArea);
                cacheObject(nextArea);
                cameraPositioner.setRotation(Camera.getTileAngle(destination) + General.random(-25, 25));
                Timing.waitCondition(new Condition() {
                    @Override
                    public boolean active() {
                        General.sleep(100, 300);
                        if (course.getPlayerStatus() == null){
                            return true;
                        }
                        if (hover && destination != null &&  destination.isOnScreen()){
                            Polygon tileBounds = Projection.getTileBoundsPoly(destination, 0);
                            if (tileBounds != null && !tileBounds.contains(Mouse.getPos())){
                                destination.hover();
                            }
                        }
                        return false;
                    }
                }, General.random(8500, 10000));
            }
        } else {
            Timing.waitCondition(Delays.playerIsIdle(course), General.random(5000, 7000));
        }
    }

    private static void centerCamera(RSObject obstacle, CameraPositioner cameraPositioner){
        cameraPositioner.setRotation(Camera.getTileAngle(obstacle.getPosition()) + General.random(-25, 25));
        cameraPositioner.setAngle(100 - (obstacle.getPosition().distanceTo(Player.getPosition()) * 5));
    }

    private static RSTile getValidWalkingTile(RSTile center, Course course){
        Area area = course.getCurrentArea();
        if (area != null){
            RSTile tile = area.getOptimalLocation();
            if (tile != null){
                return tile;
            }
        }
        RSTile[] tiles = new RSArea(center, 1).getAllTiles();
        shuffleArray(tiles);
        for (RSTile tile : tiles){
            if (course.getCurrentArea().getRSArea().contains(tile)){
                return tile;
            }
        }
        return null;
    }

    private static void cacheObject(Area area){
        if (area == null){
            return;
        }
        RSObject cacheObject = area.getObstacle();
        if (cacheObject != null) {
            CACHE.put(area, cacheObject.getPosition());
        }
    }

    private static void hover(RSObject obstacle) {
        if (!obstacle.isOnScreen()){
            return;
        }
        RSModel model = obstacle.getModel();
        if (model != null){
            Polygon modelArea = model.getEnclosedArea();
            if (!modelArea.contains(Mouse.getPos())){
                obstacle.hover();
            }
        }
    }

    private static void shuffleArray(Object[] array){
        Random random = new Random();
        for (int i = array.length - 1; i > 0; i--){
            int index = random.nextInt(i + 1);
            Object a = array[index];
            array[index] = array[i];
            array[i] = a;
        }
    }

}
