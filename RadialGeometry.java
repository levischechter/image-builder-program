package geometries;

import primitives.Color;
import primitives.Material;

abstract class RadialGeometry extends Geometry {

	protected double _radius;

	// ***************** Constructors ********************** //
	/**
	 * construct radial geometry with radius
	 * 
	 * @param radius
	 */
	public RadialGeometry(double radius, Color color, Material material) {
		super(color, material);
		_radius = radius;
	}

	/**
	 * copy constructor
	 * 
	 * @param other
	 */

	public RadialGeometry(RadialGeometry other) {
		super(other);
		_radius = other._radius;
	}

	// ***************** Getters/Setters ********************** //

	public double radius() {
		return _radius;
	}

	public Color emmission() {
		return _emmission;
	}
	
	public Material material() {
		return _material;
	}
	
}
