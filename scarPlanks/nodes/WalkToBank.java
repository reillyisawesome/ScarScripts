package scripts.scarPlanks.nodes;

import org.tribot.api.interfaces.Positionable;
import org.tribot.api2007.Banking;
import org.tribot.api2007.Player;
import org.tribot.api2007.WebWalking;

import scripts.scarPlanks.PlankValues;
import scripts.scarPlanks.nodehandler.Node;

public class WalkToBank extends Node {

	@Override
	public void execute() {
		
	}

	@Override
	public boolean validate() {
		return !Banking.isInBank();
	}

	@Override
	public int priority() {
		return 0;
	}

}
