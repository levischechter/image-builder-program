package elements;

import primitives.Color;
import primitives.Point_3D;
import primitives.Vector;

public class DirectionalLight extends Light implements LightSource {

	private Vector _direction;

	// ***************** Constructors ********************** //

	public DirectionalLight(Color color, Vector direction) {
		super(color);
		_direction = direction.normalize();
	}

	// ***************** Getters/Setters ********************** //

	public Color color() {
		return _color;
	}

	// ***************** Operations ******************** //

	@Override
	public Color getIntensity(Point_3D point) {
		return new Color(_color);
	}

	@Override
	public Vector getL(Point_3D point) {
		return _direction;
	}

	@Override
	public Vector getD(Point_3D point) {
		return _direction;
	}
}
