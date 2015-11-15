package scripts.scarPlanks;

import java.awt.Graphics;

import org.tribot.api2007.types.RSArea;
import org.tribot.api2007.types.RSTile;
import org.tribot.script.Script;
import org.tribot.script.ScriptManifest;
import org.tribot.script.interfaces.Painting;

@ScriptManifest(name = "Scar Planks", authors = "Scarlett", description = "ScarPlanks, a flawless plank making script.", category = "Money Making", version = 0.1)

public class ScarPlanksMain extends Script implements Painting{
	
	RSArea bankArea = new RSArea(new RSTile[] { new RSTile(3250, 3420, 0), new RSTile(3257, 3419, 0), new RSTile(3257, 3420, 0), new RSTile(3254, 3421, 0), new RSTile(3254, 3423, 0), new RSTile(3252, 3423, 0), new RSTile(3250, 3422, 0) });
	RSArea plankArea = new RSArea(new RSTile[] { new RSTile(3300, 3491, 0), new RSTile(3304, 3491, 0), new RSTile(3304, 3487, 0), new RSTile(3300, 3487, 0), new RSTile(3300, 3488, 0), new RSTile(3301, 3488, 0), new RSTile(3301, 3490, 0), new RSTile(3300, 3490, 0) });
	//Sawmill operator
	
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onPaint(Graphics arg0) {
		// TODO Auto-generated method stub
		
	}

}
