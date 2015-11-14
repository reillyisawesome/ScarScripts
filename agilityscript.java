package scripts;
import static org.tribot.api.General.random;
import scripts.ScarAgilityGUI;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JSlider;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;

import javax.swing.JSeparator;
import javax.swing.JButton;
import javax.imageio.ImageIO;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import org.tribot.script.interfaces.Arguments;
import org.tribot.api.Clicking;
import org.tribot.api.DynamicClicking;
import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.input.Mouse;
import org.tribot.api.types.generic.Condition;
import org.tribot.api.util.ABCUtil;
import org.tribot.api2007.Banking;
import org.tribot.api2007.Camera;
import org.tribot.api2007.Game;
import org.tribot.api2007.GameTab;
import org.tribot.api2007.Inventory;
import org.tribot.api2007.NPCs;
import org.tribot.api2007.Objects;
import org.tribot.api2007.Options;
import org.tribot.api2007.Player;
import org.tribot.api2007.Skills;
import org.tribot.api2007.Walking;
import org.tribot.api2007.WebWalking;
import org.tribot.api2007.types.RSArea;
import org.tribot.api2007.types.RSNPC;
import org.tribot.api2007.types.RSObject;
import org.tribot.api2007.types.RSTile;
import org.tribot.script.Script;
import org.tribot.script.ScriptManifest;
import org.tribot.script.interfaces.Painting;

@ScriptManifest(authors = { "Scarlett" }, category = "Agility", name = "Scar Agility", version = 0.01, description = "", gameMode = 1)
public class agilityscript extends Script  implements Painting, Arguments {
	
	
	//Paint Variables
	 private final int startLvl = Skills.getActualLevel(Skills.SKILLS.AGILITY);
	 private final int startXP = Skills.getXP(Skills.SKILLS.AGILITY);  
	 private int currentLVL = Skills.getCurrentLevel(Skills.SKILLS.AGILITY);
	 Font font = new Font("Verdana", Font.BOLD, 14);  
     private final Image img = getImage("http://i.imgur.com/Xp8PKMf.jpg"); 
     
    //Variables for different things such as ABCL
     private ABCUtil abc = new ABCUtil();
    ScarAgilityGUI gui = new ScarAgilityGUI();
     
     private void antiBan(){
    	    // ABC: Check XP
    	    final long check_xp = this.abc.TIME_TRACKER.CHECK_XP.next();

    	    if (check_xp > 0 && System.currentTimeMillis() > check_xp) {
    	            if (GameTab.TABS.STATS.open()) {
    	                    General.sleep(100, 300);

    	                    Mouse.moveBox(555, 400, 600, 414);

    	                    sleep(800, 2000);

    	                    this.abc.TIME_TRACKER.CHECK_XP.reset();
    	            }
    	    }
    	    
    	   // ABC: Randomly Rotate Camera
    	    long next_camera = this.abc.TIME_TRACKER.ROTATE_CAMERA.next();

    	    if (next_camera > 0 && System.currentTimeMillis() > next_camera) {
    	            final int rotation = Camera.getCameraRotation()
    	                            + General.random(-20, 20) % 360;

    	            Camera.setCameraRotation(rotation);

    	            this.abc.TIME_TRACKER.ROTATE_CAMERA.reset();
    	    }   
    	    
    	   // ABC: Activating Run
    	    int run_at = this.abc.INT_TRACKER.NEXT_RUN_AT.next();

    	    if (run_at > 0 && Game.getRunEnergy() > run_at) {
    	            Options.setRunOn(true);

    	            this.abc.INT_TRACKER.NEXT_RUN_AT.reset();
    	    }  
     }
	
	//Tiles for Start/End of courses
	private final RSTile DRAYNOR_START = new RSTile(3103,3279,0),
						 DRAYNOR_END = new RSTile(3103,3279,0),
						 GNOME_START = new RSTile(2474,3436,0),
						 GNOME_PIPE_LOCATION = new RSTile(2487,3430,0),
						 ALKARID_START = new RSTile(0,0,0),
						 ALKARID_END = new RSTile(0,0,0),
						 VARROCK_START = new RSTile(0,0,0),
						 VARROCK_END = new RSTile(0,0,0);
	
	  // Define the areas of the gnome course
	  private final RSArea startArea = new RSArea(new RSTile(2472, 3439), new RSTile(2476, 3436));
	  private final RSArea secondArea = new RSArea(new RSTile(2471, 3429, 0), new RSTile(2477, 3426, 0));
	  private final RSArea thirdArea = new RSArea(new RSTile(2471, 3424, 1), new RSTile(2476, 3422, 1));
	  private final RSArea fourthArea = new RSArea(new RSTile(2472, 3421, 2), new RSTile(2477, 3418, 2));
	  private final RSArea fifthArea = new RSArea(new RSTile(2483, 3418, 2), new RSTile(2488, 3421, 2));
	  private final RSArea sixthArea = new RSArea(new RSTile(2482, 3425, 0), new RSTile(2488, 3418, 0));
	  private final RSArea seventhArea = new RSArea(new RSTile(2482, 3431, 0), new RSTile(2489, 3427, 0));
	  private final RSArea finishedArea = new RSArea(new RSTile(2478, 3939), new RSTile(2488, 3436));
	  private final RSArea sixthNetArea = new RSArea(new RSTile(2483, 3425), new RSTile(2488, 3425));
	  private final RSArea sixthFailsafeArea = new RSArea(new RSTile(2490, 3423, 0), new RSTile(2489, 3427, 0));
	  private final RSArea seventhFailsafeArea = new RSArea(new RSTile(2479, 3435), new RSTile(2482, 3431));
	  private final RSArea seventhFailsafeAreaRight = new RSArea(new RSTile(2489, 3436), new RSTile(2490, 3431));
	  private final RSArea seventhEntryArea = new RSArea(new RSTile(2482, 3427), new RSTile(2489, 3431));
	
	//Gnome Stronghold Course Objects
	private RSObject[] gnomeLogBalance;
	private RSObject[] gnomeOBNet1;
	private RSObject[] gnomeBranch1;
	private RSObject[] gnomeBalanceRope;
	private RSObject[] gnomeBranch2;
	private RSObject[] gnomeOBNet2;
	private RSObject[] gnomeOBPipe;
	
	//Gnome Stronghold Course strings
	private final String gnomeLB = "Log balance";
	private final String gnomeNet1 = "Obstacle net";
	private final String gnomeBranch = "Tree branch";
	private final String gnomeBR = "Balancing rope";
	private final String gnomeBranchString = "Tree branch";
	private final String gnomeNet2 = "Obstacle net";
	private final String gnomePipe = "Obstacle pipe";
	
	//Draynor Village Course Objects
	private RSObject[] draynorRoughWall;
	private RSObject[] draynorRope;
	private RSObject[] draynorRope2;
	private RSObject[] draynorNarrowWall;
	private RSObject[] draynorWall;
	private RSObject[] draynorGap;
	private RSObject[] draynorCrate;
	
	//Draynor Village Course strings
	private final String draynorRW = "Rough wall";
	private final String draynorRopeString = "Tightrope";
	private final String draynorRopeString2 = "Tightrope";
	private final String draynorNW = "Narrow wall";
	private final String draynorWallString = "Wall";
	private final String draynorGapString = "Gap";
	private final String draynorCrateString = "Crate";
	
	//Al-karid Course Objects
	
	//Al-karid Course Strings
	
	//Varrock CO
	
	//Varrock CS
	
	//Canifis CO
	
	//Canifis CS
	
	//Falador CO
	
	//Falador CS
	
	//Seers CO
	
	//Seers CS
	
	//polliniovitch CO
	
	//Polliniovitch CS
	
	
	
	//
	//Here BE code for Gnome Course
	//This TODO has nothing to do with having to finish anything
	//just shows where to start
	//
	
	  public void clickLog() {
		    println("Clicking log");
		    
		    // 
		    gnomeLogBalance = Objects.findNearest(10, gnomeLB);
		    
		    if(gnomeLogBalance.length > 0) { 
		    	Clicking.click(gnomeLogBalance);
		    	 if (Camera.getCameraRotation() < 140 && Camera.getCameraRotation() > 200) {
		             Camera.setCameraRotation(General.random(150, 190));
		           }
		           if (Camera.getCameraAngle() < 50) {
		             Camera.setCameraAngle(General.random(1, 27) + 73);
		           }
		    } else {
		    	Timing.waitCondition(new Condition() {
					@Override
					public boolean active() {
						return secondArea.contains(Player.getPosition());
					}
		    	}, random(2500, 4000));
		    }sleep(5000);
	  }
	  
	  public void Net1() {
		  println("Clicking Obstacle net.");
		  gnomeOBNet1 = Objects.findNearest(5, gnomeNet1);
		  if(gnomeOBNet1.length > 0){
			  Clicking.click(gnomeOBNet1);
			  Timing.waitCondition(new Condition() {
					@Override
					public boolean active() {
						return Game.getPlane() == 1;
					}
		    	}, random(2500, 4000));
		  }
	  }
	  
	  public void gnomeBranch1() {
		  println("Climbing up Branch!");
		  gnomeBranch1 = Objects.findNearest(5, gnomeBranch);
		  if (gnomeBranch1.length > 0){
			  Clicking.click(gnomeBranch1);
			  
			  Timing.waitCondition(new Condition() {
		          @Override
		          public boolean active() {
		            return Game.getPlane() == 2;
		          }
		        }, random(2500, 4000));
		  }
	  }
	  
	  public void gnomeBR(){
		  println("Walking across Balancing Rope");
		  gnomeBalanceRope = Objects.findNearest(5, gnomeBR);
		  
		  if (Camera.getCameraRotation() < 200 && Camera.getCameraRotation() > 250) {
		      Camera.setCameraRotation(General.random(210, 240) + 8);
		  }    
		  
		  if (gnomeBalanceRope.length > 0){
			  Clicking.click(gnomeBalanceRope);
			  
			  Timing.waitCondition(new Condition() {
				  public boolean active(){
					  return sixthArea.contains(Player.getPosition());
				  }
			  }, random (2500,4000));
		  } else {
			  Camera.setCameraAngle(50 + General.random(0, 40)); 
		  }
	  }
	  
	  public void gnomeBranch2(){
		  println("Climbing down the branch.");
		  gnomeBranch2 = Objects.findNearest(5, gnomeBranchString);
		  
		  if(gnomeBranch2.length > 0 ){
			  Clicking.click(gnomeBranch2);
			  Timing.waitCondition(new Condition() {
			        @Override
			        public boolean active() {
			          return seventhArea.contains(Player.getPosition());
			        }
			      }, random(2500, 4000));
		  } else {
		        if (Camera.getCameraRotation() < 220) {
		            Camera.setCameraRotation(General.random(230, 300));
		        }
		  }
	  }
	  
	  public void Net2() {
		  println("Clicking  Second Obstacle net.");
		  Camera.setCameraRotation(General.random(1, 110));
		  Camera.setCameraAngle(General.random(85, 100));
		  gnomeOBNet2 = Objects.findNearest(9, gnomeNet2);
		  if(gnomeOBNet2.length > 0){
			  Clicking.click(gnomeOBNet2);
			  Timing.waitCondition(new Condition() {
					@Override
					public boolean active() {
						return Game.getPlane() == 1;
					}
		    	}, random(2500, 4000));
			  
		  }
		  sleep(2500);
	  }
	  
	  public void gnomeOBPipe() {
		    println("Clicking on Pipe.");
		    
		    gnomeOBPipe = Objects.findNearest(10, gnomePipe);
		    
		    if (gnomeOBPipe.length > 0) {
		      Clicking.click(gnomeOBPipe);
		    } 
	  }
	
	public int gnomecourse(){
		if((!isRunOn() && checkRun())){
			turnRunOn();
		    return 0;
		} else if (Player.getAnimation() != -1) {
			sleep(100,200);
		    return 0;
		} else if (Player.isMoving()) {
		    antiBan();          
		    sleep(100, 200);
		    return 0;
		} else if (startArea.contains(Player.getPosition())) {
		    clickLog();
		    return 0;
		} else if(secondArea.contains(Player.getPosition())) {
		    Net1();
		    return 0;
		} else if(thirdArea.contains(Player.getPosition())) {
			gnomeBranch1();
			return 0;
		} else if (fourthArea.contains(Player.getPosition())) {
		    gnomeBR();
		    return 0;
		} else if(fifthArea.contains(Player.getPosition())) {
		    gnomeBranch2();
		    return 0;
		} else if(sixthArea.contains(Player.getPosition())) {
			Net2();
			return 0;
		} else if(seventhEntryArea.contains(Player.getPosition())){
			gnomeOBPipe();
			return 0;
		}  else if (finishedArea.contains(Player.getPosition())) {
		      try {
		          if (Walking.clickTileMM(GNOME_START, 1)) {
		            while (!Game.getDestination().equals(GNOME_START)) {
		              Walking.clickTileMM(GNOME_START, 1);
		            }
		          }
		        } catch (NullPointerException e) {
		          System.err.println("NullPointException: " + e.getMessage());
		        }

		        return 0;
		} else {
			return 0;
		}
	}
	
	//
	//
	//TODO This is the start of the Draynor Course rooftops
	//
	//
	
	
	//checks current agility level then walks to the course.
	/**private void checkAgilityLevel (){
		if(currentLVL >= 1 && currentLVL <=10){
			WebWalking.walkTo(GNOME_START);
		
		(currentLVL >= 10 && currentLVL <=99){
			//TODO later for auto level switching
			WebWalking.walkTo(DRAYNOR_START);
			println("Walking to Draynor Course.");
			if(currentLVL >= 20 && currentLVL <= 29){
				WebWalking.walkTo(ALKARID_START);
			}
		}
	}**/
	
	  // Check the run energy
	  public boolean isRunOn() {
	    return Game.isRunOn();
	  }
	  
	  public boolean checkRun() {
	    return (Game.getRunEnergy() > 30);
	  }
	  
	  public void turnRunOn() {
	    Options.setRunOn(true);
	  }
	  
	  public void checkHealth(){
		  
	  }
	
	
	public void run() {
		// TODO allow custom 
		println("Script started.");
		Mouse.setSpeed(General.random(100, 180));
	    //checkAgilityLevel();
	    //init();
	    while (gnomecourse() >= 0) {
	        sleep(10);    
	    }
	}
	
	 private Image getImage(String url) {
		    try {
		      return ImageIO.read(new URL(url));
		    } catch (IOException e) {
		      return null;
		    }
		  }
	
	//Paint Info
	  public void onPaint(Graphics g)  {
		  
		  Graphics2D gg = (Graphics2D)g;
	      gg.drawImage(img, 0, 304, null);
	      
		  //Paint Variables
	      int currentLvl = Skills.getActualLevel(Skills.SKILLS.AGILITY);
	      int gainedLvl = currentLvl - startLvl;
	      int gainedXP = Skills.getXP(Skills.SKILLS.AGILITY) - startXP;
	      int xpToLevel = Skills.getXPToNextLevel(Skills.SKILLS.AGILITY);
	      long xpPerHour = (long) (gainedXP * 3600000 / this.getRunningTime()); 

	      g.setColor(new Color(102, 102, 102));
	      g.drawString("Runtime: " + Timing.msToString(this.getRunningTime()), 363, 370);
	      g.drawString("Current lvl: " + currentLvl + " (+" + gainedLvl + ")", 363, 390);
	      g.drawString("XP Gained: " + gainedXP, 363, 410);
	      g.drawString("XP TNL: " + xpToLevel, 363, 430);
	      g.drawString("XP/H: " + xpPerHour, 363, 450);
	    
	  }

	@Override
	public void passArguments(HashMap<String, String> arg0) {
		// TODO Auto-generated method stub
		
	}  
	  
	 //GUI
	
}
