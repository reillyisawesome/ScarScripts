package scripts.scarPlanks.nodes;

import scripts.scarPlanks.PlankValues;
import scripts.scarPlanks.nodehandler.Node;

import static org.tribot.api.General.random;

import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.types.generic.Condition;
import org.tribot.api2007.Banking;
import org.tribot.api2007.Game;
import org.tribot.api2007.Inventory;
import org.tribot.api2007.types.RSItem;

public class BankOP  extends Node{

	@Override
	public void execute() {
		if (!Banking.isBankScreenOpen()){
			if (Banking.openBankBanker()){
				Timing.waitCondition(new Condition() {
					@Override
					public boolean active() {
						return Banking.isBankScreenOpen();
					}
		    	}, random(2500, 4000));
			}
		}
		if (Inventory.getCount("Coins")==0){
			RSItem Coins[] = Banking.find("Coins");
			if (Coins.length > 0 ){
				Banking.withdrawItem(Coins[0], 0);
				Timing.waitCondition(new Condition(){
					public boolean active(){
						return Inventory.find("Coins").length > 0;
					}
				}, random(2500, 5000));
                General.sleep(PlankValues.abc.DELAY_TRACKER.ITEM_INTERACTION.next());
                PlankValues.abc.DELAY_TRACKER.ITEM_INTERACTION.reset();
			} else {
				General.println("Out of Coins!");
				PlankValues.runScript = false;
			}
		}
		if (PlankValues.usePotions == true){
            RSItem potions[] = Banking.find(PlankValues.potionIds);
            if(potions.length > 0 && Inventory.getCount(PlankValues.potionIds) == 0){
                Banking.withdrawItem(potions[0],1);
                Timing.waitCondition(new Condition() {
                    @Override
                    public boolean active() {
                        General.sleep(25,100);
                        return Inventory.find(PlankValues.potionIds).length > 0;
                    }
                },General.random(2000,5000));
                General.sleep(PlankValues.abc.DELAY_TRACKER.ITEM_INTERACTION.next());
                PlankValues.abc.DELAY_TRACKER.ITEM_INTERACTION.reset();
            } else {
            	General.println("Out of Potions!");
            }
		}
        RSItem logs[] = Banking.find(PlankValues.logs);
        if(logs.length > 0){
            if(Banking.withdrawItem(logs[0],0)) {
                Timing.waitCondition(new Condition() {
                    @Override
                    public boolean active() {
                        General.sleep(50, 200);
                        return Inventory.find(PlankValues.logs).length > 0;
                    }
                }, General.random(3000, 5000));
                General.sleep(PlankValues.abc.DELAY_TRACKER.ITEM_INTERACTION.next());
                PlankValues.abc.DELAY_TRACKER.ITEM_INTERACTION.reset();
            }
        }else{
            General.println("Out of logs!");
            PlankValues.runScript = false;
        }
        if(PlankValues.usePotions){
            if(Game.getSetting(638) == 0 && Game.getRunEnergy() <= 70 && PlankValues.hasPotions()){
                Banking.close();
            }
        }
		
	}

	@Override
	public boolean validate() {
		return PlankValues.atBank() && !PlankValues.hasLogs();
	}

	@Override
	public int priority() {
		return 1;
	}

}
