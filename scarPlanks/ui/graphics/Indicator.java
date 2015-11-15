package scripts.scarPlanks.ui.graphics;

import org.tribot.api2007.Player;
import org.tribot.api2007.types.RSModel;
import org.tribot.api2007.types.RSPlayer;

import java.awt.*;
import java.awt.font.TextAttribute;
import java.text.AttributedString;

public class Indicator {

    public static void playerStatus(Graphics g, String playerstatus){
                    String message = "Status: " + playerstatus;
                    AttributedString as = new AttributedString(message);
                    as.addAttribute(TextAttribute.FOREGROUND, Color.GREEN);
                    as.addAttribute(TextAttribute.FOREGROUND, Color.WHITE, 0, 7);
                    g.drawString(as.getIterator(),363, 470);
    }

}
