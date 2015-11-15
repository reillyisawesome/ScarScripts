package scripts.scarAgility.utils.areas.rooftops;

import org.tribot.api.types.generic.Filter;
import org.tribot.api2007.Objects;
import org.tribot.api2007.Player;
import org.tribot.api2007.types.RSArea;
import org.tribot.api2007.types.RSObject;
import org.tribot.api2007.types.RSObjectDefinition;
import org.tribot.api2007.types.RSTile;

import scripts.scarAgility.utils.action.Manager;
import scripts.scarAgility.utils.areas.CourseDefinition;
import scripts.scarAgility.utils.areas.Area;

public class Draynor extends CourseDefinition{
	enum CourseArea implements Area {
		//START_AREA, "Ropeswing");
		;
		
		private RSArea rsArea;
        private String obstacleName;

        CourseArea(RSArea rsArea, String obstacleName){
            this.rsArea = rsArea;
            this.obstacleName = obstacleName;
        }

		@Override
		public Area getNextArea() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public RSArea getRSArea() {
			return rsArea;
		}

		@Override
		public RSObject getObstacle() {
			RSObject[] objects = Objects.findNearest(20, obstacleName);
            return objects.length > 0 ? objects[0] : null;
		}

		@Override
		public boolean containsPlayer() {
			return rsArea.contains(Player.getPosition());
		}

		@Override
		public RSTile getOptimalLocation() {
			// TODO Auto-generated method stub
			return null;
		}
		
	}

	@Override
	public Area[] getCourseAreas() {
		return CourseArea.values();
	}

	@Override
	public String toString() {
		return "Draynor Rooftop Course";
	}

	@Override
	public String getPlayerStatus() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
