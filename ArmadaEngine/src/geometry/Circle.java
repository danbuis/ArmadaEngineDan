package geometry;

public class Circle extends ShapeBase{
	protected double radius = 0;
	public Circle(double radius){
		super();
		this.radius = radius;
		
	}
	@Override
	//do nothing. circles don't rotate;
	public void rotate(double d) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void translate(double diffX, double diffY) {
		center.xPos += diffX;
		center.yPos += diffY;
		
	}

	@Override
	public boolean detectOverlap(Polygon s) {
		//find the closest polygon vertex, and see if it is too close
		double closestDistance = Double.MAX_VALUE;
		for(int i=0;i<s.coordinates.length;i++){
			double tempDist = center.getDistance(s.coordinates[i].toCoordinate(s.center));
			if(tempDist<closestDistance){
				closestDistance=tempDist;
			}
		
		}
		if(closestDistance<radius){
			return true;
			//check the line segments, iteratively, to see if their closest points are too close
		} else {
			for(int i = 0;i<s.coordinates.length;i++){
				int i_ = i+1;
				if(i_>=s.coordinates.length){
					i_=0;
				}
				LineSegment seg = new LineSegment(s.coordinates[i].toCoordinate(s.center),s.coordinates[i_].toCoordinate(s.center));
				if(seg.findClosestPoint(center).getDistance(center) <= radius){
					return true;
				}
			}
		}
		//fallback
		return false;
	}
	@Override
	public boolean detectOverlap(Circle other) {
		if(radius+other.radius<=center.getDistance(other.center)){
			return true;
		} else{
			return false;
		}
	}
	
	public boolean detectOverlap(ComplexShape other){
		return other.detectOverlap(this);
	}
	@Override
	public LineSegment findClosestPoints(Polygon other) {
		Coordinate closestFromPolygon = null;
		double closestDistance = Double.MAX_VALUE;
		for(int i = 0; i<other.coordinates.length;i++){
			int i_=i+1;
			if(i_>=other.coordinates.length){
				i_=0;
			}
			Coordinate temp1 = other.coordinates[i].toCoordinate(other.center);
			Coordinate temp2 = other.coordinates[i_].toCoordinate(other.center);
			
			//Coordinate cases
			double tempDist = temp1.getDistance(center);
			if(tempDist < closestDistance){
				closestDistance = tempDist;
				closestFromPolygon = temp1;
			}
			//Line segment cases
			Coordinate closestOnLine = new LineSegment(temp1,temp2).findClosestPoint(center);
			tempDist = closestOnLine.getDistance(center);
			if(tempDist < closestDistance){
				closestDistance = tempDist;
				closestFromPolygon = closestOnLine;
			}
			
		}
		PolarCoord closestFromCircle = new PolarCoord(center.getAngle(closestFromPolygon),radius);
		return new LineSegment(closestFromCircle.toCoordinate(center),closestFromPolygon);
	}
	@Override
	public LineSegment findClosestPoints(Circle other) {
		double angle = center.getAngle(other.center);
		PolarCoord temp1 = new PolarCoord(angle,radius);
		PolarCoord temp2 = new PolarCoord(angle+Math.PI,other.radius);
		return new LineSegment(temp1.toCoordinate(center),temp2.toCoordinate(other.center));
	}
	@Override
	//#lazy programming
	public LineSegment findClosestPoints(ComplexShape other) {
		return other.findClosestPoints(this);
	}
	public double getRadius() {
		return radius;
	}
	public void setRadius(double radius) {
		this.radius = radius;
	}
	
	//getters setters
	

}
