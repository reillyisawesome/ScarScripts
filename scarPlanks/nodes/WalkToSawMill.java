package scripts.scarPlanks.nodes;

import static org.tribot.api.General.random;

import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.types.generic.Condition;
import org.tribot.api2007.Game;
import org.tribot.api2007.WebWalking;

import scripts.scarPlanks.PlankValues;
import scripts.scarPlanks.nodehandler.Node;

public class WalkToSawMill extends Node{

	@Override
	public void execute() {
		WebWalking.walkTo(PlankValues.plankArea.getRandomTile());{
			Timing.waitCondition(new Condition() {
				@Override
				public boolean active() {
					return Game.getSetting(638) == 0;
				}
	    	}, random(2500, 4000));
		}
	}

	@Override
	public boolean validate() {
		return !PlankValues.atSawMill() && PlankValues.hasLogs();
	}

	@Override
	public int priority() {
		return 2;
	}

}
