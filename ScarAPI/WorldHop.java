package scripts.ScarAPI;

import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.input.Mouse;
import org.tribot.api.types.generic.Condition;
import org.tribot.api2007.*;
import org.tribot.api2007.types.RSInterfaceChild;
import org.tribot.api2007.types.RSInterfaceComponent;

import java.awt.*;

//
//
//	WorldHopping API by Jazed (Tribot)
//
//		Example use: WorldHop.hopTo(69);
//					 WorldHop.getRandomWorld(true); (True for Members False for Free)
//
//

public class WorldHop
{
    private final static int[] memberWorlds = new int[]{2, 3, 4, 5, 6, 9, 10, 11, 12, 13, 14,17, 18, 19, 20, 21, 22, 27, 28, 29, 30, 33, 34,
            36, 38, 41, 42, 43, 44, 45, 46, 49, 50, 51, 52, 54, 57, 58, 59, 60, 62, 67, 68, 69,
            70, 74, 75, 76, 77, 78, 86};
    private final static int[] freeWorlds = new int[]{1, 8, 26, 35, 81, 82, 83, 84, 85, 93, 94};

    public static int getRandomWorld(boolean members)
    {
        return (members) ? memberWorlds[General.random(0, memberWorlds.length - 1)] : freeWorlds[General.random(0, freeWorlds.length - 1)];
    }

    public static boolean hopTo(int world)
    {
        if (world > 300)
            world = world - 300;

        return doHopping(world, 0);
    }

    private static boolean doHopping(int world, int retries)
    {
        if (!GameTab.getOpen().equals(GameTab.TABS.LOGOUT))
        {
            GameTab.open(GameTab.TABS.LOGOUT);
        }

        checkSwitchButton();

        RSInterfaceChild worldList = Interfaces.get(69, 7);
        Rectangle worldBox = worldList.getAbsoluteBounds();
        RSInterfaceComponent worldComponent = getWorldComponent(world, worldList);

        if (worldBox != null && worldComponent != null)
        {
            if (worldBox.contains(worldComponent.getAbsoluteBounds()))
            {
                worldComponent.hover();

                String uptext = Game.getUptext();

                if (uptext != null)
                {
                    if (uptext.split("/")[0].replace("Switch", "").replaceAll(" ", "").equals(String.valueOf(world)))
                    {
                        Mouse.click(1);

                        General.sleep(850, 1300);

                        checkNPCChat();

                        Timing.waitCondition(getSwitchedWorld(world), General.random(3000, 6000));

                        if (WorldHopper.getWorld() == world)
                            return true;
                    }
                }

                if (retries >= 3)
                    return false;

                doHopping(world, retries + 1);
            }
            else
            {
                scrollToWorldComponent(worldBox, worldComponent);
                doHopping(world, retries);
            }
        }

        return false;
    }

    private static RSInterfaceComponent getWorldComponent(int world, RSInterfaceChild worldList)
    {
        if (worldList != null)
        {
            for (RSInterfaceComponent c : worldList.getChildren())
            {
                if (c.getText().equals(String.valueOf(world)))
                {
                    return worldList.getChildren()[c.getIndex() - 2];
                }
            }
        }

        return null;
    }

    private static void scrollToWorldComponent(Rectangle worldBox, RSInterfaceComponent worldComponent)
    {
        while (!worldBox.contains(Mouse.getPos()))
        {
            Mouse.moveBox(worldBox);
        }

        while (!worldBox.contains(worldComponent.getAbsoluteBounds()))
        {
            Mouse.scroll(worldComponent.getAbsoluteBounds().y < worldBox.y);
            General.sleep(50, 100);
        }

        if (worldComponent.getAbsoluteBounds().y > worldBox.y + worldBox.getHeight() - worldComponent.getHeight())
        {
            Mouse.scroll(false);
            General.sleep(50, 100);
        }
    }

    private static void checkSwitchButton()
    {
        RSInterfaceChild worldSwitchButton = Interfaces.get(182, 5);

        if (worldSwitchButton != null)
        {
            worldSwitchButton.click("World Switcher");

            Timing.waitCondition(new Condition()
            {
                @Override
                public boolean active()
                {
                    return worldSwitchButton == null;
                }
            }, General.random(2000, 3000));
        }
    }

    private static void checkNPCChat()
    {
        String option = "Yes. In future, only warn about dangerous worlds.";

        String[] options = NPCChat.getOptions();

        if (options != null)
        {
            if (options[1] != null && options[1].equals(option))
            {
                NPCChat.selectOption(option, true);
            }
        }
    }

    private static Condition getSwitchedWorld(final int world)
    {
        return new Condition()
        {
            public boolean active()
            {
                return WorldHopper.getWorld() == world;
            }
        };
    }
}