package scripts.ScarAPI.skills.prayer;

import org.tribot.api.General;
import org.tribot.api2007.GameTab;
import org.tribot.api2007.GameTab.TABS;
import org.tribot.api2007.Skills;

public class PrayerBook {
    public boolean isOpen() {
        return GameTab.getOpen() == TABS.PRAYERS;
    }

    public boolean open() {
        if (!isOpen()) {
            return GameTab.open(TABS.PRAYERS);
        }
        return true;
    }

    public int getCurrentPrayerLevel() {
        if (isOpen()) {
            return Prayer.getChild(1) != null ? Integer.parseInt(Prayer
                    .getChild(1).getText().split("/")[0].replaceFirst(
                    ".*?(\\d+).*", "$1")) : -1;
        }
        return -1;
    }

    public int getActualPrayerLevel() {
        return Skills.getActualLevel(Skills.SKILLS.PRAYER);
    }

    public boolean hasPrayerEnergy() {
        return getPrayerEnergy() > 0;
    }

    public int getPrayerEnergy() {
        return Prayer.getChild(1) != null ? Integer.parseInt(Prayer.getChild(1)
                .getText().split("/")[1].replaceFirst(".*?(\\d+).*", "$1")) : 0;
    }

    public boolean flashBetweenPrayers(PRAYERS first, PRAYERS second, long wait) {
        if (Prayer.getChild(first) != null && Prayer.getChild(second) != null
                && hasPrayerEnergy()) {
            Prayer.cast(first);
            Prayer.hover(second);
            General.sleep(wait);
            if (hasPrayerEnergy()) {
                return Prayer.cast(second);
            }
        }
        return false;
    }

    public boolean flashBetweenPrayers(PRAYERS first, PRAYERS second) {
        return flashBetweenPrayers(first, second, 350);
    }

    public boolean flashPrayer(PRAYERS prayer, long wait) {
        if (Prayer.getChild(prayer) != null && hasPrayerEnergy()) {
            Prayer.cast(prayer);
            General.sleep(wait);
            if (hasPrayerEnergy()) {
                return Prayer.cast(prayer);
            }
        }
        return false;
    }

    public boolean flashPrayer(PRAYERS prayer) {
        return flashPrayer(prayer, 350);
    }
}