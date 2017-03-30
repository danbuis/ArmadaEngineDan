package geometry;

public class ComplexShape extends ShapeBase{
	protected ShapeBase[] childShapes = null;
	
	public ComplexShape(ShapeBase[] childShapes){
		super();
		this.childShapes = childShapes;
	}

	@Override
	public void rotate(double d) {
		for(int i = 0;i<childShapes.length;i++){
			PolarCoord tempPolar = childShapes[i].center.toPolarCoord(center);
			tempPolar.setAngle(tempPolar.getAngle()+d);
			childShapes[i].center=tempPolar.toCoordinate(center);
			childShapes[i].rotate(d);
		}
	}

	@Override
	public void translate(double diffX, double diffY) {
		center.xPos+=diffX;
		center.yPos+=diffY;
		for(int i = 0;i<childShapes.length;i++){
			childShapes[i].translate(diffX,diffY);
		}
	}

	@Override
	public boolean detectOverlap(Polygon s) {
		for(int i=0;i<childShapes.length;i++){
			if(childShapes[i].detectOverlap(s)){
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean detectOverlap(Circle other) {
		for(int i = 0;i<childShapes.length;i++){
			if(childShapes[i].detectOverlap(other)){
				return true;
			}
		}
			
		return false;
	}
	@Override
	public boolean detectOverlap(ComplexShape other){
		for(int i = 0;i<other.childShapes.length;i++){
			if(childShapes[i] instanceof Circle){
				if(other.detectOverlap((Circle) childShapes[i])){
					return true;
				}
			}
			if(childShapes[i] instanceof Polygon){
				if(other.detectOverlap((Polygon) childShapes[i])){
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public LineSegment findClosestPoints(Polygon other) {
		LineSegment returnThis = null;
		double closestDist = Double.MAX_VALUE;
		LineSegment tempSeg = null;
			for(int i =0;i<childShapes.length;i++){
				//grab closest points from individual shapes
				if(childShapes[i] instanceof Circle){
					tempSeg = ((Circle) childShapes[i]).findClosestPoints(other);
				}
				if(childShapes[i] instanceof Polygon){
					tempSeg = ((Polygon) childShapes[i]).findClosestPoints(other);
				}	
				//get distances of closest line segments and logic
				Double tempDist = tempSeg.getFirst().getDistance(tempSeg.getSecond());
				if(tempDist<closestDist){
					returnThis = tempSeg;
					closestDist = tempDist;
				}
			}
		return returnThis;
	}

	@Override
	public LineSegment findClosestPoints(Circle other) {
		LineSegment returnThis = null;
		double closestDist = Double.MAX_VALUE;
		LineSegment tempSeg = null;
			for(int i =0;i<childShapes.length;i++){
				//grab closest points from individual shapes
				if(childShapes[i] instanceof Circle){
					tempSeg = ((Circle) childShapes[i]).findClosestPoints(other);
				}
				if(childShapes[i] instanceof Polygon){
					tempSeg = ((Polygon) childShapes[i]).findClosestPoints(other);
				}	
				//get distances of closest line segments and logic
				Double tempDist = tempSeg.getFirst().getDistance(tempSeg.getSecond());
				if(tempDist<closestDist){
					returnThis = tempSeg;
					closestDist = tempDist;
				}
			}
		return returnThis;
	}

	@Override
	public LineSegment findClosestPoints(ComplexShape other) {
		LineSegment returnThis = null;
		double closestDist = Double.MAX_VALUE;
		LineSegment tempSeg = null;
			for(int i =0;i<childShapes.length;i++){
				//grab closest points from individual shapes
				if(childShapes[i] instanceof Circle){
					tempSeg = ((Circle) childShapes[i]).findClosestPoints(other);
				}
				if(childShapes[i] instanceof Polygon){
					tempSeg = ((Polygon) childShapes[i]).findClosestPoints(other);
				}	
				//get distances of closest line segments and logic
				Double tempDist = tempSeg.getFirst().getDistance(tempSeg.getSecond());
				if(tempDist<closestDist){
					returnThis = tempSeg;
					closestDist = tempDist;
				}
			}
		return returnThis;
	}

	public ShapeBase[] getChildShapes() {
		return childShapes;
	}

	public void setChildShapes(ShapeBase[] childShapes) {
		this.childShapes = childShapes;
	}
	
	
	
	

}
