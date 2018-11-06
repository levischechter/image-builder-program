package primitives;

public class Material {
	
	private double _Kd;
	private double _Ks;
	private double _Kr;
	private double _Kt;
	private int _nShininess;
	private double _clarity;
	
	// ***************** Constructors ********************** //


	/***
	 * constructor
	 * @param kd
	 * @param ks
	 * @param nShininess
	 * @param kr
	 * @param kt
	 */
	public Material(double kd, double ks, int nShininess,double kr,double kt, double clarity) {
		super();
		_Kd = kd;
		_Ks = ks;
		_nShininess = nShininess;
		_Kr = kr;
		_Kt = kt;
		_clarity = clarity;
	}
	
	// ***************** Getters/Setters ********************** //

	public double kd() {
		return _Kd;
	}
	public double ks() {
		return _Ks;
	}
	public int nShininess() {
		return _nShininess;
	}
	public double kr() {
		return _Kr;
	}
	public double kt() {
		return _Kt;
	}
	public void kd(double kd) {
		_Kd = kd;
	}
	public void ks(double ks) {
		_Ks = ks;
	}
	public void nShininess(int nShininess) {
		_nShininess = nShininess;
	}
	public void kr(double kr) {
		_Kr = kr;
	}
	public void kt(double kt) {
		_Kt = kt;
	}

	
}
