package scripts.scarAgility.utils.areas.rooftops;

import org.tribot.api.types.generic.Filter;
import org.tribot.api2007.Objects;
import org.tribot.api2007.Player;
import org.tribot.api2007.types.RSArea;
import org.tribot.api2007.types.RSObject;
import org.tribot.api2007.types.RSObjectDefinition;
import org.tribot.api2007.types.RSTile;
import scripts.scarAgility.utils.areas.CourseDefinition;
import scripts.scarAgility.utils.areas.Area;

public class Draynor extends CourseDefinition{
	private enum CourseArea implements Area {
        FIRST_AREA (new RSArea(new RSTile[]{
                new RSTile(2469, 3437, 0), new RSTile(2473, 3441, 0), new RSTile(2487, 3441, 0), new RSTile(2491, 3437, 0), new RSTile(2490, 3436, 0),
                new RSTile(2488, 3436, 0), new RSTile(2488, 3437, 0), new RSTile(2486, 3437, 0), new RSTile(2486, 3436, 0), new RSTile(2485, 3436, 0),
                new RSTile(2485, 3437, 0), new RSTile(2484, 3437, 0), new RSTile(2484, 3436, 0), new RSTile(2469, 3436, 0)
        }), "Rough wall"),
        SECOND_AREA (new RSArea(new RSTile[]{
                new RSTile(2470, 3430, 0), new RSTile(2479, 3430, 0), new RSTile(2479, 3426, 0), new RSTile(2470, 3426, 0)
        }), "Tightrope"),
        THIRD_AREA (new RSArea(new RSTile[]{
                new RSTile(2477, 3425, 1), new RSTile(2477, 3422, 1), new RSTile(2471, 3422, 1), new RSTile(2471, 3425, 1)
        }), "Tightrope"),
        FOURTH_AREA (new RSArea(new RSTile[]{
                new RSTile(2472, 3422, 2), new RSTile(2476, 3422, 2), new RSTile(2476, 3421, 2), new RSTile(2478, 3421, 2), new RSTile(2478, 3419, 2),
                new RSTile(2476, 3419, 2), new RSTile(2476, 3418, 2), new RSTile(2472, 3418, 2)
        }), "Narrow wall"),
        FIFTH_AREA (new RSArea(new RSTile[]{
                new RSTile(2483, 3421, 2), new RSTile(2485, 3421, 2), new RSTile(2485, 3422, 2), new RSTile(2489, 3422, 2), new RSTile(2489, 3418, 2),
                new RSTile(2485, 3418, 2), new RSTile(2485, 3419, 2), new RSTile(2483, 3419, 2)
        }), "Wall"),
        SIXTH_AREA (new RSArea(new RSTile[]{
                new RSTile(2480, 3426, 0), new RSTile(2491, 3426, 0), new RSTile(2491, 3418, 0), new RSTile(2481, 3418, 0)
        }), "Gap"),
        SEVENTH_AREA (new RSArea(new RSTile[]{
                new RSTile(2481, 3432, 0), new RSTile(2491, 3432, 0), new RSTile(2491, 3427, 0), new RSTile(2481, 3427, 0)
        }), "Crate");


        private RSArea area;
        private String obstacleName;

        CourseArea(RSArea area, String obstacleName){
            this.area = area;
            this.obstacleName = obstacleName;
        }

        @Override
        public Area getNextArea() {
            switch (this){
                case FIRST_AREA: return SECOND_AREA;
                case SECOND_AREA: return THIRD_AREA;
                case THIRD_AREA: return FOURTH_AREA;
                case FOURTH_AREA: return FIFTH_AREA;
                case FIFTH_AREA: return SIXTH_AREA;
                case SIXTH_AREA: return SEVENTH_AREA;
                case SEVENTH_AREA: return FIRST_AREA;
            }
            return null;
        }

        @Override
        public RSArea getRSArea(){
            return area;
        }

        @Override
        public boolean containsPlayer(){
            return area.contains(Player.getPosition());
        }

        @Override
        public RSTile getOptimalLocation() {
            return null;
        }

        @Override
        public RSObject getObstacle(){
            RSObject[] objects = Objects.findNearest(20, obstacleName);
            return objects.length > 0 ? objects[0] : null;
        }

    }

    @Override
    public String getPlayerStatus() {
        int animation = Player.getAnimation();
        if (animation == 828){
            return "Climbing";
        }
        if (animation == 749){
            return "Crawling";
        }
        RSTile playerPosition = Player.getPosition();
        int x = playerPosition.getX(), y = playerPosition.getY(), z = playerPosition.getPlane();
        if (x == 2474 && y < 3436 && y > 3429){
            return "Crossing Log";
        }
        if (z == 2 && y == 3420 && x > 2477 && x < 2483){
            return "Walking rope";
        }
        if (x == 2484 || x == 2487){
            if (y > 3430 && y < 3437){
                return "Crawling";
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "Draynor Rooftop Course";
    }

    @Override
    public Area[] getCourseAreas() {
        return CourseArea.values();
    }

    @Override
    public RSObject getNextObstacle(){
        if (getCurrentArea() == CourseArea.FIRST_AREA){
            RSObject[] objects = Objects.findNearest(20, new Filter<RSObject>() {
                @Override
                public boolean accept(RSObject object) {
                    RSObjectDefinition definition = object.getDefinition();
                    if (definition  != null){
                        String name = definition.getName();
                        return name.equals("Obstacle net") && object.getPosition().getX() <= 2475;
                    }
                    return false;
                }
            });
            return objects.length > 0 ? objects[0] : null;
        }
        return super.getNextObstacle();
    }
}
