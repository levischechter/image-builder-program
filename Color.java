package primitives;

public class Color {

	private double _r;
	private double _g;
	private double _b;

	public static final Color BLACK = new Color(0, 0, 0);
	public static final Color WHITE = new Color(255, 255, 255);
	public static final Color RED = new Color(255, 0, 0);
	public static final Color GREEN = new Color(0, 255, 0);
	public static final Color BLUE = new Color(0, 0, 255);

	// ***************** Constructors ********************** //
	/**
	 * construct color with 3 numbers
	 * 
	 * @param r
	 *            - double
	 * @param g
	 *            - double
	 * @param b
	 *            - double
	 */
	public Color(double r, double g, double b) {
		_r = r;
		_g = g;
		_b = b;
	}

	/**
	 * construct color with 3 numbers
	 * 
	 * @param r
	 *            - int
	 * @param g
	 *            - int
	 * @param b
	 *            - int
	 */
	public Color(int r, int g, int b) {
		_r = r;
		_g = g;
		_b = b;
	}

	/**
	 * copy constructor
	 * 
	 * @param other
	 */
	public Color(Color other) {
		_r = other._r;
		_g = other._g;
		_b = other._b;
	}

	/**
	 * construct color with java color
	 * 
	 * @param color
	 */
	public Color(java.awt.Color color) {
		_r = color.getRed();
		_g = color.getGreen();
		_b = color.getBlue();
	}

	/**
	 * default constructor
	 */
	public Color() {
		_r = 0;
		_g = 0;
		_b = 0;
	}
	// ***************** Getters/Setters ********************** //

	public java.awt.Color getColor() {
		int r = _r > 255 ? 255 : (int) _r;
		int g = _g > 255 ? 255 : (int) _g;
		int b = _b > 255 ? 255 : (int) _b;
		return new java.awt.Color(r, g, b);
	}

	public void setColor(double r, double g, double b) {
		_r = r;
		_g = g;
		_b = b;
	}

	public void setColor(Color other) {
		setColor(other._r, other._g, other._b);
	}

	// ***************** Operations ******************** //
	/**
	 * adds a color
	 * 
	 * @param x
	 *            - color
	 * @return color intensified by x
	 */
	public Color add(Color... colors) {
		for (Color x : colors) {
			_r += x._r;
			_g += x._g;
			_b += x._b;
		}
		return this;
	}

	/**
	 * multiply a color
	 * 
	 * @param x
	 *            - double
	 * @return color intensified by x
	 */
	public Color scale(double x) {
		_r *= x;
		_g *= x;
		_b *= x;
		return this;
	}

	/**
	 * divide a color
	 * 
	 * @param x
	 *            - double
	 * @return color reduced by x
	 */
	public Color reduce(double x) {
		_r /= x;
		_g /= x;
		_b /= x;
		return this;
	}

	@Override
	public String toString() {
		return "Color [" + _r + ", " + _g + ", " + _b + "]";
	}

}
