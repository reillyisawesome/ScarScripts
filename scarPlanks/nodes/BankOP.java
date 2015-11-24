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
			} else {
				General.println("Out of Coins");
				PlankValues.runScript = false;
			}
		}
		//if (PlankValues.usePotions == true){
			//Banking.withdraw(PlankValues.potionIds, 1)
		//}
		
	}

	@Override
	public boolean validate() {
		return PlankValues.atBank();
	}

	@Override
	public int priority() {
		return 0;
	}

}
