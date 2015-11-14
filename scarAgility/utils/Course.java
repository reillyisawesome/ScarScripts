package scripts.scarAgility.utils;

import org.tribot.api.General;
import org.tribot.api2007.types.RSArea;
import org.tribot.api2007.types.RSObject;
import scripts.scarAgility.utils.areas.Area;
import scripts.scarAgility.utils.areas.CourseDefinition;
import scripts.scarAgility.utils.areas.normal.Barbarian;
import scripts.scarAgility.utils.areas.normal.Gnome;
import scripts.scarAgility.utils.areas.rooftops.Draynor;

public enum Course {

    GNOME (new Gnome()),
    BARBARIAN (new Barbarian()),
	DRAYNOR (new Draynor());
	//ALKARID (new Alkarid());
	//VARROCK (new Varrock());

    private CourseDefinition courseDefinition;

    Course(CourseDefinition agilityCourse){
        this.courseDefinition = agilityCourse;
    }

    public String getPlayerStatus(){
        return courseDefinition.getPlayerStatus();
    }

    public Area getNextArea(){
        return courseDefinition.getNextArea();
    }

    public Area getCurrentArea(){
        return courseDefinition.getCurrentArea();
    }

    public RSObject getNextObstacle(){
        return courseDefinition.getNextObstacle();
    }

    public RSObject getCurrentObstacle(){
        return courseDefinition.getCurrentObstacle();
    }

    public boolean isInCourse(){
        return courseDefinition.isInCourse();
    }

    public RSArea getStartingArea(){
        return courseDefinition.getStartingArea();
    }

    @Override
    public String toString(){
        return courseDefinition.toString();
    }

}
