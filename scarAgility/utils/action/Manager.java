package scripts.scarAgility.utils.action;



import org.tribot.api.DynamicClicking;
import org.tribot.api.General;
import org.tribot.api.input.Mouse;
import org.tribot.api.types.generic.Condition;
import org.tribot.api.util.ABCUtil;
import org.tribot.api2007.Game;
import org.tribot.api2007.Options;
import org.tribot.api2007.Player;
import org.tribot.api2007.Skills;
import org.tribot.api2007.Skills.SKILLS;
import org.tribot.api2007.WebWalking;
import org.tribot.api2007.types.RSArea;
import org.tribot.api2007.types.RSGroundItem;
import org.tribot.api2007.types.RSTile;
import org.tribot.api2007.GroundItems;

import scripts.scarAgility.ui.MainGUI;
import scripts.scarAgility.utils.Course;
import scripts.scarAgility.utils.action.obstacle.Obstacle;
import scripts.scarAgility.utils.areas.Area;

public class Manager {

    private Course course;
    private ABCUtil abcUtil;
    private CameraPositioner cameraPositioner;
    private Area currentArea, nextArea;
    private int failClickCount, failLimit;
    private State state;
    private long lastBusyTime;

    public Manager(MainGUI gui){
        this.course = gui.getCourse();
        this.abcUtil = new ABCUtil();
        cameraPositioner = new CameraPositioner();
        failClickCount = 0;
        failLimit = General.random(5, 10);
        lastBusyTime = System.currentTimeMillis();
    }

    public State determineState(){
        if (!Game.isRunOn() && Game.getRunEnergy() > abcUtil.INT_TRACKER.NEXT_RUN_AT.next()){
            return State.RUN_ENERGY;
        }
        if (failClickCount > failLimit){
            return State.STUCK;
        }
        if (course.isInCourse()){
            return State.INSIDE_COURSE;
        } else {
            return State.WALK_TO_COURSE;
        }
    }

    public void loop(){
        switch ((state = determineState())){

            case STUCK:
                cameraPositioner.setAngle(General.random(70, 100));
                cameraPositioner.setRotation(General.random(0, 360));
                failClickCount = 0;
                failLimit = General.random(5, 10);
                Mouse.pickupMouse();
                lastBusyTime = System.currentTimeMillis();
                break;

            case WALK_TO_COURSE:
                abcUtil.waitNewOrSwitchDelay(lastBusyTime, false);
                RSArea area = course.getStartingArea();
                if (area != null){
                    WebWalking.walkTo(area.getRandomTile(), new Condition() {
                        @Override
                        public boolean active() {
                            return course.isInCourse();
                        }
                    }, 500);
                    lastBusyTime = System.currentTimeMillis();
                }
                break;

            case INSIDE_COURSE:

                General.println("Current Area: " + (currentArea = course.getCurrentArea()));
                General.println("Next Area: " + (nextArea = course.getNextArea()));

                abcUtil.waitNewOrSwitchDelay(lastBusyTime, true);
                if (Obstacle.maneuver(course, cameraPositioner, abcUtil.BOOL_TRACKER.HOVER_NEXT.next())){
                    abcUtil.performTimedActions(Skills.SKILLS.AGILITY);
                    failClickCount = 0;
                    Obstacle.prepareForNext(course, abcUtil.BOOL_TRACKER.HOVER_NEXT.next(), cameraPositioner);
                } else {
                    failClickCount++;
                }
                abcUtil.BOOL_TRACKER.HOVER_NEXT.reset();
                lastBusyTime = System.currentTimeMillis();
                break;

            case RUN_ENERGY:
                Options.setRunOn(true);
                abcUtil.INT_TRACKER.NEXT_RUN_AT.reset();
                lastBusyTime = System.currentTimeMillis();
                break;
            case TOKEN_PICKUP:
            	final String MOG = "Mark of Grace"; 
            	RSGroundItem[] marks = GroundItems.find(MOG);
            	RSTile myPos = Player.getPosition();
            	if (marks.length > 0){
            		RSTile marksTile = marks[0].getPosition();
            		if(marksTile.distanceTo(Player.getPosition()) > 8){
            			WebWalking.walkTo(marksTile);
            			
            		}
            	}
        }
    }

    public Area getCurrentArea() {
        return currentArea;
    }

    public Area getNextArea() {
        return nextArea;
    }

    public State getState() {
        return state;
    }

    public String getPlayerStatus(){
        return course.getPlayerStatus();
    }
}
