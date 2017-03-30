package geometry;

public class Polygon extends ShapeBase{
	/*
	 * A shape is defined as a series of points. however, these shapes will translate and rotate, so it is necessary to store
	 * the points in a way that is mobile. Therefore we will use polar coordinates to store the shape as it is moved and rotated. 
	 */
	

	//polar coordinates of a shape, relative to center,
	protected PolarCoord[] coordinates = null;
	
	//null constructor
	public Polygon(){
		System.out.println("null shape. please fix");
	}
	//valued constructor
	public Polygon(Coordinate[] coords){
		super();
		coordinates = new PolarCoord[coords.length];
		for(int i = 0;i<coords.length;i++){
			coordinates[i]=coords[i].toPolarCoord(center);
		}
	}
	
	//rotates the object an amount in radians, changes the angle of polar coordinates.
	public void rotate(double d){
		for (PolarCoord i: coordinates){
			i.setAngle(i.getAngle()+d);
		}
	}
	
	//translates the shape by an amount, given as delta x and delta y; changes the center point of a shape;
	public void translate(double diffX, double diffY){
		center.setXPos(center.getXPos()+diffX);
		center.setYPos(center.getYPos()+diffY);
	}
	
	//find the minimum distance between center and all of the coordinates in a shape
	public double findMinDistance(){
		double min = Double.MAX_VALUE;
		for(PolarCoord i:coordinates)
			if(i.getDistance()<min){
				min = i.getDistance();
			}
		return min;
	}
	
	//find the maximum distance between the center and all of the coordinates in a shape;
	public double findMaxDistance(){
		double max = Double.MIN_VALUE;
		for(PolarCoord i:coordinates){
			if(i.getDistance()>max){
				max = i.getDistance();
			}
		}
		return max;
	}
	
	public boolean detectOverlap(Polygon s) {
		// if too far away, not overlapping;
		double midToMid = center.getDistance(s.center);
		if (findMaxDistance() + s.findMaxDistance() < midToMid) {
			return false;
		} else {
			// if too close, return true
			if (midToMid <= findMaxDistance()
					|| midToMid <= s.findMaxDistance()) {
				return true;
			} else {
				// buckle in, we're iterating through all of the line segments
				// now.
				for (int i = 0; i < coordinates.length; i++) {
						int i_ = i + 1;
						if (i_ >= coordinates.length) {
							i_ = 0;
						}
						LineSegment seg1 = new LineSegment(
								coordinates[i].toCoordinate(center),
								coordinates[i_].toCoordinate(center));
						if(seg1.detectOverlap(s)){
							return true;
						}
					}
				}
			}

		return false;
	}
	@Override
	public boolean detectOverlap(Circle other) {
		for(int i = 0;i<coordinates.length;i++){
			// Point is too close to center
			if(coordinates[i].toCoordinate(center).getDistance(other.center)<=other.radius){
				return true;
			}
		}
		/*
		 * Iterate through points to see if line segments get too close
		 */
		for(int i =0;i<coordinates.length;i++){
			int i_= i+1;
			if(i_>=coordinates.length){
				i_=0;
			}
			LineSegment tempSeg = new LineSegment(coordinates[i].toCoordinate(center),coordinates[i_].toCoordinate(center));
			if(tempSeg.detectOverlap(other)){
				return true;
			}
		}
		return false;
	}
	
	public boolean detectOverlap(ComplexShape other){
		return other.detectOverlap(this);
	}
	
	@Override
	public LineSegment findClosestPoints(Polygon other) {
		LineSegment returnThis = new LineSegment(null,null);
		Double closestDistance = Double.MAX_VALUE;
		for(int i = 0;i<coordinates.length;i++){
			for(int j = 0;j<other.coordinates.length;j++){
				int i_=i+1;
				if(i_>=coordinates.length){
					i_=0;
				}
				int j_=j+1;
				if(j_>=other.coordinates.length){
					j_=0;
				}
				Coordinate temp1 = coordinates[i].toCoordinate(center);
				Coordinate temp2 = other.coordinates[j].toCoordinate(other.center);
				Coordinate temp1_ = coordinates[i_].toCoordinate(center);
				Coordinate temp2_ = other.coordinates[j_].toCoordinate(other.center);
				
				LineSegment tempSeg1 = new LineSegment(temp1,temp1_);
				LineSegment tempSeg2 = new LineSegment(temp2,temp2_);

				LineSegment tempSeg3 = new LineSegment(tempSeg1.findClosestPoint(tempSeg2),tempSeg2.findClosestPoint(tempSeg1));
				double tempDist = tempSeg3.first.getDistance(tempSeg3.second);
				if(tempDist<closestDistance){
					closestDistance = tempDist;
					returnThis = tempSeg3;
				}
			}
		}
		return returnThis;
	}

	
	//getters and setters
	public PolarCoord[] getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(PolarCoord[] coordinates) {
		this.coordinates = coordinates;
	}
	@Override
	public LineSegment findClosestPoints(Circle other) {
		//#lazy programming
		return other.findClosestPoints(this);
	}
	@Override
	public LineSegment findClosestPoints(ComplexShape other) {
		// #lazy programming
		return other.findClosestPoints(this);
	}



	

}
