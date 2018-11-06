package elements;

import primitives.Color;
import primitives.Point_3D;
import primitives.Vector;

public class PointLight extends Light implements LightSource {

	protected Point_3D _position;
	protected double _Kc;
	protected double _Kl;
	protected double _Kq;

	// ***************** Constructors ********************** //

	public PointLight(Color color, Point_3D position, double kc, double kl, double kq) {
		super(color);
		_position = position;
		_Kc = kc;
		_Kl = kl;
		_Kq = kq;
	}

	// ***************** Getters/Setters ********************** //

	public Color color() {
		return _color;
	}

	// ***************** Operations ******************** //

	@Override
	public Color getIntensity(Point_3D point) {
		double d = point.distance(_position);
		double m = _Kc + (_Kl * d) + (_Kq * d * d);
		return new Color(_color).reduce(m);
	}

	@Override
	public Vector getL(Point_3D point) {
		return point.subtract(_position).normalize();
	}

	@Override
	public Vector getD(Point_3D point) {
		return getL(point);
	}

}
