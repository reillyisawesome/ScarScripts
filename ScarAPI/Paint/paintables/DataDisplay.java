package scripts.ScarAPI.Paint.paintables;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import scripts.ScarAPI.*;
import scripts.ScarAPI.Paint.Paintable;

public class DataDisplay extends Paintable<String[]> {

	private int width;
	private int height;

	public DataDisplay() {
		this(new String[0], 255, 349, 260, 76);
	}

	public DataDisplay(String[] t, int x, int y, int width, int height) {
		super(t, x, y);
		this.width = width;
		this.height = height;
	}

	@Override
	public void draw(Graphics g, long time) {
		g.setColor(DARK_GREY);
		g.fillRect(x, y, width, super.get().length * 19);
		g.setFont(ARIAL_SIZE_ELEVEN);
		int c = 0;
		for (String s : super.get()) {
			g.setColor(VERY_LIGHT_GREY);
			int length = super.getStringPixelLength(s, g);
			g.fillRect(x + 5, (y + 5) + (17 * c), length + 20, 14);
			g.setColor(Color.BLACK);
			g.drawRect(x + 5, (y + 5) + (17 * c), length + 20, 14);
			g.drawString(s, x + 15, (y + 16) + 17 * c);
			c++;
		}

	}

	
	@Override
	public int getWidth() {
		return width;
	}

	@Override
	public int getHeight() {
		return height;
	}

	@Override
	protected boolean isInClick(Point p) {
		Rectangle rec = new Rectangle(x, y, width, height);
		return rec.contains(p);
	}

}
