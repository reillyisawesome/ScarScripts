package scripts.scarPlanks;

import org.tribot.api.util.ABCUtil;
import org.tribot.api2007.Inventory;
import org.tribot.api2007.Player;
import org.tribot.api2007.types.RSArea;
import org.tribot.api2007.types.RSTile;

public class PlankValues {
	
    public static String planks;
    public static int planksMade;
    public static String logs;
	public static RSArea bankArea = new RSArea(new RSTile[] { new RSTile(3250, 3420, 0), new RSTile(3257, 3419, 0), new RSTile(3257, 3420, 0), new RSTile(3254, 3421, 0), new RSTile(3254, 3423, 0), new RSTile(3252, 3423, 0), new RSTile(3250, 3422, 0) });
	public static RSArea plankArea = new RSArea(new RSTile[] { new RSTile(3300, 3491, 0), new RSTile(3304, 3491, 0), new RSTile(3304, 3487, 0), new RSTile(3300, 3487, 0), new RSTile(3300, 3488, 0), new RSTile(3301, 3488, 0), new RSTile(3301, 3490, 0), new RSTile(3300, 3490, 0) });
    public static boolean guiComplete = false;
    public static boolean usePotions;
    public static int interfaceChild;
    static ABCUtil abc = new ABCUtil();
    public static boolean runScript = true;
    public static int[] potionIds = {12631,12629,12627,12625};
    
    
    public static boolean atMill(){
        return plankArea.contains(Player.getPosition());
    }
    
    public static boolean atBank(){
    	return bankArea.contains(Player.getPosition());
    }
    
    public static boolean hasLogs(){
        return Inventory.getCount(logs) > 0;
    }

    public static boolean hasPotions(){
        return Inventory.find(potionIds).length > 0;
    }
    

}
