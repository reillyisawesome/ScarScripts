package scripts.ScarAPI.Paint.paintables;

import java.awt.Graphics;
import java.awt.Point;

import org.tribot.api2007.types.RSGroundItem;

import scripts.ScarAPI.Paint.Paintable;


public class LootItemDisplay extends Paintable<RSGroundItem> {

	public LootItemDisplay(RSGroundItem t, int x, int y) {
		super(t, x, y);
	}

	@Override
	public void draw(Graphics g, long time) {

	}

	@Override
	public int getWidth() {
		return 0;
	}

	@Override
	public int getHeight() {
		return 0;
	}
	
	@Override
	protected boolean isInClick(Point p) {
		return false;
	}

	@Override
	public void update(RSGroundItem t) {

	}

}
