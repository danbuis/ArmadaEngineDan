package gameCore;

import java.util.Random;
import gameCore.GameConstants.*;


public class DiceBase {
	/*
	 * enumerated fields. results are the available results the game
	 * understands; Color pertains to the color of the die. mostly affects the
	 * rolltables used;
	 */


	public static enum Colors {
		RED, BLUE, BLACK
	};

	protected DiceResult[] rollTable = new DiceResult[8];
	Random rd = new Random();
	public DiceResult facing = null;
	public Colors color = null;

	public DiceBase(Colors color) {
		populateRollTable(color);
		facing = roll();
		this.color = color;
	}

	public DiceBase(Colors color, DiceResult result) {
		populateRollTable(color);
		if (isOnRollTable(result)) {
			facing = result;
			this.color = color;
		} else {
			System.out.println("Something just tried to add a result that the die can't have." + color + " " + result);
		}
	}
	
	public String toString(){
		return (color + " " + facing);
	}

	/*
	 * Rolls a die. Looks up a random value on the roll table and returns that
	 * value
	 */
	public DiceResult roll() {
		return rollTable[rd.nextInt(rollTable.length)];
	}

	public int getDamageValue() {
		switch (facing) {
		case BLANK:
		case ACCURACY:
			return 0;
		case HIT:
		case CRIT:
			return 1;
		case HITHIT:
		case HITCRIT:
			return 2;
		default:
			return 0;

		}
	}

	public int getHitCount() {
		switch (facing) {
		case HIT:
		case HITCRIT:
			return 1;
		case HITHIT:
			return 2;
		default:
			return 0;
		}
	}

	public int getCritCount() {
		switch (facing) {
		case CRIT:
		case HITCRIT:
			return 1;
		default:
			return 0;
		}
	}

	public int getAccuracyCount() {
		switch (facing) {
		case ACCURACY:
			return 1;
		default:
			return 0;
		}
	}

	public int getBlankCount() {
		switch (facing) {
		case BLANK:
			return 1;
		default:
			return 0;
		}
	}

	public boolean isOnRollTable(DiceResult r) {
		for (int i = 0; i < rollTable.length; i++) {
			if (rollTable[i].equals(r)) {
				return true;
			}
		}
		return false;
	}

	/*
	 * populates the die's rolltable according to its color
	 */
	public void populateRollTable(Colors color) {
		switch (color) {
		case RED:
			rollTable[0] = DiceResult.BLANK;
			rollTable[1] = DiceResult.BLANK;
			rollTable[2] = DiceResult.ACCURACY;
			rollTable[3] = DiceResult.HIT;
			rollTable[4] = DiceResult.HIT;
			rollTable[5] = DiceResult.HITHIT;
			rollTable[6] = DiceResult.CRIT;
			rollTable[7] = DiceResult.CRIT;
			break;
		case BLUE:
			rollTable[0] = DiceResult.HIT;
			rollTable[1] = DiceResult.HIT;
			rollTable[2] = DiceResult.HIT;
			rollTable[3] = DiceResult.HIT;
			rollTable[4] = DiceResult.ACCURACY;
			rollTable[5] = DiceResult.ACCURACY;
			rollTable[6] = DiceResult.CRIT;
			rollTable[7] = DiceResult.CRIT;
			break;
		case BLACK:
			rollTable[0] = DiceResult.BLANK;
			rollTable[1] = DiceResult.BLANK;
			rollTable[2] = DiceResult.HIT;
			rollTable[3] = DiceResult.HIT;
			rollTable[4] = DiceResult.HIT;
			rollTable[5] = DiceResult.HIT;
			rollTable[6] = DiceResult.HITCRIT;
			rollTable[7] = DiceResult.HITCRIT;
			break;
		}// end switch(color)
	}
}
