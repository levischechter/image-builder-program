package geometries;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import primitives.*;

public class Geometries implements Intersectable {

	private List<Intersectable> _geometries;

	// ***************** Constructors ******************** //
	public Geometries() {
		_geometries = new ArrayList<Intersectable>();
	}

	// ***************** Operations ******************** //
	/**
	 * adds a shape to the list
	 * 
	 * @param shape
	 *            - Geometry
	 */
	public void addGeometry(Intersectable shape) {
		_geometries.add(shape);
	}

	public Map<Geometry, List<Point_3D>> findIntersections(Ray ray) {
		Map<Geometry, List<Point_3D>> intersectionsPoints = new HashMap<Geometry, List<Point_3D>>();
		for (Intersectable shape : _geometries)
				intersectionsPoints.putAll(shape.findIntersections(ray));
		return intersectionsPoints;
	}
}