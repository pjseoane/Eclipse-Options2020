package qaant.com.OptionModels2020;

public class AnyModel {
	protected UnderlyingAsset underlying;
	protected OptionElements optionStructure;
	protected double prima,delta,gamma,vega,theta,rho=0;
	
	public AnyModel(UnderlyingAsset underlying, OptionElements optionStructure) {
		super();
		this.underlying = underlying;
		this.optionStructure = optionStructure;
	}
	
	public void runBS() {
		
		BSmodel opt = new BSmodel(underlying,optionStructure);
		opt.run();
		prima=opt.getPrima();
		
	}
	public void runW() {
		WhaleyModel opt = new WhaleyModel(underlying,optionStructure);
		opt.run();
		prima=opt.getPrima();
	}

	public UnderlyingAsset getUnderlying() {
		return underlying;
	}

	public OptionElements getOptionStructure() {
		return optionStructure;
	}

	public double getPrima() {
		return prima;
	}

	public double getDelta() {
		return delta;
	}

	public double getGamma() {
		return gamma;
	}

	public double getVega() {
		return vega;
	}

	public double getTheta() {
		return theta;
	}

	public double getRho() {
		return rho;
	}
	
}
