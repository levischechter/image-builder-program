package geometries;

import java.util.List;
import java.util.Map;

import primitives.Color;
import primitives.Coordinate;
import primitives.Material;
import primitives.Point_3D;
import primitives.Ray;
import primitives.Vector;

public class Rectangle extends Plane {
	Point_3D _downRightP;

	Vector _left;
	Vector _up;

	double _width;
	double _height;

	@SuppressWarnings("unlikely-arg-type")
	public Rectangle(Point_3D downRightP, Vector left, Vector up, double width, double height, Color color,
			Material material) {
		super(downRightP, downRightP.add(up), downRightP.add(left), color, material);
		if (left.dotProduct(up) != 0)
			throw new IllegalArgumentException("vectors should be vertical");
		if (Coordinate.ZERO.equals(width) || Coordinate.ZERO.equals(height))
			throw new IllegalArgumentException("rectangle must have some surface");
		_left = left.normalize();
		_up = up.normalize();
		_width = width;
		_height = height;
		_downRightP = downRightP;
	}

	public Rectangle(Point_3D drP, Point_3D dlP, Point_3D ulP, Point_3D urP, Color color, Material material) {
		super(drP, urP, dlP, color, material);
		if (urP.subtract(ulP).dotProduct(urP.subtract(drP)) != 0
				|| dlP.subtract(drP).dotProduct(dlP.subtract(ulP)) != 0)
			throw new IllegalArgumentException("vectors should be vertical");
		
		_left = dlP.subtract(drP);
		_up = urP.subtract(drP);
		_width = _left.length();
		_height = _up.length();
		_downRightP = drP;
		
		_left = _left.normalize();
		_up = _up.normalize();
	}

	public Point_3D downRightP() {
		return _downRightP;
	}

	public Vector left() {
		return _left;
	}

	public Vector up() {
		return _up;
	}

	public double get_width() {
		return _width;
	}

	public double height() {
		return _height;
	}

	@Override
	public Map<Geometry, List<Point_3D>> findIntersections(Ray ray) {
		Map<Geometry, List<Point_3D>> intersections = super.findIntersections(ray);
		if (intersections.isEmpty())
			return intersections;

		Point_3D intersectionPoint = intersections.get(this).get(0);

		Point_3D p2 = _downRightP.add(_left.scale(_width));
		Point_3D p3 = p2.add(_up.scale(_height));
		Point_3D p4 = p3.add(_left.scale(-_width));

		Vector v3 = _left.scale(-1);
		Vector v4 = null;
		Vector v5 = null;
		Vector v6 = _up.scale(-1);
		Vector v7 = null;
		Vector v8 = null;
		try {
			v4 = intersectionPoint.subtract(_downRightP).normalize();
			v5 = intersectionPoint.subtract(p3).normalize();
			v7 = intersectionPoint.subtract(p2).normalize();
			v8 = intersectionPoint.subtract(p4).normalize();
		} catch (IllegalArgumentException e) {
			if (e.getMessage().matches("zero vector is illegal"))
				return intersections;
			else System.out.println("something wrong happaned");
		}
		
		double n1 = _left.dotProduct(v4);
		double n2 = v3.dotProduct(v5);
		double n3 = _up.dotProduct(v7);
		double n4 = v6.dotProduct(v8); 

		if (!(n1 >= 0 && n2 >= 0 && n3 >= 0 && n4 >= 0))
			intersections.clear();
		return intersections;
	}

	@Override
	public Vector getNormal(Point_3D other) {
		return super._normal;
	}

}
