package scripts.scarAgility.utils.areas.normal;

import org.tribot.api.types.generic.Filter;
import org.tribot.api2007.Objects;
import org.tribot.api2007.Player;
import org.tribot.api2007.types.RSArea;
import org.tribot.api2007.types.RSObject;
import org.tribot.api2007.types.RSObjectDefinition;
import org.tribot.api2007.types.RSTile;
import scripts.scarAgility.utils.areas.Area;
import scripts.scarAgility.utils.areas.CourseDefinition;

public class Barbarian extends CourseDefinition {

    private enum CourseArea implements Area {
        ROPE_SWING_AREA(new RSArea(new RSTile[]{
                new RSTile(2543, 3557, 0), new RSTile(2546, 3557, 0), new RSTile(2546, 3556, 0), new RSTile(2550, 3556, 0), new RSTile(2550, 3557, 0),
                new RSTile(2554, 3557, 0), new RSTile(2554, 3552, 0), new RSTile(2550, 3552, 0), new RSTile(2550, 3550, 0), new RSTile(2543, 3550, 0)
        }), "Ropeswing"),
        FAIL_ROPE_SWING(new RSArea(new RSTile[]{
                new RSTile(2548, 9956, 0), new RSTile(2553, 9956, 0), new RSTile(2556, 9954, 0), new RSTile(2556, 9950, 0), new RSTile(2554, 9948, 0),
                new RSTile(2547, 9948, 0), new RSTile(2546, 9950, 0), new RSTile(2546, 9953, 0)
        }), "Ladder"),
        LOG_BALANCE_AREA(new RSArea(new RSTile[]{
                new RSTile(2550, 3550, 0), new RSTile(2554, 3550, 0), new RSTile(2554, 3542, 0), new RSTile(2544, 3542, 0), new RSTile(2543, 3548, 0),
                new RSTile(2550, 3548, 0)
        }), "Log balance"),
        NET_AREA(new RSArea(new RSTile[]{
                new RSTile(2538, 3548, 0), new RSTile(2543, 3548, 0), new RSTile(2542, 3546, 0), new RSTile(2543, 3544, 0), new RSTile(2538, 3544, 0)
        }), "Obstacle net"),
        LEDGE_AREA(new RSArea(new RSTile[]{
                new RSTile(2535, 3548, 1), new RSTile(2539, 3548, 1), new RSTile(2539, 3545, 1), new RSTile(2536, 3545, 1), new RSTile(2536, 3547, 1),
                new RSTile(2535, 3547, 1)
        }), "Balancing ledge"),
        UPSTAIRS_LADDER(new RSArea(new RSTile[]{
                new RSTile(2532, 3547, 1), new RSTile(2533, 3547, 1), new RSTile(2533, 3545, 1), new RSTile(2532, 3545, 1)
        }), "Ladder"),
        FIRST_RUBBLE(new RSArea(new RSTile[]{
                new RSTile(2533, 3545, 0), new RSTile(2531, 3545, 0), new RSTile(2531, 3555, 0), new RSTile(2537, 3555, 0), new RSTile(2537, 3545, 0)
        }), "Crumbling wall"),
        SECOND_RUBBLE(new RSArea(new RSTile[]{
                new RSTile(2537, 3555, 0), new RSTile(2540, 3555, 0), new RSTile(2540, 3552, 0), new RSTile(2537, 3552, 0)
        }), "Crumbling wall"),
        THIRD_RUBBLE(new RSArea(new RSTile[]{
                new RSTile(2540, 3555, 0), new RSTile(2543, 3555, 0), new RSTile(2543, 3552, 0), new RSTile(2540, 3552, 0)
        }), "Crumbling wall");


        private RSArea rsArea;
        private String obstacleName;

        CourseArea(RSArea rsArea, String obstacleName){
            this.rsArea = rsArea;
            this.obstacleName = obstacleName;
        }

        @Override
        public Area getNextArea() {
            switch (this){
                case ROPE_SWING_AREA: return LOG_BALANCE_AREA;
                case FAIL_ROPE_SWING: return ROPE_SWING_AREA;
                case LOG_BALANCE_AREA: return NET_AREA;
                case NET_AREA: return LEDGE_AREA;
                case LEDGE_AREA: return UPSTAIRS_LADDER;
                case UPSTAIRS_LADDER: return FIRST_RUBBLE;
                case FIRST_RUBBLE: return SECOND_RUBBLE;
                case SECOND_RUBBLE: return THIRD_RUBBLE;
                case THIRD_RUBBLE: return ROPE_SWING_AREA;
            }
            return null;
        }

        @Override
        public RSArea getRSArea() {
            return rsArea;
        }

        @Override
        public RSObject getObstacle() {
            int x = -1;
            switch (this){
                case FIRST_RUBBLE: x = 2536;
                    break;
                case SECOND_RUBBLE: x = 2539;
                    break;
                case THIRD_RUBBLE: x = 2542;
            }
            if (x != -1){
                RSObject[] objects = Objects.getAt(new RSTile(x, 3553, 0));
                return objects.length > 0 ? objects[0] : null;
            }
            RSObject[] objects = Objects.findNearest(20, new Filter<RSObject>() {
                @Override
                public boolean accept(RSObject object) {
                    RSObjectDefinition def = object.getDefinition();
                    if (def != null){
                        String name = def.getName();
                        if (name != null){
                            return name.equals(obstacleName) && def.getActions().length > 0;
                        }
                    }
                    return false;
                }
            });
            return objects.length > 0 ? objects[0] : null;
        }

        @Override
        public boolean containsPlayer() {
            return rsArea.contains(Player.getPosition());
        }

        @Override
        public RSTile getOptimalLocation() {
            switch (this){
                case ROPE_SWING_AREA: return new RSArea(new RSTile[]{
                        new RSTile(2550, 3556, 0), new RSTile(2553, 3556, 0), new RSTile(2553, 3554, 0), new RSTile(2550, 3554, 0)}).getRandomTile();
            }
            return null;
        }

    }

    @Override
    public Area[] getCourseAreas() {
        return CourseArea.values();
    }

    @Override
    public String toString() {
        return "Barbarian Agility Course";
    }

    @Override
    public String getPlayerStatus() {
        int animation = Player.getAnimation();
        if (animation == 840){
            return "Climbing";
        }
        if (animation == 751){
            return "Swinging";
        }
        if (animation == 828 || animation == 827){
            return "Climbing";
        }
        RSTile playerPosition = Player.getPosition();
        int x = playerPosition.getX(), y = playerPosition.getY(), z = playerPosition.getPlane();
        if (y == 3546 && x > 2541 && x < 2552){
            return "Crossing Log";
        }
        if (y == 3547 && x > 2532 && x < 2536){
            return "Crossing Wall";
        }
        return null;
    }

}
