package geometries;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import primitives.*;

public class Plane extends Geometry {

	protected Point_3D _q0;
	protected Vector _normal;

	// ***************** Constructors ********************** //
	/**
	 * construct a plane with point and normal vector
	 * 
	 * @param point
	 *            - 3D_Point
	 * @param normalVector
	 *            - vector
	 */
	public Plane(Point_3D point, Vector normalVector, Color color, Material material) {
		super(color, material);
		_q0 = new Point_3D(point);
		_normal = normalVector.normalize();
	}

	/**
	 * construct plane with 3 points
	 * 
	 * @param p1
	 *            - Point_3D
	 * @param p2
	 *            - Point_3D
	 * @param p3
	 *            - Point_3D
	 */
	public Plane(Point_3D p1, Point_3D p2, Point_3D p3, Color color, Material material) {
		super(color, material);
		_q0 = p1;
		
		Vector v1;
		Vector v2;
		try {
			v1 = p1.subtract(p2);
			v2 = p1.subtract(p3);
		} catch (Exception e) {
			throw new IllegalArgumentException("same points exception");
		}
		_normal = v1.crossProduct(v2);
	}

	
	// ***************** Getters/Setters ********************** //

	public Point_3D q0() {
		return _q0;
	}

	public Vector normal() {
		return _normal;
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
		return _normal;
	}

	@SuppressWarnings("unlikely-arg-type")
	@Override
	public Map<Geometry, List<Point_3D>> findIntersections(Ray ray) {
		List<Point_3D> intersectionsList = new ArrayList<Point_3D>();
		Map<Geometry, List<Point_3D>> intersections = new HashMap<Geometry, List<Point_3D>>();
		Vector rayDir = ray.direction();
		Point_3D rayP0 = ray.p0();
		double d = _normal.dotProduct(rayDir);
		if (Coordinate.ZERO.equals(d))
			return intersections;
		try {
			double t = _normal.dotProduct(_q0.subtract(rayP0)) / d;
			if (t > 0) {
				intersectionsList.add(rayP0.add(rayDir.scale(t)));
				intersections.put(this, intersectionsList);
			}
		} catch (IllegalArgumentException e) {
		}
		return intersections;
	}
}