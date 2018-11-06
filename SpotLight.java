package elements;

import primitives.Color;
import primitives.Point_3D;
import primitives.Vector;

public class SpotLight extends PointLight {

	private Vector _direction;

	// ***************** Constructors ********************** //

	public SpotLight(Color color, Point_3D position, double kc, double kl, double kq, Vector direction) {
		super(color, position, kc, kl, kq);
		_direction = direction.normalize();
	}

	// ***************** Getters/Setters ********************** //

	public Color color(){
		return _color;
	}

	// ***************** Operations ******************** //

	@Override
	public Color getIntensity(Point_3D point) {
		double angle = getD(point).dotProduct(getL(point));
		return (angle < 0) ? new Color() : super.getIntensity(point).scale(angle);
	}

	@Override
	public Vector getL(Point_3D point) {
		return point.subtract(_position).normalize();
	}

	@Override
	public Vector getD(Point_3D point) {
		return _direction;
	}
}
