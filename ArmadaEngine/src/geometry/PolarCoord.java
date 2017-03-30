package geometry;

public class PolarCoord {
	private double angle;
	private double distance;
	
	//null constructor
	public PolarCoord(){
		angle = 0;
		distance =0;
	}
	//valued constructor
	public PolarCoord(double a, double d){
		angle = a;
		distance = d;
	}
	//Converts a polar coordinate into a regular one
	public Coordinate toCoordinate(Coordinate other){
		double tempX =Math.cos(angle)*distance;
		double tempY= Math.sin(angle)*distance;
		return new Coordinate(tempX+other.getXPos(), tempY+other.getYPos());
	}
	
	
	//toString()
	public String toString(){
		return ("Polar Coordinate: Angle: " + angle + " Distance: " + distance);
	}
	
	
	
	//getters amd setters
	public double getAngle() {
		return angle;
	}

	public void setAngle(double angle) {
		this.angle = angle;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}
	
	
}
