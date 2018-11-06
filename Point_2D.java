package primitives;

public class Point_2D {

	protected Coordinate _x;
	protected Coordinate _y;

	// ***************** Constructors ********************** //
	/**
	 * construct 2D point with two coordinates
	 * 
	 * @param x
	 *            - coordinate
	 * @param y
	 *            - coordinate
	 */
	public Point_2D(Coordinate x, Coordinate y) {
		_x = x;
		_y = y;
	}

	/**
	 * construct 2D point with 2 numbers
	 * 
	 * @param x
	 *            - double
	 * @param y
	 *            - double
	 */
	public Point_2D(double x, double y) {
		_x = new Coordinate(x);
		_y = new Coordinate(y);
	}

	/**
	 * copy constructor
	 * 
	 * @param other
	 *            - Poimt_2D
	 */
	public Point_2D(Point_2D other) {
		_x = other._x;
		_y = other._y;
	}

	// ***************** Getters/Setters ********************** //

	public Coordinate x() {
		return _x;
	}

	public Coordinate y() {
		return _y;
	}

	// ***************** Operations ******************** //

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Point_2D))
			return false;
		Point_2D other = (Point_2D) obj;
		return _x.equals(other._x) && _y.equals(other._y);
	}

	@Override
	public String toString() {
		return "[x=" + _x + ", y=" + _y + "]";
	}
}
