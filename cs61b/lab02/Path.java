/** A class that represents a path via pursuit curves.
 *  @author You!
 */
public class Path {
 private double x,y;
 Point currPoint = new Point();
 Point nextPoint = new Point();

public double getCurrX(){
 return currPoint.getX();
}
 public double getCurrY(){
return  currPoint.getY();
}
 public double getNextX(){
 return nextPoint.getX();
}
 public double getNextY(){
 return nextPoint.getY();
}
 public void iterate(double dx, double dy){
currPoint.setX(nextPoint.getX());
currPoint.setY(nextPoint.getY());
nextPoint.setX(nextPoint.getX()+dx);
nextPoint.setY(nextPoint.getY()+dy);
}
  public Path(double x,double y){
 nextPoint.setX(x);
 nextPoint.setY(y);
}
    /** What to do, what to do... */  

}
