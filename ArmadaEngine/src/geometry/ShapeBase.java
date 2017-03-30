package geometry;

public abstract class ShapeBase {
	
	//The center of the shape is a relatively arbitrary point used to anchor hte other points of a shape
	protected Coordinate center = new Coordinate(0,0);
	
	public ShapeBase(){
		
	}
	
	//rotates the object an amount in radians, changes the angle of polar coordinates.
	public abstract void rotate(double d);
	//translates the shape by an amount, given as delta x and delta y; changes the center point of a shape;
	public abstract void translate(double diffX, double diffY);
	
	public Coordinate getCenter() {
		return center;
	}

	public void setCenter(Coordinate center) {
		this.center = center;
	}
	
	public abstract boolean detectOverlap(Polygon s);
	public abstract boolean detectOverlap(Circle other);
	public abstract boolean detectOverlap(ComplexShape other);
	
	public abstract LineSegment findClosestPoints(Polygon other);
	public abstract LineSegment findClosestPoints(Circle other);
	public abstract LineSegment findClosestPoints(ComplexShape other);
}
