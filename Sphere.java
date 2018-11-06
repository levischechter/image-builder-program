package geometries;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import primitives.*;

public class Sphere extends RadialGeometry {

	private Point_3D _center;

	// ***************** Constructors ********************** //
	/**
	 * construct Sphere with point and radius
	 * 
	 * @param radius
	 *            - double
	 * @param center
	 *            - Point_3D
	 */
	public Sphere(double radius, Point_3D center, Color color, Material material) {
		super(radius, color, material);
		_center = center;
	}

	// ***************** Getters/Setters ********************** //

	public Point_3D center() {
		return _center;
	}

	public double radius() {
		return _radius;
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
		return _center.subtract(other).normalize();
	}

	@SuppressWarnings("unlikely-arg-type")
	@Override
	public Map<Geometry, List<Point_3D>> findIntersections(Ray ray) {
		List<Point_3D> intersectionsList = new ArrayList<Point_3D>();
		Map<Geometry, List<Point_3D>> intersections = new HashMap<Geometry, List<Point_3D>>();
		Point_3D rayP0 = ray.p0();
		Vector rayDir = ray.direction();
		Vector u;
		try {
			u = _center.subtract(rayP0);
		} catch (IllegalArgumentException e) {
			intersectionsList.add(rayP0.add(rayDir.scale(_radius)));
			intersections.put(this, intersectionsList);
			return intersections;
		}
		double tm = u.dotProduct(rayDir);
		double d = Math.sqrt(u.dotProduct(u) - tm * tm);

		if (d > _radius)
			return intersections;
		double th = Math.sqrt(_radius * _radius - d * d);
		if (Coordinate.ZERO.equals(th)) {
			if (tm > 0)
				intersectionsList.add(rayP0.add(rayDir.scale(tm)));
		} else {
			double t1 = tm - th;
			if (t1 > 0 && !Coordinate.ZERO.equals(t1))
				intersectionsList.add(rayP0.add(rayDir.scale(t1)));
			double t2 = tm + th;
			if (t2 > 0 && !Coordinate.ZERO.equals(t2))
				intersectionsList.add(rayP0.add(rayDir.scale(t2)));
		}
		if (!intersectionsList.isEmpty())
			intersections.put(this, intersectionsList);
		return intersections;
	}
}
