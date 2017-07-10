package shipCore;
import geometry.*;
//import gameCore.GameConstants;
/*
 * A hull zone has several attributes that the its parent ship will look up.
 * 1) Its shield count
 * 2) Its dice pool
 * 3) Its geometry
 */

public class HullZone{
	//fields
	private int maxShields = 0;
	private int currentShields = 0;
	//dice are in the order of red, blue, black
	private int[] dicePool = {0,0,0};
	private Polygon geometry;
	
	public HullZone (Polygon geometry, int redDice, int blueDice, int blackDice, int shields){
		
		maxShields=shields;
		setCurrentShields(shields);
		
		dicePool[0]=redDice;
		dicePool[1]=blueDice;
		dicePool[2]=blackDice;
		
		this.geometry=geometry;
		
	}

	public int getCurrentShields() {
		return currentShields;
	}

	public void setCurrentShields(int currentShields) {
		this.currentShields = currentShields;
	}

	public int getMaxShields() {
		return maxShields;
	}

	public int[] getDicePool(){
		return dicePool;
	}

	public Polygon getGeometry() {
		return geometry;
	}


	
}

