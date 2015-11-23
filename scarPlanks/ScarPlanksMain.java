package scripts.scarPlanks;

import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import org.tribot.api2007.types.RSArea;
import org.tribot.api2007.types.RSTile;
import org.tribot.script.Script;
import org.tribot.script.ScriptManifest;
import org.tribot.script.interfaces.Painting;

@ScriptManifest(name = "Scar Planks", authors = "Scarlett", description = "ScarPlanks, a flawless plank making script.", category = "Money Making", version = 0.1)

public class ScarPlanksMain extends Script implements Painting{
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
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
