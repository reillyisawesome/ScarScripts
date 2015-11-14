package scripts.scarAgility.utils.action.obstacle;

import org.tribot.api.General;
import org.tribot.api.types.generic.Condition;
import org.tribot.api2007.Player;
import org.tribot.api2007.types.RSObject;
import scripts.scarAgility.utils.Course;

public class Delays {

    public static Condition playerIsIdle(final Course course){
        return new Condition() {
            @Override
            public boolean active() {
                General.sleep(100, 300);
                return course.getPlayerStatus() == null;
            }
        };
    }

    public static Condition playerIsActive(final Course course){
        return new Condition() {
            @Override
            public boolean active() {
                General.sleep(100, 300);
                return course.getPlayerStatus() != null;
            }
        };
    }

    public static Condition playerIsActiveOrMoving(final Course course){
        return new Condition() {
            @Override
            public boolean active() {
                General.sleep(100, 300);
                return course.getPlayerStatus() != null || Player.isMoving();
            }
        };
    }

    public static Condition isOnScreen(final RSObject object){
        return new Condition() {
            @Override
            public boolean active() {
                General.sleep(100, 300);
                return object == null || object.isOnScreen();
            }
        };
    }

    public static Condition playerStoppedMovingOrActive(final Course course){
        return new Condition() {
            @Override
            public boolean active() {
                General.sleep(100, 300);
                return !Player.isMoving() || course.getPlayerStatus() != null;
            }
        };
    }

}

