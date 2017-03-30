package gameCore;

import java.util.ArrayList;

import gameCore.GameConstants.DiceResult;

public class DiceRoll {
	public ArrayList<DiceBase> roll = new ArrayList<>();

	public DiceRoll(int redDice, int blueDice, int blackDice) {
		if (redDice > 0) {
			for (int i = 0; i < redDice; i++) {
				roll.add(new DiceBase(DiceBase.Colors.RED));
			}
		}
		if (blueDice > 0) {
			for (int i = 0; i < blueDice; i++) {
				roll.add(new DiceBase(DiceBase.Colors.BLUE));
			}
		}
		if (blackDice > 0) {
			for (int i = 0; i < blackDice; i++) {
				roll.add(new DiceBase(DiceBase.Colors.BLACK));
			}
		}
	}

	public String toString() {
		return (roll.toString());
	}
	/*
	 * Attempts to add a die of a certain color
	 * returns true if successful
	 */
	public boolean addDie(DiceBase.Colors color) {
		if(canAddDie(color)){
			roll.add(new DiceBase(color));
			return true;
		}
		return false;
	}
	/*
	 * attempts to add dice of a certain color
	 * return true if successful
	 */
	public boolean addDice(DiceBase.Colors color, int count) {
		if (canAddDice(color, count)) {
			if (count > 0) {
				for (int i = 0; i < count; count++) {
					addDie(color);
				}
				return true;
			}
		}
		return false;
	}
	/*
	 * attempts to remove a die with a certain result.
	 * returns true if successful
	 */
	public boolean removeDie(DiceResult result) {
		if (canRemoveDie(result)) {
			for (int i = 0; i < roll.size(); i++) {
				if (roll.get(i).facing.equals(result)) {
					roll.remove(i);
					return true;
				}
			}
		}
		return false;
	}
	/*
	 * attempts to remove a die of a certain color
	 * returns true if successful
	 */
	public boolean removeDie(DiceBase.Colors color) {
		if (canRemoveDie(color)) {
			for (int i = 0; i < roll.size(); i++) {
				if (roll.get(i).color.equals(color)) {
					roll.remove(i);
					return true;
				}
			}
		}
		return false;
	}
	/*
	 * attempts to remove a die of a certain color and result.
	 * returns true if successful;
	 */
	public boolean removeDie(DiceBase.Colors color, GameConstants.DiceResult result) {
		if (canRemoveDie(color, result)) {
			for (int i = 0; i < roll.size(); i++) {
				if (roll.get(i).color.equals(color) && roll.get(i).facing.equals(result)) {
					roll.remove(i);
					return true;
				}
			}
		}
		return false;
	}
	/*
	 * Attempts to add a die of a certain color and result
	 * returns true if successful
	 */
	public boolean addResult(DiceBase.Colors color, GameConstants.DiceResult result) {
		if (canAddDie(color, result)) {
			DiceBase toAdd = new DiceBase(color, result);
			if (toAdd.color != null) {
				roll.add(toAdd);
				return true;
			}
		}
		return false;
	}

	/*
	 * get the total damage in a roll. counts both hits and crits
	 */
	public int getDamageValue() {
		int returnThis = 0;
		if (roll.size() > 0) {
			for (int i = 0; i < roll.size(); i++) {
				returnThis += roll.get(i).getDamageValue();
			}
		}
		return returnThis;
	}

	/*
	 * get the total number of hit results in a roll
	 */
	public int getHitCount() {
		int returnThis = 0;
		if (roll.size() > 0) {
			for (int i = 0; i < roll.size(); i++) {
				returnThis += roll.get(i).getHitCount();
			}
		}
		return returnThis;
	}

	/*
	 * Get the total number of crit results in a roll
	 */
	public int getCritCount() {
		int returnThis = 0;
		if (roll.size() > 0) {
			for (int i = 0; i < roll.size(); i++) {
				returnThis += roll.get(i).getCritCount();
			}
		}
		return returnThis;
	}

	/*
	 * get the total number of accuracy results in a roll
	 */
	public int getAccuracyCount() {
		int returnThis = 0;
		if (roll.size() > 0) {
			for (int i = 0; i < roll.size(); i++) {
				returnThis += roll.get(i).getAccuracyCount();
			}
		}
		return returnThis;
	}

	/*
	 * Get the total number of blank results in a roll
	 */
	public int getBlankCount() {
		int returnThis = 0;
		if (roll.size() > 0) {
			for (int i = 0; i < roll.size(); i++) {
				returnThis += roll.get(i).getBlankCount();
			}
		}
		return returnThis;
	}

	/*
	 * get the total number of a specific specific die facing in a roll
	 */
	public int getResultCount(GameConstants.DiceResult r) {
		int returnThis = 0;
		if (roll.size() > 0) {
			for (int i = 0; i < roll.size(); i++) {
				if (roll.get(i).facing.equals(r)) {
					returnThis += 1;
				}
			}
		}
		return returnThis;
	}

	/*
	 * Determine if a die of a certain color can be removed, namely by existing
	 * in the first place
	 */
	public boolean canRemoveDie(DiceBase.Colors c) {
		if (roll.size() > 0) {
			for (int i = 0; i < roll.size(); i++) {
				if (roll.get(i).color.equals(c)) {
					return true;
				}
			}
		}
		return false;
	}

	/*
	 * Determine if dice of a certain color can be removed, namely by existing
	 * in the first place
	 */
	public boolean canRemoveDice(DiceBase.Colors c, int num) {
		if (roll.size() > 0) {
			int foundValid = 0;
			for (int i = 0; i < roll.size(); i++) {
				if (roll.get(i).color.equals(c)) {
					foundValid += 1;
					if (foundValid >= num) {
						return true;
					}
				}
			}
		}
		return false;
	}

	/*
	 * Determine if a die of a certain result can be removed, namely by existing
	 * in the first place
	 */
	public boolean canRemoveDie(DiceResult r) {
		if (roll.size() > 0) {
			for (int i = 0; i < roll.size(); i++) {
				if (roll.get(i).facing.equals(r)) {
					return true;
				}
			}
		}
		return false;
	}

	/*
	 * Determines if dice of a certain result can be removes, namely by existing
	 * in the first place
	 */
	public boolean canRemoveDice(DiceResult r, int num) {
		if (roll.size() > 0) {
			int foundValid = 0;
			for (int i = 0; i < roll.size(); i++) {
				if (roll.get(i).facing.equals(r)) {
					foundValid += 1;
					if (foundValid >= num) {
						return true;
					}
				}
			}
		}
		return false;
	}

	/*
	 * Determines if a die of a certain color and result can be removed, namely
	 * by existing in the first place
	 */
	public boolean canRemoveDie(DiceBase.Colors c, DiceResult r) {
		if (roll.size() > 0) {
			for (int i = 0; i < roll.size(); i++) {
				if (roll.get(i).facing.equals(r) && roll.get(i).color.equals(c)) {
					return true;
				}
			}
		}
		return false;
	}

	/*
	 * Determines if dice of a certain color and result can be removed, namely
	 * by existing in the first place
	 */
	public boolean canRemoveDice(DiceBase.Colors c, DiceResult r, int num) {
		if (roll.size() > 0) {
			int foundValid = 0;
			for (int i = 0; i < roll.size(); i++) {
				if (roll.get(i).facing.equals(r) && roll.get(i).color.equals(c)) {
					foundValid += 1;
					if (foundValid >= num) {
						return true;
					}
				}
			}
		}
		return false;
	}

	/*
	 * determines if a die of a certain color can be added, namely by the roll
	 * having more than 0 dice
	 */
	public boolean canAddDie(DiceBase.Colors c) {
		if (roll.size() > 0) {
			return true;
		}
		return false;
	}

	/*
	 * Determines if dice of a certain color can be added, namely by the roll
	 * having more than 0 dice
	 */
	public boolean canAddDice(DiceBase.Colors c, int num) {
		if (roll.size() > 0) {
			return true;
		}
		return false;
	}

	/*
	 * Determines if a die of a certain color and result cna be added, namely by
	 * the roll having more than 0 dice
	 */
	public boolean canAddDie(DiceBase.Colors c, DiceResult r) {
		if (roll.size() > 0) {
			return true;
		}
		return false;
	}

	/*
	 * Determines if dice of a certain color and result can be addded, namely by
	 * the roll having more than 0 dice
	 */
	public boolean canAddDice(DiceBase.Colors c, DiceResult r, int num) {
		if (roll.size() > 0) {
			return true;
		}
		return false;
	}
}
