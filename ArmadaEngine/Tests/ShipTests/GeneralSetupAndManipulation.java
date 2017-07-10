package ShipTests;

import static org.junit.Assert.*;

import org.junit.Test;

import gameCore.GameConstants;
import geometry.Coordinate;
import geometry.PolarCoord;
import geometry.Polygon;
import shipCore.HullZone;
import ships.NebulonB;

public class GeneralSetupAndManipulation {

	@Test
	public void testStatInputNebB() {
		NebulonB escort = new NebulonB(GameConstants.ShipVarients.NebulonB_Escort);
		NebulonB support = new NebulonB(GameConstants.ShipVarients.NebulonB_Support);
		
		assertEquals(3, escort.engineeringValue);
		assertEquals(3, support.engineeringValue);
		
		assertEquals(2, escort.squadronValue);
		assertEquals(1, support.squadronValue);
		
		assertEquals(2, escort.antiSquadBlueDice);
		assertEquals(1, support.antiSquadBlueDice);
		assertEquals(0, escort.antiSquadRedDice);
		assertEquals(0, support.antiSquadBlackDice);

	}
	
	@Test
	public void testHullZoneStatsNebB(){
		NebulonB escort = new NebulonB(GameConstants.ShipVarients.NebulonB_Escort);
		
		HullZone front = escort.hullZones[0];
		assertEquals(3, front.getCurrentShields());
		assertEquals(3, front.getMaxShields());
		assertEquals(3, front.getDicePool()[0]);
		assertEquals(0, front.getDicePool()[1]);
		assertEquals(0, front.getDicePool()[2]);
		assertNotNull(front.getGeometry());
		
		HullZone right = escort.hullZones[1];
		assertEquals(1, right.getCurrentShields());
		assertEquals(1, right.getMaxShields());
		assertEquals(1, right.getDicePool()[0]);
		assertEquals(1, right.getDicePool()[1]);
		assertEquals(0, right.getDicePool()[2]);
		assertNotNull(right.getGeometry());
		
		assertNotNull(escort.hullZones[2].getGeometry());
		assertNotNull(escort.hullZones[3].getGeometry());
		
	}
	
	@Test
	public void testBaseGeometry(){
		NebulonB escort = new NebulonB(GameConstants.ShipVarients.NebulonB_Escort);
		
		//verify plastic bounds (small base is 45 x 71 (includes plastic rails) 
		Polygon plasticBase = escort.plasticBase;
		PolarCoord[] polars = plasticBase.getCoordinates();
		
		//check each corner to make sure its in the right place
		Coordinate UR = polars[0].toCoordinate();
		assertEquals(UR.getXPos(), 22.5, 0.0001);
		assertEquals(UR.getYPos(), 35.5, 0.0001);
		
		Coordinate UL = polars[1].toCoordinate();
		assertEquals(UL.getXPos(), -22.5, 0.0001);
		assertEquals(UL.getYPos(), 35.5, 0.0001);
		
		Coordinate RR = polars[3].toCoordinate();
		assertEquals(RR.getXPos(), 22.5, 0.0001);
		assertEquals(RR.getYPos(), -35.5, 0.0001);
		
		Coordinate BL = polars[2].toCoordinate();
		assertEquals(BL.getXPos(), -22.5, 0.0001);
		assertEquals(BL.getYPos(), -35.5, 0.0001);
	}
	
	@Test
	public void testHullZoneGeometryCornorToCorner(){
		NebulonB escort = new NebulonB(GameConstants.ShipVarients.NebulonB_Escort);
		
		//look at front hullzone
		HullZone front = escort.hullZones[0];
		
		//in this configuration there is no front offset, so check that those points are the same
		Coordinate zero = front.getGeometry().getCoordinates()[0].toCoordinate();
		Coordinate one = front.getGeometry().getCoordinates()[1].toCoordinate();
		Coordinate two = front.getGeometry().getCoordinates()[2].toCoordinate();
		Coordinate three = front.getGeometry().getCoordinates()[3].toCoordinate();
		Coordinate four = front.getGeometry().getCoordinates()[4].toCoordinate();
		
		//make sure that the front left points are the same
		assertEquals(zero.getXPos(), -20.5, 0.0001);
		assertEquals(four.getXPos(), -20.5, 0.0001);
		assertEquals(zero.getYPos(), 35.5, 0.0001);
		assertEquals(four.getYPos(), 35.5, 0.0001);
		
		//make sure front right points are the same
		assertEquals(one.getXPos(), 20.5, 0.0001);
		assertEquals(two.getXPos(), 20.5, 0.0001);
		assertEquals(one.getYPos(), 35.5, 0.0001);
		assertEquals(two.getYPos(), 35.5, 0.0001);
		
		//make sure conjunction is at the origin (0.0)
		assertEquals(three.getXPos(), 0, 0.0001);
		assertEquals(three.getYPos(), 0, 0.0001);
		
		
		//look at rear hullzone
		HullZone rear = escort.hullZones[2];
		
		//in this configuration there is no front offset, so check that those points are the same
		Coordinate zeroA = rear.getGeometry().getCoordinates()[0].toCoordinate();
		Coordinate oneA = rear.getGeometry().getCoordinates()[1].toCoordinate();
		Coordinate twoA = rear.getGeometry().getCoordinates()[2].toCoordinate();
		Coordinate threeA = rear.getGeometry().getCoordinates()[3].toCoordinate();
		Coordinate fourA = rear.getGeometry().getCoordinates()[4].toCoordinate();
		
		//make sure that the rear right points are the same
		assertEquals(zeroA.getXPos(), 20.5, 0.0001);
		assertEquals(fourA.getXPos(), 20.5, 0.0001);
		assertEquals(zeroA.getYPos(), -35.5, 0.0001);
		assertEquals(fourA.getYPos(), -35.5, 0.0001);
		
		//make sure rear left points are the same
		assertEquals(oneA.getXPos(), -20.5, 0.0001);
		assertEquals(twoA.getXPos(), -20.5, 0.0001);
		assertEquals(oneA.getYPos(), -35.5, 0.0001);
		assertEquals(twoA.getYPos(), -35.5, 0.0001);
		
		//make sure conjunction is at the origin (0.0)
		assertEquals(threeA.getXPos(), 0, 0.0001);
		assertEquals(threeA.getYPos(), 0, 0.0001);
		
	}
	

}
