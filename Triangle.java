package geometries;

import java.util.List;
import java.util.Map;
import primitives.*;

public class Triangle extends Plane {

	private Point_3D _p1;
	private Point_3D _p2;
	private Point_3D _p3;

	// ***************** Constructors ********************** //
	/**
	 * construct triangle with 3 points
	 * 
	 * @param p1
	 *            - Point_3D
	 * @param p2
	 *            - Point_3D
	 * @param p3
	 *            - Point_3D
	 */
	public Triangle(Point_3D p1, Point_3D p2, Point_3D p3, Color color, Material material) {
		super(p1, p2, p3, color, material);
		if (p1.equals(p2) || p1.equals(p3) || p2.equals(p3))
			throw new IllegalArgumentException("points must be different");
		_p1 = p1;
		_p2 = p2;
		_p3 = p3;
	}

	// ***************** Getters/Setters ********************** //

	public Point_3D p1() {
		return _p1;
	}

	public Point_3D p2() {
		return _p2;
	}

	public Point_3D p3() {
		return _p3;
	}

	public Color emmission() {
		return _emmission;
	}

	public Material Material() {
		return _material;
	}

	// ***************** Operations ******************** //

	@Override
	public Vector getNormal(Point_3D other) {
		return super._normal;
	}
		
	@Override
	public Map<Geometry, List<Point_3D>> findIntersections(Ray ray) {
		Map<Geometry, List<Point_3D>> intersections = super.findIntersections(ray);
		if(intersections.isEmpty()) return intersections;
		Point_3D p0 = ray.p0();
		Vector v = intersections.get(this).get(0).subtract(p0);
		Vector v1 = _p1.subtract(p0);
		Vector v2 = _p2.subtract(p0);
		Vector v3 = _p3.subtract(p0);
		Vector n1 = v1.crossProduct(v2);
		Vector n2 = v2.crossProduct(v3);
		Vector n3 = v3.crossProduct(v1);
		double c1 = v.dotProduct(n1);
		double c2 = v.dotProduct(n2);
		double c3 = v.dotProduct(n3);
		if (!((c1 > 0 && c2 > 0 && c3 > 0) || (c1 < 0 && c2 < 0 && c3 < 0)))
			intersections.clear();
		return intersections;
	}
}
