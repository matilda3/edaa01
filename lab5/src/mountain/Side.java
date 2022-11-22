package mountain;

public class Side {
	private Point a;
	private Point b;

	public Side(Point a, Point b) {
		this.a = a;
		this.b = b;
	}
	
	@Override
	public int hashCode() {
		return a.hashCode() + b.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Side) {
			Side s = (Side) obj;
			return a.equals(s.getPointa()) || a.equals(s.getPointb()) || b.equals(s.getPointa()) || b.equals(s.getPointb());
		}else {
			return false;
		}
	}
	
	public Point getPointa() {
		return a;
	}
	public Point getPointb() {
		return b;
	}

}
