package Attacks;

import shipCore.HullZone;

public class Attack {
	
	HullZone attackingZone;
	HullZone defendingZone;
	double range;
	
	public Attack(){
		
	}
	
	//regular constructor
	public Attack(HullZone attackingZone, HullZone defendingZone, double range){
		this.attackingZone = attackingZone;
		this.defendingZone = defendingZone;
		this.range = range;
	}

}
