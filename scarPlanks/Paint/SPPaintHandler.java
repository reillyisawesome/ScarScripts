package scripts.scarPlanks.Paint;
import java.awt.Graphics;

import scripts.ScarAPI.Paint.PaintHandler;
import scripts.ScarAPI.Paint.Paintable;
import scripts.ScarAPI.Paint.paintables.DataDisplay;
import scripts.ScarAPI.Paint.paintables.tabs.PaintPanel;
import scripts.ScarAPI.Paint.types.CGUI;

public class SPPaintHandler extends PaintHandler{
	
	private PaintPanel panel;
	private String version;
	private CGUI gui;
	private DataDisplay generic_data_display;
	
	public SPPaintHandler(CGUI gui, String version) {
		this.generic_data_display = new DataDisplay();
		this.generic_data_display.register(this);

	}

	@Override
	public void update(long run_time) {
		this.generic_data_display.update((String[]) getGenericDataDisplay(run_time));
	}

	private Object getGenericDataDisplay(long run_time) {
		String[] info_array = { "Runtime: " + getFormattedTime(run_time),
				"Version" + version };
		return info_array;
	}

	@Override
	public void draw(Graphics g, long run_time) {
		
	}

}
