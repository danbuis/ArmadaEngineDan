package ships;

import gameCore.GameConstants;
import geometry.Polygon;
import shipCore.*;

public class NebulonB extends ShipBase{

	public NebulonB(GameConstants.ShipVarients varient){
		
		//creates base
		super(BaseSize.SMALL);
		
		//assign stats
		frontRedDice = 3;
		frontBlueDice = 0;
		frontBlackDice = 0;
		
		rightRedDice = 1;
		rightBlueDice = 1;
		rightBlackDice = 0;
		
		backRedDice = 2;
		backBlueDice = 0;
		backBlackDice = 0;
		
		leftRedDice = 1;
		leftBlueDice = 1;
		leftBlackDice = 0;
		
		antiSquadRedDice = 0;
		if (varient==GameConstants.ShipVarients.NebulonB_Escort){
			antiSquadBlueDice=2;
		} else antiSquadBlueDice=1;
		antiSquadBlackDice = 0;
		
		frontShieldsMax = 3;
		rightShieldsMax = 1;
		backShieldsMax = 2;
		leftShieldsMax = 1;
		
		commandValue = 2;
		if (varient==GameConstants.ShipVarients.NebulonB_Escort){
			squadronValue=2;
		} else squadronValue=1;
		engineeringValue = 3;
		
		//generateHullZones
		//generate geometry
		Polygon[] hullZoneGeometry = generateHullZonesPolygons(0, 0, baseLength/2, baseLength/2);
		
		hullZones[0]=new HullZone(hullZoneGeometry[0], frontRedDice, frontBlueDice, frontBlackDice, frontShieldsMax);
		hullZones[1]=new HullZone(hullZoneGeometry[1], rightRedDice, rightBlueDice, rightBlackDice, rightShieldsMax);
		hullZones[2]=new HullZone(hullZoneGeometry[2], backRedDice, backBlueDice, backBlackDice, backShieldsMax);
		hullZones[3]=new HullZone(hullZoneGeometry[3], leftRedDice, leftBlueDice, leftBlackDice, leftShieldsMax);
		
	}
	

}
