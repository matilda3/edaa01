package fractal;

import koch.Koch;
import mountain.Mountain;
import mountain.Point;
import mountain.RandMountain;

public class FractalApplication {
	public static void main(String[] args) {
		Fractal[] fractals = new Fractal[3];
		fractals[0] = new Koch(300);
		fractals[1] = new Mountain(new Point(350, 350), new Point(200, 200), new Point(75, 300));
		fractals[2] = new RandMountain(new Point(350, 350), new Point(200, 200), new Point(75, 300), 5.0);
	    new FractalView(fractals, "Fraktaler", 600, 600);
	}

}
