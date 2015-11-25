package scripts.scarPlanks.nodes;

import org.tribot.api.Clicking;
import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.types.generic.Condition;
import org.tribot.api2007.Interfaces;
import org.tribot.api2007.Inventory;
import org.tribot.api2007.NPCChat;
import org.tribot.api2007.NPCs;
import org.tribot.api2007.types.RSInterfaceChild;
import org.tribot.api2007.types.RSNPC;

import scripts.scarPlanks.PlankValues;
import scripts.scarPlanks.nodehandler.Node;

public class MakePlank extends Node {

	@Override
	public void execute() {
        if(Interfaces.get(403) == null){
            RSNPC operator[] = NPCs.find("Sawmill operator");
            if(Clicking.click("Buy-plank", operator)) {
                Timing.waitCondition(new Condition() {
                    @Override
                    public boolean active() {
                        General.sleep(100, 200);
                        return Interfaces.get(403) != null;
                    }
                }, General.random(3000, 5000));
            }
        }
        General.sleep(PlankValues.abc.DELAY_TRACKER.ITEM_INTERACTION.next());
        PlankValues.abc.DELAY_TRACKER.ITEM_INTERACTION.reset();
        RSInterfaceChild box = Interfaces.get(403, PlankValues.interfaceChild);
        if (box != null) {
            if (Clicking.click("Buy all", box)) {
                Timing.waitCondition(new Condition() {
                    @Override
                    public boolean active() {
                        return !PlankValues.hasLogs();
                    }
                }, General.random(1000, 2000));
                if(NPCChat.getMessage() != null){
                    General.println("Out of coins!");
                    PlankValues.runScript = false;
                }
                PlankValues.planksMade = PlankValues.planksMade + Inventory.getCount(PlankValues.planks);
            }
        }
	}

	@Override
	public boolean validate() {
		return PlankValues.atSawMill() && PlankValues.hasLogs();
	}

	@Override
	public int priority() {
		return 3;
	}

}
