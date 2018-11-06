package elements;

import primitives.Color;

public class AmbientLight extends Light {

	private double _Ka;
	public static final AmbientLight NO_AMBIENT = new AmbientLight(Color.BLACK, 0);

	// ***************** Constructors ********************** //
	/**
	 * construct Ambient Light with Color and double
	 * 
	 * @param color
	 * @param ka
	 */
	public AmbientLight(Color color, double ka) {
		super(new Color(color));
		_Ka = ka;
		_color.scale(_Ka);
	}

	// ***************** Getters/Setters ********************** //

	public Color color() {
		return _color;
	}
}
