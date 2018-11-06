package primitives;

public class Vector {

	protected Point_3D _head;
	
	public static final Vector X = new Vector(1,0,0);
	public static final Vector Y = new Vector(0,1,0);
	public static final Vector Z = new Vector(0,0,1);
	public static final Vector Xm = new Vector(-1,0,0);
	public static final Vector Ym = new Vector(0,-1,0);
	public static final Vector Zm = new Vector(0,0,-1);

	// ***************** Constructors ********************** //
	/**
	 * construct vector with point
	 * 
	 * @param point
	 *            - Point_3D
	 */
	public Vector(Point_3D point) {
		if (Point_3D.ZERO.equals(point))
			throw new IllegalArgumentException("zero vector is illegal");
		_head = point;
	}

	/**
	 * construct vector with 3 numbers
	 * 
	 * @param x
	 *            - double
	 * @param y
	 *            - double
	 * @param z
	 *            - double
	 */
	public Vector(double x, double y, double z) {
		Point_3D point = new Point_3D(new Coordinate(x), new Coordinate(y), new Coordinate(z));
		if (point._x.equals(Coordinate.ZERO) && point._y.equals(Coordinate.ZERO) && point._z.equals(Coordinate.ZERO))
			throw new IllegalArgumentException("zero vector is illegal");
		_head = point;
	}

	/**
	 * copy constructor
	 * 
	 * @param other
	 */
	public Vector(Vector other) {
		_head = other._head;
	}

	// ***************** Getters/Setters ********************** //

	public Point_3D head() {
		return _head;
	}

	// ***************** Operations ******************** //
	/**
	 * adds another vector
	 * 
	 * @param other
	 *            - vector
	 * @return vector - sum of 2 vectors
	 */
	public Vector add(Vector other) {
		return new Vector(new Point_3D(_head._x.add(other._head._x), _head._y.add(other._head._y),
				_head._z.add(other._head._z)));
	}

	/**
	 * subtracts another vector
	 * 
	 * @param other
	 *            - vector
	 * @return vector - subtract between 2 vectors
	 */
	public Vector subtract(Vector other) {
		return new Vector(new Point_3D(_head._x.subtract(other._head._x), _head._y.subtract(other._head._y),
				_head._z.subtract(other._head._z)));
	}

	/**
	 * scale vector with scalar
	 * 
	 * @param scalar
	 *            - number to scale
	 * @return vector scaled
	 */
	public Vector scale(double scalar) {
		return new Vector(new Point_3D(_head._x.scale(scalar), _head._y.scale(scalar), _head._z.scale(scalar)));
	}

	/**
	 * 
	 * @param other
	 *            - vector
	 * @return the cosine between 2 vectors
	 */
	public double dotProduct(Vector other) {
		return _head._x.multi(other._head._x).add(_head._y.multi(other._head._y))
				.add(_head._z.multi(other._head._z)).coordinate();
	}

	/**
	 * 
	 * @param other
	 *            - vector
	 * @return vector normal to 2 vectors
	 */
	public Vector crossProduct(Vector other) {
		return new Vector(new Point_3D(_head._y.multi(other._head._z).subtract(_head._z.multi(other._head._y)),
				_head._z.multi(other._head._x).subtract(_head._x.multi(other._head._z)),
				_head._x.multi(other._head._y).subtract(_head._y.multi(other._head._x)))).normalize();
	}

	/**
	 * 
	 * @return the length of the vector
	 */
	public double length() {
		return Math.sqrt(_head._x.multi(_head._x).add(_head._y.multi(_head._y).add(_head._z.multi(_head._z)))
				.coordinate());
	}

	/**
	 * Creates new vector with the same direction and length 1
	 * 
	 * @return the normalized vector
	 */
	public Vector normalize() {
		return new Vector(this).scale(1 / length());
	}

	/***
	 * find a normal vector
	 * @return vector normal to this vector
	 */
	public Vector findNormal() {
		if (!_head._x.equals(Coordinate.ZERO) || !_head._y.equals(Coordinate.ZERO))
			return new Vector(_head._y.scale(-1).coordinate(), _head._x.coordinate(), 0);
		else
			return new Vector(0, _head._z.scale(-1).coordinate(), _head._y.coordinate()); 
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Vector))
			return false;
		Vector other = (Vector) obj;
		return _head.equals(other._head);
	}

	@Override
	public String toString() {
		return "vector= " + _head.toString();
	}
}
