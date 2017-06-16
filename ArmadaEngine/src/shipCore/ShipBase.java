package shipCore;

import gameCore.GameConstants;
import geometry.*;
//import gameCore.GameConstants;

public class ShipBase{
	

	private Polygon plasticBase = null;
	private double heading = 0;
	
	

	
	/**
	 * general constructor.  Uses base size and creates the plastic base for ship
	 */
	public ShipBase(BaseSize size){
		
		double baseLength=0;
		double baseWidth = 0;
		
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
	

}
