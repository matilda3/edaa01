package mountain;

import java.util.HashMap;
import java.util.Map;

import fractal.Fractal;
import fractal.TurtleGraphics;

public class RandMountain extends Fractal {
	private Point a;
	private Point b;
	private Point c;
	private double dev;
	private Map<Side, Point> map;

	public RandMountain(Point a, Point b, Point c, double dev) {
		super();
		this.a = a;
		this.b = b;
		this.c = c;
		this.dev = dev;
		map = new HashMap<Side, Point>();
	}
	
	@Override
	public String getTitle() {
		return "Random Mountain";
	}

	@Override
	public void draw(TurtleGraphics g) {
		fractalMountain(g, order, a, b, c, dev);
	}
	
	public void drawTriangle(TurtleGraphics turtle, Point a, Point b, Point c) {
		turtle.moveTo(a.getX(), a.getY());//move to a
		turtle.forwardTo(b.getX(), b.getY());
		turtle.forwardTo(c.getX(), c.getY());
		turtle.forwardTo(a.getX(), a.getY());
	}
	
	private void fractalMountain(TurtleGraphics turtle, int order, Point a, Point b, Point c, double dev) {
		Point x;
		Point y;
		Point z;
		if(order == 0) {
			drawTriangle(turtle, a, b, c);
		}else {
			Side ab = new Side(a, b);
			Side bc = new Side(b, c);
			Side ca = new Side(c, a);
			if(map.containsKey(ab)) {
				x = map.get(ab);
				map.remove(ab);
			}else {
				x = getMidPoint(a, b);
				map.put(ab, x);
			}
			if(map.containsKey(bc)) {
				y = map.get(bc);
				map.remove(bc);
			}else {
				y = getMidPoint(b, c);
				map.put(bc,  y);
			}
			if(map.containsKey(ca)) {
				z = map.get(ca);
				map.remove(ca);
			}else {
				z = getMidPoint(c, a);
				map.put(ca, z);
			}
			fractalMountain(turtle, order - 1, a, x, z, dev / 2);//a corner triangle
			fractalMountain(turtle, order - 1, x, b, y, dev / 2);//b corner triangle
			fractalMountain(turtle, order - 1, z, y, c, dev / 2);//c corner triangle
			fractalMountain(turtle, order - 1, x, y, z, dev / 2);//mid triangle
		}
	}
	
	private Point getMidPoint(Point a, Point b) {
		return new Point((b.getX() + a.getX()) / 2, ((b.getY() + a.getY()) / 2) + (int) RandomUtilities.randFunc(dev));
	}

}
