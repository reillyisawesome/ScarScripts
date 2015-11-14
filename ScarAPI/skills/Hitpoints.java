package scripts.ScarAPI.skills;

import org.tribot.api2007.Skills;
import org.tribot.api2007.Skills.SKILLS;

public class Hitpoints {
	
	SKILLS HP = Skills.SKILLS.HITPOINTS;
	
	public double getHpPercent() {
        return ((double) (Skills.getCurrentLevel(HP) / Skills.getActualLevel(HP)));
    }
	
	public int getHitpoints(){
		return Skills.getCurrentLevel(HP);
	}

}
