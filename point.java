import java.util.ArrayList;
import java.util.List;

public class point {
	private double xPoint;
	private double yPoint;
	private double radius;
	ArrayList<point> adj = new ArrayList<point>();
	private int dist;

	public point() {

	}

	public point(double xPoint, double yPoint, double radius) {
		super();
		this.xPoint = xPoint;
		this.yPoint = yPoint;
		this.radius = radius;
		this.dist = -1;
	}

	/**
	 * @return the xPoint
	 */
	public double getxPoint() {
		return xPoint;
	}

	/**
	 * @return the yPoint
	 */
	public double getyPoint() {
		return yPoint;
	}

	/**
	 * @return the radius
	 */
	public double getRadius() {
		return radius;
	}

	/**
	 * @param xPoint the xPoint to set
	 */
	public void setxPoint(double xPoint) {
		this.xPoint = xPoint;
	}

	/**
	 * @param yPoint the yPoint to set
	 */
	public void setyPoint(double yPoint) {
		this.yPoint = yPoint;
	}

	/**
	 * @param radius the radius to set
	 */
	public void setRadius(double radius) {
		this.radius = radius;
	}

	/**
	 * @return the adj
	 */
	public ArrayList<point> getAdj() {
		return adj;
	}

	/**
	 * adds the x to adj
	 */
	public void addAdj(point x) {
		adj.add(x);
	}

	/**
	 * @return the dist
	 */
	public int getDist() {
		return dist;
	}

	/**
	 * @param dist the dist to set
	 */
	public void setDist(int dist) {
		this.dist = dist;
	}

}
