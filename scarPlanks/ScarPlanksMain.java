package scripts.scarPlanks;

import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import org.tribot.api.General;
import org.tribot.api2007.types.RSArea;
import org.tribot.api2007.types.RSTile;
import org.tribot.api2007.util.ThreadSettings;
import org.tribot.script.Script;
import org.tribot.script.ScriptManifest;
import org.tribot.script.interfaces.Painting;

import scripts.scarPlanks.nodehandler.Node;
import scripts.scarPlanks.nodehandler.NodeManager;
import scripts.scarPlanks.nodes.Potions;
import scripts.scarPlanks.nodes.WalkToSawMill;

@ScriptManifest(name = "Scar Planks", authors = "Scarlett", description = "ScarPlanks, a flawless plank making script.", category = "Money Making", version = 0.1)

public class ScarPlanksMain extends Script implements Painting{
	
	@Override
	public void run() {
        General.useAntiBanCompliance(true);
        ThreadSettings.get().getClickingAPIUseDynamic();
        MainGUI mainGui = new MainGUI();
        initGui(mainGui);
        loop(20,50);
	}
	
	 private void loop(int min, int max) {
	        Potions potion = new Potions();
	        potion.start();
	        potion.setPriority(10);
	        NodeManager manager = new NodeManager();
	        manager.addTasks(new WalkToSawMill());
	        while (PlankValues.runScript) {
	           potion.run();
	            Node node = manager.getValidTask();
	            if (node != null) {
	                node.execute();
	                sleep(min,max);
	            }
	        }
		
	}

	private void initGui(MainGUI mainGui) {
		 JFrame f = new JFrame();
	        f.getContentPane();
	        f.add(mainGui);
	        while(!PlankValues.guiComplete){
	            General.sleep(100,300);
	        }
	        f.setVisible(false);
	}

	private Image getImage(String url) {
		    try {
		      return ImageIO.read(new URL(url));
		    } catch (IOException e) {
		      return null;
		    }
	 }


	@Override
	public void onPaint(Graphics arg0) {
		// TODO Auto-generated method stub
		
	}
	

}
