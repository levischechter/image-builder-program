package renderer;

import primitives.*;
import scene.Scene;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

//import javax.xml.bind.annotation.adapters.NormalizedStringAdapter;

import elements.LightSource;
import geometries.Geometry;

public class Render {

	private static final int MAX_CALC_COLOR_LEVEL = 3;

	private Scene _scene;
	private ImageWriter _imageWriter;

	private static class GeoPoint {
		public Geometry geometry;
		public Point_3D point;
	}

	// ***************** Constructors ********************** //
	/**
	 * construct Render with Image Writer and Scene
	 * 
	 * @param imageWriter
	 * @param scene
	 */
	public Render(ImageWriter imageWriter, Scene scene) {
		_scene = scene;
		_imageWriter = imageWriter;
	}

	// ***************** Operations ******************** //
	/**
	 * painting a picture
	 */
	public void renderImage() {
		int Nx = _imageWriter.getNx();
		int Ny = _imageWriter.getNy();
		double distance = _scene.distance();
		int width = _imageWriter.getWidth();
		int height = _imageWriter.getHeight();

		for (int i = 0; i < Ny; i++)
			for (int j = 0; j < Nx; j++) {
				Ray ray = _scene.camera().constructRayThroughPixel(Nx, Ny, i, j, distance, width, height);
				Map<Geometry, List<Point_3D>> intersectionsPoints = _scene.getGeomtries().findIntersections(ray);
				if (intersectionsPoints.isEmpty())
					_imageWriter.writePixel(i, j, _scene.background().getColor());
				else {
					GeoPoint closesPoint = getClosestPoint(intersectionsPoints);
					_imageWriter.writePixel(i, j, calcColor(closesPoint, ray).getColor());
				}
			}
	}

	/**
	 * prints grid
	 * 
	 * @param interval
	 *            - integer
	 */
	public void printGrid(int interval) {
		int Nx = _imageWriter.getNx();
		int Ny = _imageWriter.getNy();
		for (int i = 0; i < Ny - 1; i++)
			for (int j = 0; j < Nx - 1; j++)
				if ((i + 1) % interval == 0 || (j + 1) % interval == 0)
					_imageWriter.writePixel(j, i, _scene.ambientLight().color().getColor());
	}

	/**
	 * 
	 */
	public void writeToImage() {
		_imageWriter.writeToimage();
	}

	/***
	 * finding reflected ray
	 * @param n
	 * @param p
	 * @param v
	 * @return Ray
	 */
	private Ray constructReflectedRay(Vector n, Point_3D p, Ray v) {
		try {
			return new Ray(v.subtract(n.scale(2 * v.dotProduct(n))), addEps(p, v, n));
		} catch (IllegalArgumentException s) {
			return new Ray(v, addEps(p, v, n));
		}
	}

	/***
	 * finding refracted ray
	 * @param n
	 * @param p
	 * @param v
	 * @return Ray
	 */
	private Ray constructRefractedRay(Vector n, Point_3D p, Ray v) {
		return new Ray(v, addEps(p, v, n));
	}

	/***
	 * finds the closest intersected point
	 * @param ray
	 * @return GeoPoint
	 */
	private GeoPoint findClosestIntersection(Ray ray) {
		Map<Geometry, List<Point_3D>> intersections = _scene.getGeomtries().findIntersections(ray);
		if (!intersections.isEmpty())
			return getClosestPoint(intersections);
		return null;
	}

	/**
	 * wrap function for calcColor
	 * 
	 * @param geopoint
	 * @param inRay
	 * @return Color
	 */
	private Color calcColor(GeoPoint geopoint, Ray inRay) {
		return calcColor(geopoint, inRay, MAX_CALC_COLOR_LEVEL, 1.0);
	}

	/**
	 * calculate the color to paint the pixel
	 * 
	 * @param p
	 *            - Point_3D
	 * @param shape
	 *            - Geometry
	 * @return color
	 */
	/*
	 * @SuppressWarnings("unlikely-arg-type") private Color calcColor(GeoPoint
	 * geopoint, Ray inRay, int level, double k) { if (level == 0 ||
	 * Coordinate.ZERO.equals(k)) return new Color(); Material mat =
	 * geopoint.geometry.material(); double kd = mat.kd(); double ks = mat.ks(); int
	 * nShininess = mat.nShininess(); double kr = mat.kr(); double kt = mat.kt();
	 * Color color = new Color(_scene.ambientLight().color()).scale(Math.max(0, 1 -
	 * kr - kt)); color = color.add(geopoint.geometry.emmission());
	 * 
	 * Vector v = inRay.direction(); Vector n =
	 * geopoint.geometry.getNormal(geopoint.point);
	 * 
	 * for (LightSource lightSource : _scene.lights()) { Vector l =
	 * lightSource.getL(geopoint.point); if (n.dotProduct(l) * n.dotProduct(v) > 0)
	 * { double o = occluded(l, geopoint); if (!Coordinate.ZERO.equals(o * k)) {
	 * Color lightIntensity = lightSource.getIntensity(geopoint.point).scale(o);
	 * color.add(calcDiffusive(kd, l, n, lightIntensity), calcSpecular(ks, l, n, v,
	 * nShininess, lightIntensity)); } } }
	 * 
	 * Ray reflectedRay = constructReflectedRay(n, geopoint.point, inRay); GeoPoint
	 * reflectedPoint = findClosestIntersection(reflectedRay); if (reflectedPoint !=
	 * null) { Color reflectedLight = calcColor(reflectedPoint, reflectedRay, level
	 * - 1, k * kr).scale(kr); color.add(reflectedLight); }
	 * 
	 * Ray refractedRay = constructRefractedRay(n, geopoint.point, inRay); GeoPoint
	 * refractedPoint = findClosestIntersection(refractedRay); if (refractedPoint !=
	 * null) { Color refractedLight = calcColor(refractedPoint, refractedRay, level
	 * - 1, k * kt).scale(kt); color.add(refractedLight); }
	 * 
	 * return color; }
	 */
	
	/***
	 * create a cone shape of rays
	 * @param baseRay the ray in the center
	 * @param radius the radius of the ray
	 * @return a list with all the rays
	 */
	private ArrayList<Ray> coneRays(Ray baseRay, double radius) {
		ArrayList<Ray> list = new ArrayList<>();
		list.add(baseRay);
		Random random = new Random();
		Vector normal = baseRay.findNormal();
		Vector cross = baseRay.crossProduct(normal);
		double a,b;
		for (int i = 0; i < 16; ++i) {
			a = -radius + (2*radius)*random.nextDouble();
			if (Coordinate.isZero(a))
				a += 0.001;
			b = -radius + (2*radius)*random.nextDouble();
			if(Coordinate.isZero(b))
				b += 0.001;
			list.add(baseRay.nearbyRay(normal, cross, a, b));
		}
		return list;
	}

	/***
	 * recursive method to calculate the color to paint a pixel
	 * @param geopoint intersection point with the ray and a geometry
	 * @param inRay the ray that intersect the point
	 * @param level integer that defines the recursion level
	 * @param k double that defines the reflection/refraction quality 
	 * @return the color to paint the pixel
	 */
	@SuppressWarnings("unlikely-arg-type")
	private Color calcColor(GeoPoint geopoint, Ray inRay, int level, double k) {
		if (level == 0 || Coordinate.ZERO.equals(k))
			return new Color(0, 0, 0);

		Material mat = geopoint.geometry.material();
		double kd = mat.kd();
		double ks = mat.ks();
		int nShininess = mat.nShininess();
		double kr = mat.kr();
		double kt = mat.kt();
		Color color = new Color(_scene.ambientLight().color()).scale(Math.max(0, 1 - kr - kt));
		color = color.add(geopoint.geometry.emmission());

		Vector v = inRay.direction();
		Vector n = geopoint.geometry.getNormal(geopoint.point);

		for (LightSource lightSource : _scene.lights()) {

			Vector l = lightSource.getL(geopoint.point);

			if (n.dotProduct(l) * n.dotProduct(v) > 0) {
				double o = occluded(l, geopoint);
				if (!Coordinate.ZERO.equals(o)) {
					Color lightIntensity = lightSource.getIntensity(geopoint.point).scale(o);
					color.add(calcDiffusive(kd, l, n, lightIntensity));
					color.add(calcSpecular(ks, l, n, v, nShininess, lightIntensity));
				}
			}
		}

		Color reflectedLight = new Color();
		Ray reflectedRay = constructReflectedRay(n, geopoint.point, inRay);
		GeoPoint reflectedPoint = findClosestIntersection(reflectedRay);
		if (reflectedPoint != null) {
			ArrayList<Ray> reflectedList = coneRays(reflectedRay, 1);
			
			int size = reflectedList.size();
			for (int i = 0; i < size; ++i)
				reflectedLight = calcColor(reflectedPoint, reflectedList.get(i), level - 1, k * kr);
			reflectedLight.scale(kr / size);
			color.add(reflectedLight);
		}
		
		Ray refractedRay = constructRefractedRay(n, geopoint.point, inRay);
		GeoPoint refractedPoint = findClosestIntersection(refractedRay);
		if (refractedPoint != null) {
			Color refractedLight = new Color();
			ArrayList<Ray> refractedList = coneRays(refractedRay, 1);
			
			int size = refractedList.size();
			for (int i = 0; i < size; ++i)
				refractedLight = calcColor(refractedPoint, refractedList.get(i), level - 1, k * kt);
			refractedLight.scale(kt / size);
			color.add(refractedLight);
		}

		return color;
	}

	private Color calcDiffusive(double kd, Vector l, Vector n, Color lightIntensity) {
		return new Color(lightIntensity).scale(kd * Math.abs(l.dotProduct(n)));
	}

	private Color calcSpecular(double ks, Vector l, Vector n, Vector v, int nShininess, Color lightIntensity) {
		double vr = v.dotProduct(l.subtract(n.scale(2 * l.dotProduct(n))));
		return vr > 0 ? new Color() : new Color(lightIntensity).scale(Math.pow(-vr, nShininess) * ks);
	}

	Point_3D addEps(Point_3D p, Vector v, Vector n) {
		Vector epsVector = n.scale(n.dotProduct(v) > 0 ? 0.1 : -0.1);
		return p.add(epsVector);
	}

	private double occluded(Vector l, GeoPoint geopoint) {
		Vector tolightDirection = l.scale(-1);
		Ray lightRay = new Ray(tolightDirection,
				addEps(geopoint.point, tolightDirection, geopoint.geometry.getNormal(geopoint.point)));
		Map<Geometry, List<Point_3D>> intersectionPoints = _scene.getGeomtries().findIntersections(lightRay);
		double shadowK = 1;
		for (Map.Entry<Geometry, List<Point_3D>> entry : intersectionPoints.entrySet())
			shadowK *= entry.getKey().material().kt();
		return shadowK;
	}

	private GeoPoint getClosestPoint(Map<Geometry, List<Point_3D>> intersectionsPoints) {
		GeoPoint geopoint = new GeoPoint();
		double minDistance = Double.MAX_VALUE;
		Point_3D p0 = _scene.camera().p0();
		for (Map.Entry<Geometry, List<Point_3D>> entry : intersectionsPoints.entrySet())
			for (Point_3D point : entry.getValue())
				if (p0.distance(point) < minDistance) {
					geopoint.geometry = entry.getKey();
					geopoint.point = point;
					minDistance = p0.distance(point);
				}
		return geopoint;
	}
}