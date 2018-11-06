package elements;

import primitives.Color;
import primitives.Point_3D;
import primitives.Vector;

public interface LightSource {

	public Color getIntensity(Point_3D point);
	public Vector getL(Point_3D point);
	public Vector getD(Point_3D point);
}
