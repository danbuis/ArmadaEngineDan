package shipCore;

import gameCore.GameConstants;
import geometry.*;
//import gameCore.GameConstants;

public class ShipBase{
	

	private Polygon plasticBase = null;
	private double heading = 0;
	
	/**
	 * [0] front zone
	 * [1] right zone
	 * [2] rear zone
	 * [3] left zone
	 */
	private HullZone[] hullZones = new HullZone[4];
	
	private int frontRedDice;
	private int frontBlueDice;
	private int frontBlackDice;
	
	private int rightRedDice;
	private int rightBlueDice;
	private int rightBlackDice;
	
	private int backRedDice;
	private int backBlueDice;
	private int backBlackDice;
	
	private int leftRedDice;
	private int leftBlueDice;
	private int leftBlackDice;
	
	private int frontShieldsMax;
	private int rightShieldsMax;
	private int rearShieldsMax;
	private int leftShieldsMax;
	
	private int commandValue;
	private int squadronValue;
	private int engineeringValue;
	
	private double baseLength=0;
	private double baseWidth=0;
	
	
	

	
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
		shipCoords[0] = new Coordinate(baseLength/2,baseWidth/2 + GameConstants.shipBaseRails);
		shipCoords[1] = new Coordinate(-baseLength/2,baseWidth/2 + GameConstants.shipBaseRails);
		shipCoords[2] = new Coordinate(-baseLength/2,-baseWidth/2 - GameConstants.shipBaseRails);
		shipCoords[3] = new Coordinate(baseLength/2,-baseWidth/2 - GameConstants.shipBaseRails);
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
	 * @param frontConjunction - how far along the central axis for the lines to meet
	 * @param rearConjuntion - how far along the central axis for the lines to meet
	 */
	public Polygon[] generateHullZonesPolygons( double frontOffset, double rearOffset, double frontConjunction, double rearConjunction){
		Polygon[] returnPolys = new Polygon[4];
		
		//generate front polygon
		Coordinate[] coordsFront = new Coordinate[5];
		coordsFront[0] = new Coordinate(baseLength/2,-baseWidth/2); //front left
		coordsFront[1] = new Coordinate(baseLength/2,baseWidth/2); //front right
		coordsFront[2] = new Coordinate(baseLength/2-frontOffset, baseWidth/2); //right middle front
		coordsFront[3] = new Coordinate(baseLength/2-frontConjunction, 0); // front conjunction
		coordsFront[4] = new Coordinate(baseLength/2-frontOffset, -baseWidth/2); //left middle front
		returnPolys[0] = new Polygon(coordsFront); //generate new polygon
		
		//generate right polygon
		Coordinate []coordsRight = new Coordinate[4];
		coordsRight[0] = coordsFront[2]; //right middle front
		coordsRight[1] = new Coordinate(-baseLength/2+rearOffset, baseWidth/2); //right middle rear
		coordsRight[2] = new Coordinate(-baseLength/2+rearConjunction, 0); //rear conjunction
		coordsRight[3] = coordsFront[3]; //front conjunction
		returnPolys[1] = new Polygon(coordsRight); //generate new polygon
		
		//generate rear polygon
		Coordinate[]coordsRear = new Coordinate[5];
		coordsRear[0] = new Coordinate(-baseLength/2, baseWidth/2); //rear right
		coordsRear[1] = new Coordinate(-baseLength/2, -baseWidth/2); //rear left
		coordsRear[2] = new Coordinate(-baseLength/2+rearOffset, -baseWidth/2); //left middle rear
		coordsRear[3] = coordsRight[2]; //rear conjunction
		coordsRear[4] = coordsRight[1]; //right middle rear
		returnPolys[2] = new Polygon(coordsRear); //generate new polygon
		
		//generate left polygon
		Coordinate[]coordsLeft = new Coordinate[4];
		coordsLeft[0] = coordsRear[2]; //left middle rear
		coordsLeft[1] = coordsFront[4];//left middle front
		coordsLeft[3] = coordsFront[3];//front conjunction
		coordsLeft[4] = coordsRight[2];//rear conjunction
		returnPolys[3] = new Polygon(coordsLeft);
		
		
		return returnPolys;
	}

}
