package shipCore;

import gameCore.GameConstants;
import geometry.*;
//import gameCore.GameConstants;

public class ShipBase{
	

	public Polygon plasticBase = null;
	private double heading = 0;
	
	/**
	 * [0] front zone
	 * [1] right zone
	 * [2] rear zone
	 * [3] left zone
	 */
	public HullZone[] hullZones = new HullZone[4];
	
	protected int frontRedDice;
	protected int frontBlueDice;
	protected int frontBlackDice;
	
	protected int rightRedDice;
	protected int rightBlueDice;
	protected int rightBlackDice;
	
	protected int backRedDice;
	protected int backBlueDice;
	protected int backBlackDice;
	
	protected int leftRedDice;
	protected int leftBlueDice;
	protected int leftBlackDice;
	
	public int antiSquadRedDice;
	public int antiSquadBlueDice;
	public int antiSquadBlackDice;
	
	protected int frontShieldsMax;
	protected int rightShieldsMax;
	protected int backShieldsMax;
	protected int leftShieldsMax;
	
	public int commandValue;
	public int squadronValue;
	public int engineeringValue;
	
	protected double baseLength=0;
	protected double baseWidth=0;
	
	
	

	
	/**
	 * general constructor.  Uses base size and creates the plastic base for ship
	 */
	public ShipBase(BaseSize size){

		
		if(size == BaseSize.SMALL){
			baseLength = GameConstants.shipSmallLength;
			baseWidth = GameConstants.shipSmallWidth;
		}else if(size == BaseSize.MEDIUM){
			baseLength = GameConstants.shipMediumLength;
			baseWidth = GameConstants.shipMediumWidth;
		} else if (size == BaseSize.LARGE){
			baseLength = GameConstants.shipLargeLength;
			baseWidth = GameConstants.shipLargeWidth;
		}
		
		Coordinate[] shipCoords = new Coordinate[4];
		shipCoords[0] = new Coordinate(baseWidth/2 + GameConstants.shipBaseRails , baseLength/2);
		shipCoords[1] = new Coordinate(-baseWidth/2 - GameConstants.shipBaseRails,baseLength/2);
		shipCoords[2] = new Coordinate(-baseWidth/2 - GameConstants.shipBaseRails,-baseLength/2);
		shipCoords[3] = new Coordinate(baseWidth/2 + GameConstants.shipBaseRails,-baseLength/2);
		plasticBase = new Polygon(shipCoords);
	}
	
	
	public void translate (double diffX, double diffY){
		plasticBase.translate(diffX,diffY);
	}
	
	public boolean checkShipOverlap(ShipBase other){
		return plasticBase.detectOverlap(other.plasticBase);
	}

	//getters and setters
	public void rotate(double r){
		heading+=r;
		plasticBase.rotate(r);
		for(HullZone hullZone : hullZones){
			hullZone.getGeometry().rotate(r);
		}
	}
	public double getFacing() {
		return heading;
	}

	public void setFacing(double heading) {
		this.heading = heading;
	}
	
	public void incrementHeading (double increment){
		this.heading += increment;
	}
	
	/**Geometry for hull zones is generated clockwise, with the first 2 points defining the line for the 
	 * LOS pip
	 * 
	 * [0] front zone
	 * [1] right zone
	 * [2] rear zone
	 * [3] left zone
	 * 
	 * @param frontOffset - how far back along the side edge for the firing arc line
	 * @param rearOffset - how far forward along the side edge for the firing arc line
	 * @param frontConjunction - how far from edge along the central axis for the lines to meet
	 * @param rearConjuntion - how far from edge along the central axis for the lines to meet
	 */
	public Polygon[] generateHullZonesPolygons( double frontOffset, double rearOffset, double frontConjunction, double rearConjunction){
	
		
		//generate front polygon
		Coordinate[] coordsFront = new Coordinate[5];
		coordsFront[0] = new Coordinate(-baseWidth/2,baseLength/2); //front left
		coordsFront[1] = new Coordinate(baseWidth/2,baseLength/2); //front right
		coordsFront[2] = new Coordinate(baseWidth/2, baseLength/2-frontOffset); //right middle front
		coordsFront[3] = new Coordinate(0, baseLength/2-frontConjunction); // front conjunction
		coordsFront[4] = new Coordinate(-baseWidth/2, baseLength/2-frontOffset); //left middle front
		
		//generate right polygon
		Coordinate []coordsRight = new Coordinate[4];
		coordsRight[0] = coordsFront[2]; //right middle front
		coordsRight[1] = new Coordinate(baseWidth/2, -baseLength/2+rearOffset); //right middle rear
		coordsRight[2] = new Coordinate(0, -baseLength/2+rearConjunction); //rear conjunction
		coordsRight[3] = coordsFront[3]; //front conjunction
		
		//generate rear polygon
		Coordinate[]coordsRear = new Coordinate[5];
		coordsRear[0] = new Coordinate(baseWidth/2, -baseLength/2); //rear right
		coordsRear[1] = new Coordinate(-baseWidth/2, -baseLength/2); //rear left
		coordsRear[2] = new Coordinate(-baseWidth/2, -baseLength/2+rearOffset); //left middle rear
		coordsRear[3] = coordsRight[2]; //rear conjunction
		coordsRear[4] = coordsRight[1]; //right middle rear

		
		//generate left polygon
		Coordinate[]coordsLeft = new Coordinate[4];
		coordsLeft[0] = coordsRear[2]; //left middle rear
		coordsLeft[1] = coordsFront[4];//left middle front
		coordsLeft[2] = coordsFront[3];//front conjunction
		coordsLeft[3] = coordsRight[2];//rear conjunction

		
		Polygon[] returnPolys = {new Polygon(coordsFront),new Polygon(coordsRight),new Polygon(coordsRear),new Polygon(coordsLeft)};
		
		return returnPolys;
	}

}
