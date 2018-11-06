package elements;

import primitives.*;

public class Camera {

	private Point_3D _p0;
	private Vector _vUp;
	private Vector _vTo;
	private Vector _vRight;

	public static final Camera CAMERA = new Camera(Point_3D.ZERO, Vector.Y, Vector.Zm);
	
	// ***************** Constructors ********************** //
	/**
	 * construct Camera with point and 2 vectors
	 * 
	 * @param p0
	 *            - Point_3D
	 * @param vUp
	 *            - Vector
	 * @param vTo
	 *            - Vector
	 */
	@SuppressWarnings("unlikely-arg-type")
	public Camera(Point_3D p0, Vector vUp, Vector vTo) {
		if (!Coordinate.ZERO.equals(vUp.dotProduct(vTo)))
			throw new IllegalArgumentException("vUp & vTo needs to be vertical");
		_p0 = p0;
		_vUp = vUp.normalize();
		_vTo = vTo.normalize();
		_vRight = vTo.crossProduct(vUp);
	}

	// ***************** Getters/Setters ********************** //

	public Point_3D p0() {
		return _p0;
	}

	public Vector vUp() {
		return _vUp;
	}

	public Vector vTo() {
		return _vTo;
	}

	public Vector vRight() {
		return _vRight;
	}

	// ***************** Operations ******************** //
	/**
	 * 
	 * @param Nx
	 *            - number of horizontal pixels - int
	 * @param Ny
	 *            - number of vertical pixels - int
	 * @param i
	 *            - horizontal index of the view plane - int
	 * @param j
	 *            - vertical index of the view plane - int
	 * @param screenDstance
	 *            - double
	 * @param screenWidth
	 *            - double
	 * @param screenHight
	 *            - double
	 * @return ray between the camera and the view plane
	 */
	public Ray constructRayThroughPixel(int Nx, int Ny, int i, int j, double screenDstance, double screenWidth,
			double screenHight) {

		Point_3D pc = _p0.add(_vTo.scale(screenDstance));
		double Ry = screenHight / Ny;
		double Rx = screenWidth / Nx;
		double pcX = ((double) Nx + 1) / 2;
		double pcY = ((double) Ny + 1) / 2;

		Point_3D Pij = pc;
		if (pcX != i)
			Pij = Pij.add(_vRight.scale((i - pcX) * Rx));
		if (pcY != j)
			Pij = Pij.add(_vUp.scale((pcY - j) * Ry));
		return new Ray(Pij.subtract(_p0), _p0);
	}

	@Override
	public String toString() {
		return "Camera [_p0=" + _p0 + ", _vUp=" + _vUp + ", _vTo=" + _vTo + ", _vRight=" + _vRight + "]";
	}
}
