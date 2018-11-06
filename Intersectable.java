package geometries;

import java.util.List;
import java.util.Map;
import primitives.Point_3D;
import primitives.Ray;

public interface Intersectable {
	public Map<Geometry, List<Point_3D>> findIntersections(Ray ray);
}
