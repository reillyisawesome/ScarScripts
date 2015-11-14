package scripts.ScarAPI.Paint.paintables.generic;

import java.awt.Point;

import scripts.ScarAPI.Paint.paintables.ButtonDisplay;
import scripts.ScarAPI.Paint.types.CGUI;

public class ShowGUIButton extends ButtonDisplay {

	private CGUI gui;

	public ShowGUIButton(CGUI gui) {
		this(230, 460, 12, gui);
	}

	public ShowGUIButton(int x, int y, int height, CGUI gui) {
		this(true, "Show GUI", x, y, 57, height, gui);
	}

	private ShowGUIButton(boolean can_hide, String t, int x, int y, int width,
			int height, CGUI gui) {
		super(t, x, y, width, height);
		this.gui = gui;
	}

	@Override
	public void onClick(Point p) {
		this.gui.setVisible(true);
	}

}
