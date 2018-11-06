package primitives;


public class Ray extends Vector {

	private Point_3D _p0;

	// ***************** Constructors ********************** //
	/**
	 * construct ray with vector and 3D point
	 * 
	 * @param vector
	 *            - vector
	 * @param point
	 *            - point 3D
	 */
	public Ray(Vector vector, Point_3D point) {
		super(vector.normalize());
		_p0 = point;
	}

	// ***************** Getters/Setters ********************** //

	public Point_3D p0() {
		return _p0;
	}

	public Vector direction() {
		return this;
	}

	/***
	 * finds a ray sends from the same point in a different angle
	 * @param normal vector normal to the direction vector
	 * @param scalar 
	 * @return a ray sends from the same point in a different angle
	 */
	public Ray nearbyRay(Vector normal1, Vector normal2, double scalar1, double scalar2) {
		Ray result = new Ray(this, _p0);
		normal1 = normal1.normalize();
		normal1 = normal1.scale(scalar1);
		normal2 = normal2.normalize();
		normal2 = normal2.scale(scalar2);
		result.add(normal1);
		result.add(normal2);
		return result;
	}
	// ***************** Operations ******************** //

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Ray))
			return false;
		Ray other = (Ray) obj;
		return super.equals(obj) && _p0.equals(other._p0);
	}

	@Override
	public String toString() {
		return super.toString().replaceFirst("]", "] point=" + _p0);
	}
}
