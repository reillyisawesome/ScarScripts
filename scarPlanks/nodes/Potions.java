package scripts.scarPlanks.nodes;

import org.tribot.api2007.Game;

import scripts.scarPlanks.PlankValues;

public class Potions extends Thread  {
	
    @Override
    public void run() {
        if((Game.getSetting(638) == 0 && PlankValues.hasPotions() && Game.getRunEnergy() <= 70) || Game.getRunEnergy() == 0){
            PlankValues.drinkPotion();
        }
    }

    public static void main(String args[]){
        (new Potions()).start();
    }

}
