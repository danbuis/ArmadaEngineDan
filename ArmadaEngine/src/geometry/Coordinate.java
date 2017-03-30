package geometry;

/*
 * A Coordinate represents a position in 2 Dimensional Space
 * marked by positions on the X and Y coordinates
 * The X axis represents a horizontal distance
 * The Y Axis represents a vertical distance
 */

public class Coordinate {
	protected double xPos;
	protected double yPos;

	//null constructor
	public Coordinate(){
		xPos = 0;
		yPos=  0;
	}
	//valued constructor
	public Coordinate(double x, double y){
		xPos = x;
		yPos = y;
	}
	
	public Coordinate(Coordinate other) {
		this.xPos = other.xPos;
		this.yPos = other.yPos;
		// TODO Auto-generated constructor stub
	}
	//returns the difference in X position of this coordinate to another.
	//If the other coordinate is larger, returns a postive value
	public double getDiffX(Coordinate other){
		return(other.getXPos()-xPos);
	}
	//returns the difference in Y position of this coordinate to another. 
	//If the other coordinate is larger, returns a postive value
	public double getDiffY(Coordinate other){
		return (other.getYPos()-yPos);
	}
	
	//returns the distance between 2 points, because pythagorean theorem.
	public double getDistance(Coordinate other){
		double temp = Math.pow(getDiffX(other),2)+Math.pow(getDiffY(other),2);
		temp = Math.sqrt(temp);
		return temp;
	}
	
	//returns the angle in radians of from this point to another point. Will return between 0 and 2PI
	public double getAngle(Coordinate other){
		double temp = getDiffY(other)/getDiffX(other);
		temp = Math.atan(temp);
		//adjusters based on coordinates, since aTan tradition returns between -pi/2 and +pi/2. 
		//Adding pi when X<0 will clear the overlap, and adding 2Pi to when x>0 but y<0 will push the rest above 0;
		if(getDiffX(other)<0){
			temp += Math.PI;
		} else{
			if(getDiffY(other)<0){
				temp+=2*Math.PI;
			}
		}
			return temp;
	}
	//turns a regular coordinate into a polar coordinate relative to another regular coordinate
	public PolarCoord toPolarCoord(Coordinate other){
		double temp1 = other.getDistance(this);
		double temp2 = other.getAngle(this);
		return new PolarCoord(temp2,temp1);
	}
	
	//toString method
	public String toString(){
		return ("Coordinate (" + xPos + ", " + yPos + ")");
	}
	
	//equals method
	public boolean equals(Coordinate other) {
		if (xPos != other.xPos) {
			return false;
		} else {
			if (yPos != other.yPos) {
				return false;
			} else {
				return true;
			}
		}
	}
	

	//getters and setters
	public double getXPos() {
		return xPos;
	}

	public void setXPos(double xPos) {
		this.xPos = xPos;
	}

	public double getYPos() {
		return yPos;
	}

	public void setYPos(double yPos) {
		this.yPos = yPos;
	}
	//also getters and setters
	public double getxPos() {
		return xPos;
	}
	public void setxPos(double xPos) {
		this.xPos = xPos;
	}
	public double getyPos() {
		return yPos;
	}
	public void setyPos(double yPos) {
		this.yPos = yPos;
	}

	
	
	
}
