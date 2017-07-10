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
	//Converts a polar coordinate into a regular one based on a specific non-zero origin
	public Coordinate toCoordinate(Coordinate other){
		double tempX =Math.cos(angle)*distance;
		System.out.println(tempX);
		double tempY= Math.sin(angle)*distance;
		System.out.println(tempY);
		return new Coordinate(tempX+other.getXPos(), tempY+other.getYPos());
	}
	
	public Coordinate toCoordinate(){
		return toCoordinate(new Coordinate(0,0));
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
