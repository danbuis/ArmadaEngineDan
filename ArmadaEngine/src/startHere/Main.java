package startHere;

import geometry.*;
import shipCore.*;
import gameCore.*;

public class Main {

	public static void main(String[] args) {
	DiceRoll dice = new DiceRoll(0,0,2000);
	System.out.println("Blank: " + dice.getBlankCount());
	System.out.println("Hit: " + dice.getHitCount());
	System.out.println("Double w/ crit: " + dice.getResultCount(GameConstants.DiceResult.HITCRIT));
	}

}
