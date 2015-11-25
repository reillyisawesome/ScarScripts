package scripts.scarAgility;

import org.tribot.api.Timing;
import org.tribot.api.input.Mouse;
import org.tribot.api2007.Login;
import org.tribot.api2007.Player;
import org.tribot.api2007.Skills;
import org.tribot.api2007.types.RSArea;
import org.tribot.api2007.types.RSModel;
import org.tribot.api2007.types.RSPlayer;
import org.tribot.api2007.types.RSTile;
import org.tribot.api2007.util.ProjectionUtility;
import org.tribot.script.Script;
import org.tribot.script.ScriptManifest;
import org.tribot.script.interfaces.EventBlockingOverride;
import org.tribot.script.interfaces.Painting;
import scripts.scarAgility.ui.graphics.Indicator;
import scripts.scarAgility.ui.graphics.TileDrawing;
import scripts.scarAgility.utils.action.Manager;
import scripts.scarAgility.utils.action.State;
import scripts.scarAgility.utils.areas.Area;
import scripts.scarAgility.ui.MainGUI;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.font.TextAttribute;
import java.io.IOException;
import java.net.URL;
import java.text.AttributedString;

import javax.imageio.ImageIO;

@ScriptManifest(name = "Scar Agility", authors = "Scarlett", description = "Scar Agility Based on daxAgility", category = "Agility", version = 1.0)
public class scarAgilityMain extends Script implements Painting, EventBlockingOverride {

    private boolean stopScript;
    private MainGUI gui;
    private Manager manager;
    private ProjectionUtility projectionUtility;

    public scarAgilityMain(){
        stopScript = false;
        projectionUtility = new ProjectionUtility();
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                gui = new MainGUI();
            }
        });
    }

    @Override
    public void run(){

        while (Login.getLoginState() != Login.STATE.INGAME || gui == null || !gui.isComplete()){
            sleep(500);
        }

        manager = new Manager(gui);
        Mouse.setSpeed(gui.getMouseSpeed());

        while (!stopScript){
            manager.loop();
            sleep(50);
        }
    }

	@Override
	public OVERRIDE_RETURN overrideKeyEvent(KeyEvent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OVERRIDE_RETURN overrideMouseEvent(MouseEvent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	 private Image getImage(String url) {
		    try {
		      return ImageIO.read(new URL(url));
		    } catch (IOException e) {
		      return null;
		    }
		  }
	
	//Paint Info
	 
	 
	//Paint Variables
	 private final int startLvl = Skills.getActualLevel(Skills.SKILLS.AGILITY);
	 private final int startXP = Skills.getXP(Skills.SKILLS.AGILITY);  
	 private int currentLVL = Skills.getCurrentLevel(Skills.SKILLS.AGILITY);
	 Font font = new Font("Verdana", Font.BOLD, 14);  
     private final Image img = getImage("http://i.imgur.com/Xp8PKMf.jpg"); 
     
	  public void onPaint(Graphics g)  {
		  
		  Graphics2D gg = (Graphics2D)g;
	      
	      if (manager != null){
	    	  State state = manager.getState();
	    	  Indicator.playerStatus(g, manager.getPlayerStatus());	
	    	  //Paint Variables
		      int currentLvl = Skills.getActualLevel(Skills.SKILLS.AGILITY);
		      int gainedLvl = currentLvl - startLvl;
		      int gainedXP = Skills.getXP(Skills.SKILLS.AGILITY) - startXP;
		      int xpToLevel = Skills.getXPToNextLevel(Skills.SKILLS.AGILITY);
		      long xpPerHour = (long) (gainedXP * 3600000 / this.getRunningTime()); 
		      gg.drawImage(img, 0, 304, null);
		      g.setColor(new Color(102, 102, 102));
		      g.drawString("Runtime: " + Timing.msToString(this.getRunningTime()), 363, 370);
		      g.drawString("Current lvl: " + currentLvl + " (+" + gainedLvl + ")", 363, 390);
		      g.drawString("XP Gained: " + gainedXP, 363, 410);
		      g.drawString("XP TNL: " + xpToLevel, 363, 430);
		      g.drawString("XP/H: " + xpPerHour, 363, 450);
		      
	      }

	    
	  }

    /**@Override
    public void onPaint(Graphics graphics) {
        Graphics2D g = (Graphics2D) graphics;
        g.setRenderingHints(GeneralPaint.ANTIALIASING);
        if (interactivePaint != null){
            interactivePaint.onPaint(graphics);
        }
        if (manager != null){
            g.setClip(5, 5, 511, 333);
            Area area = manager.getCurrentArea(), nextArea = manager.getNextArea();
            if (area != null){
                g.setColor(new Color(88, 185, 209, 80));
                TileDrawing.drawArea(g, area, projectionUtility);
            }
            if (nextArea != null){
                g.setColor(new Color(255, 1, 0, 70));
                TileDrawing.drawArea(g, nextArea, projectionUtility);
            }
            g.setClip(0, 0, 800, 500);
            State state = manager.getState();
            if (generalPaint != null && state != null){
                generalPaint.paint(g, state.toString(), 0)
                ;
            }
            Indicator.playerStatus(g, manager.getPlayerStatus());
        }
    }

    @Override
    public OVERRIDE_RETURN overrideMouseEvent(MouseEvent mouseEvent) {
        if (interactivePaint == null){
            return OVERRIDE_RETURN.PROCESS;
        }
        return interactivePaint.overrideMouseEvent(mouseEvent);
    }

    @Override
    public OVERRIDE_RETURN overrideKeyEvent(KeyEvent keyEvent) {
        if (interactivePaint == null){
            return OVERRIDE_RETURN.PROCESS;
        }
        return interactivePaint.overrideKeyEvent(keyEvent);
    }**/
}