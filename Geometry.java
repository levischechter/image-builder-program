package geometries;

import primitives.*;

public abstract class Geometry implements Intersectable {

	protected Color _emmission;
	protected Material _material;
	// ***************** Constructors ********************** //

	public Geometry(Color color, Material material) {
		_emmission = color;
		_material = material;
	}

	public Geometry(Geometry other) {
		_emmission = other._emmission;
		_material = other._material;
	}

	// ***************** Operations ******************** //
	/**
	 * abstract method
	 * 
	 * @param other
	 *            - Point_3D
	 * @return the vector normal to the geometry in point other
	 */
	public abstract Vector getNormal(Point_3D other);

	public Color emmission() {
		return _emmission;
	}
	public Material material() {
		return _material;
	}

}
