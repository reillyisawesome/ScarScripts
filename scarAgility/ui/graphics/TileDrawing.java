package scripts.scarAgility.ui.graphics;

import org.tribot.api2007.types.RSArea;
import org.tribot.api2007.types.RSTile;
import org.tribot.api2007.util.ProjectionUtility;
import scripts.scarAgility.utils.areas.Area;

import java.awt.*;


public class TileDrawing {

    public static void drawArea(Graphics g, Area area, ProjectionUtility projectionUtility){
        if (area != null){
            RSArea rsArea = area.getRSArea();
            if (rsArea != null){
                for (RSTile tile : rsArea.getAllTiles()){
                    Polygon tileBounds = projectionUtility.getTileBoundsPoly(tile, 0);
                    if (tileBounds != null) {
                        g.fillPolygon(tileBounds);
                    }
                }
            }
        }
    }

	public static void drawArea1(Graphics g, Area area, ProjectionUtility projectionUtility) {
		// TODO Auto-generated method stub
		
	}

}