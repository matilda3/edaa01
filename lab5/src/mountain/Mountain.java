package mountain;

import fractal.Fractal;
import fractal.TurtleGraphics;

public class Mountain extends Fractal{
	private Point a;
	private Point b;
	private Point c;
	
	public Mountain(Point a, Point b, Point c) {
		super();
		this.a = a;
		this.b = b;
		this.c = c;
	}

	@Override
	public String getTitle() {
		return "Mountain";
	}

	@Override
	public void draw(TurtleGraphics g) {
		fractalMountain(g, order, a, b, c);
	}
	
	public void drawTriangle(TurtleGraphics turtle, Point a, Point b, Point c) {
		turtle.moveTo(a.getX(), a.getY());//move to a
		turtle.forwardTo(b.getX(), b.getY());
		turtle.forwardTo(c.getX(), c.getY());
		turtle.forwardTo(a.getX(), a.getY());
	}
	
	private void fractalMountain(TurtleGraphics turtle, int order, Point a, Point b, Point c) {
		if(order == 0) {
			drawTriangle(turtle, a, b, c);
		}else {
			Point x = getMidPoint(a, b);
			Point y = getMidPoint(b, c);
			Point z = getMidPoint(c, a);
			fractalMountain(turtle, order - 1, a, x, z);//a corner triangle
			fractalMountain(turtle, order - 1, x, b, y);//b corner triangle
			fractalMountain(turtle, order - 1, z, y, c);//c corner triangle
			fractalMountain(turtle, order - 1, x, y, z);//mid triangle
		}
	}
	
	private Point getMidPoint(Point a, Point b) {
		return new Point((b.getX() + a.getX()) / 2, (b.getY() + a.getY()) / 2);
	}

}
