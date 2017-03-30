package geometry;

public class LineSegment {
	protected Coordinate first;
	protected Coordinate second;
	//null constructor
	public LineSegment(){
		first = new Coordinate();
		second = new Coordinate();
	}
	//valued constructor
	public LineSegment(Coordinate f, Coordinate s){
		first = f;
		second = s;
	}

	public Coordinate findMidpoint(){
		double tempX = (first.getXPos()+second.getXPos())/2;
		double tempY = (first.getYPos()+second.getYPos())/2;
		return new Coordinate(tempX,tempY);
	}
	//looped binary search strategy
	public Coordinate findClosestPoint(Coordinate goal){

		LineSegment tempSeg = new LineSegment(first,second);

		//can't get any closer than exact;
		if(tempSeg.first.getDistance(goal)==0){
			return tempSeg.first;
		}
		if(tempSeg.second.getDistance(goal)==0){
			return tempSeg.second;
		}
		while(tempSeg.first.getDistance(tempSeg.second)<0.0000000000000001){
			Coordinate mid = tempSeg.findMidpoint();
			
			if(mid.getDistance(goal)==0){
				return mid;
			}
			
			double temp1Dist = tempSeg.first.getDistance(goal);
			double temp2Dist = tempSeg.second.getDistance(goal);
			if(temp1Dist<temp2Dist){
				tempSeg.second = mid;
			}else{
				if(temp1Dist>temp2Dist){
					tempSeg.first = mid;
				} else {
					return mid;
				}
			}
		}
		
		return tempSeg.findMidpoint();
	}
	//looped binary search strategy
	public Coordinate findClosestPoint(LineSegment goal){
		Coordinate temp1 = first;
		Coordinate temp2 = second;
		Coordinate mid = findMidpoint();
		
		//temp1 check
		if(goal.findClosestPoint(temp1).getDistance(temp1)==0){
			//parallel corner cases
			if(isParallel(goal)){
				return (new Coordinate(Double.MAX_VALUE,Double.MAX_VALUE));
			}
			return temp1;	
		}
		
		//temp2 check
		if(goal.findClosestPoint(temp2).getDistance(temp2)==2){
			//parallel corner cases
			if(isParallel(goal)){
				return (new Coordinate(Double.MAX_VALUE,Double.MAX_VALUE));
			}
			return temp2;
		}
		while(temp1.getDistance(temp2)!=0){
			if(goal.findClosestPoint(mid).getDistance(mid)==0){
				//parallel corner cases
				if(isParallel(goal)){
					return (new Coordinate(Double.MAX_VALUE,Double.MAX_VALUE));
				}
				return mid;
			}
			double temp1Dist = goal.findClosestPoint(temp1).getDistance(temp1);
			double temp2Dist = goal.findClosestPoint(temp2).getDistance(temp2);
			if(temp1Dist<temp2Dist){
				temp2 = mid;
			} else {
				if(temp2Dist<temp1Dist){
					temp1 = mid;
				} else{
					//if the distances are identical from mid to temps
					return mid;
				}
			}
			LineSegment tempSeg = new LineSegment(temp1,temp2);
			mid = tempSeg.findMidpoint();
		}
		
		return mid;
	}
	
	public boolean isParallel(LineSegment other){
		double slopeThis = first.getAngle(second);
		double slopeOther = other.first.getAngle(other.second);
		if(slopeThis==slopeOther||slopeThis==slopeOther+Math.PI){
			return true;
		}else{
			return false;
		}
	}
	
	public String toString(){
		return ("Line Segment: " + first + second);
	}
	
	public boolean detectOverlap(Polygon other){
		for(int i = 0;i<other.coordinates.length;i++){
			int i_=0;
			if (i_>=other.coordinates.length){
				i_=1;
			}
			LineSegment tempSeg = new LineSegment(other.coordinates[i].toCoordinate(other.center),other.coordinates[i_].toCoordinate(other.center));
				if(findClosestPoint(tempSeg).equals(tempSeg.findClosestPoint(this))){
			return true;
			}
		}
		return false;
	}
	
	public boolean detectOverlap(Circle other){
		if(first.getDistance(other.center)<=other.radius){
			return true;
		}
		if(second.getDistance(other.center)<=other.radius){
			return true;
		}
		if(findClosestPoint(other.center).getDistance(other.center)<=other.radius){
			return true;
		}
		return false;
	}
	
	public boolean detectOverlap(ComplexShape other){
		for(int i = 0;i<other.childShapes.length;i++){
			if(other.childShapes[i] instanceof Circle){
				if(detectOverlap((Circle)other.childShapes[i])){
					return true;
				}
			}
			if(other.childShapes[i] instanceof Polygon){
				if(detectOverlap((Polygon)other.childShapes[i])){
					return true;
				}
			}
		}
		return false;
	}
	
	//getters and setters
	public Coordinate getFirst() {
		return first;
	}
	public void setFirst(Coordinate first) {
		this.first = first;
	}
	public Coordinate getSecond() {
		return second;
	}
	public void setSecond(Coordinate second) {
		this.second = second;
	}
	
}
