package scripts.ScarAPI.Paint.paintables;

import java.awt.Graphics;
import java.awt.Point;

import org.tribot.api2007.types.RSNPC;

import scripts.ScarAPI.Paint.Paintable;


public class MonsterDisplay extends Paintable<RSNPC> {

	private boolean target;

	public MonsterDisplay(RSNPC t, boolean target) {
		this(t, getPaintPosition(t), target);
	}

	private MonsterDisplay(RSNPC t, Point p, boolean target) {
		super(t, p.x, p.y);
		this.target = target;
	}

	@Override
	public void draw(Graphics g, long time) {
	}

	private static Point getPaintPosition(RSNPC t) {
		return null;
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
	public void update(RSNPC t) {

	}

}
