package primitives;

public class Coordinate {

	private double _coordinate;
	private static final int ACCURCY = -20;
	public static final Coordinate ZERO = new Coordinate(0);

	// ***************** Constructors ********************** //

	public Coordinate(double coord) {
		_coordinate = (getExp(coord) < ACCURCY) ? 0.0 : coord;
	}

	public Coordinate(Coordinate other) {
		_coordinate = other._coordinate;
	}

	// ***************** Getters/Setters ********************** //

	public double coordinate() {
		return _coordinate;
	}

	// ***************** Operations ******************** //

	private static int getExp(double num) {
		return (int) ((Double.doubleToRawLongBits(num) >> 52) & 0x7FFL) - 1023;
	}

	public static boolean isZero(double number) {
		return getExp(number) < ACCURCY;
	}

	/**
	 * subtracts a number from the coordinate
	 * 
	 * @param other
	 *            - another number to subtract
	 * @return double the subtraction between the coordinate and other
	 */
	private double subtract(double other) {
		double result = _coordinate - other;
		int resultExp = getExp(result);
		if (resultExp < ACCURCY)
			return 0.0;
		int thisExp = getExp(_coordinate);
		if ((resultExp - thisExp) < ACCURCY)
			return _coordinate;
		return result;
	}

	/**
	 * adds a number to the coordinate
	 * 
	 * @param other
	 *            - another number to add
	 * @return double - the sum of coordinate and other
	 */
	private double add(double other) {
		double result = _coordinate + other;
		int resultExp = (int) ((Double.doubleToRawLongBits(result) >> 52) & 0x7FFL);
		if (Math.abs(resultExp) < ACCURCY)
			return 0.0;
		int thisExp = (int) ((Double.doubleToRawLongBits(_coordinate) >> 52) & 0x7FFL);
		if (Math.abs(thisExp - resultExp) < ACCURCY)
			return _coordinate;
		return result;
	}

	/**
	 * using subtract with double
	 * 
	 * @param other
	 *            - another coordinate to subtract
	 * @return coordinate - the subtraction between coordinate and other
	 */
	public Coordinate subtract(Coordinate other) {
		return new Coordinate(subtract(other._coordinate));
	}

	/**
	 * using add with double
	 * 
	 * @param other
	 *            - another coordinate to subtract
	 * @return coordinate - the sum of coordinate and other
	 */
	public Coordinate add(Coordinate other) {
		return new Coordinate(add(other._coordinate));
	}

	/**
	 * scale coordinate with another number
	 * 
	 * @param scalar
	 *            - number to scale
	 * @return coordinate scaled
	 */
	public Coordinate scale(double scalar) {
		return new Coordinate(_coordinate * scalar);
	}

	/**
	 * using scale
	 * 
	 * @param other
	 *            another coordinate to multiply
	 * @return coordinate multiplied
	 */
	public Coordinate multi(Coordinate other) {
		return scale(other._coordinate);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (obj instanceof Double)
			return subtract((double) obj) == 0.0;
		if (!(obj instanceof Coordinate))
			return false;
		Coordinate other = (Coordinate) obj;
		return (subtract(other._coordinate) == 0.0);
	}

	@Override
	public String toString() {
		return "" + _coordinate;
	}
}
