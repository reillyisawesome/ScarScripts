package scripts.ScarAPI.skills.prayer;

import java.awt.Point;
import java.awt.Rectangle;

import org.tribot.api.General;
import org.tribot.api.input.Mouse;
import org.tribot.api2007.Interfaces;
import org.tribot.api2007.types.RSInterfaceChild;

public class Prayer {

    private static int SKILL_PARENT = 271;

    public static RSInterfaceChild getChild(PRAYERS prayer) {
        return getChild(prayer.getChildIndex());
    }

    public static RSInterfaceChild getChild(int index) {
        return Interfaces.get(SKILL_PARENT, index);
    }

    public static boolean hover(PRAYERS prayer) {
        return hover(getChild(prayer));
    }

    public static boolean hover(PRAYERS prayer, int dx, int dy) {
        return hover(getChild(prayer), dx, dy);
    }

    public static boolean hover(RSInterfaceChild child) {
        return hover(child, 0, 0);
    }

    public static boolean hover(RSInterfaceChild child, int dx, int dy) {
        return child != null
                && child.hover(
                        new Point(General.random(-dx, dx), General.random(-dy,
                                dy)), null);
    }

    public static boolean cast(PRAYERS prayer) {
        return cast(getChild(prayer));
    }

    public static boolean cast(PRAYERS prayer, int dx, int dy) {
        return cast(getChild(prayer), dx, dy);
    }

    public static boolean cast(RSInterfaceChild child) {
        return cast(child, 0, 0);
    }

    public static boolean cast(RSInterfaceChild child, int dx, int dy) {
        if (child != null) {
            if (!child.getAbsoluteBounds().contains(Mouse.getPos()))
                hover(child, dx, dy);
            return child.click();
        }
        return false;
    }

    public static Rectangle getAbsoluteBounds(PRAYERS prayer) {
        return getChild(prayer).getAbsoluteBounds();
    }
}
