package shipCore;

import geometry.*;
//import gameCore.GameConstants;

public class ShipBase{
	

	protected Polygon basePlate = null;
	protected double facing = 0;
	
	public ShipBase(double shipLength, double shipWidth){
		Coordinate[] shipCoords = new Coordinate[4];
		shipCoords[0] = new Coordinate(shipLength/2,shipWidth/2);
		shipCoords[1] = new Coordinate(-shipLength/2,shipWidth/2);
		shipCoords[2] = new Coordinate(-shipLength/2,-shipWidth/2);
		shipCoords[3] = new Coordinate(shipLength/2,-shipWidth/2);
		basePlate = new Polygon(shipCoords);
	}
	
	public void translate (double diffX, double diffY){
		basePlate.translate(diffX,diffY);
	}
	
	public boolean checkShipOverlap(ShipBase other){
		return basePlate.detectOverlap(other.basePlate);
	}

	//getters and setters
	public void rotate(double r){
		facing+=r;
		basePlate.rotate(r);
	}
	public double getFacing() {
		return facing;
	}

	public void setFacing(double facing) {
		this.facing = facing;
	}
	

}
