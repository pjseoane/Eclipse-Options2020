package qaant.com.OptionModels2020;

import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.distribution.NormalDistribution;

public class BSmodel {

	private final static String modelName= "Black Scholes";
	protected UnderlyingAsset underlying;
	protected OptionElements optionStructure;
	protected double prima,delta,gamma,vega,theta,rho=0;
	
	
	
	public BSmodel() {};
	
	public BSmodel(UnderlyingAsset underlying, OptionElements optionStructure) {
		super();
		this.underlying = underlying;
		this.optionStructure = optionStructure;
		optionStructure.setExerciseType('E');
		
	}

	public void run() {// the black scholes model
		double startTime	=System.currentTimeMillis();
		
		
		double underlyingValue=underlying.getUnderlyingValue();
		double dayYear=optionStructure.getDaysToExpiration()/365;
		double strike=optionStructure.getStrike();
		
		double rate=optionStructure.getInterestRate();
		double modelVlt=optionStructure.getImpliedVlt();
		//optionStructure.setImpliedVlt(modelVlt);
		
		
		double q=0; 
	      
		switch (underlying.getTipoContrato()){
            case UnderlyingAsset.STOCK:
            	q= underlying.getUnderlyingDividendYield();
            	break;
            case UnderlyingAsset.FUTURES:
            	q= rate;
            	break;
		}
	    
			double underlyingNPV=underlyingValue*Math.exp(-q*dayYear);
			double z =Math.exp(-rate*dayYear);
			double ww=Math.exp(-q*dayYear); //drift
       
			double d1= (Math.log(underlyingNPV *Math.exp(q*dayYear)/ strike) + dayYear * (rate - q + modelVlt * modelVlt / 2)) / (modelVlt * Math.sqrt(dayYear));
			double d2 = d1 - modelVlt * Math.sqrt(dayYear);
       
			double cndfd1 = new NormalDistribution().cumulativeProbability(d1);
			double cndfd2 = new NormalDistribution().cumulativeProbability(d2);
			double pdfd1 = new NormalDistribution().density(d1);
       
			gamma = pdfd1 * ww / (underlyingNPV * modelVlt * Math.sqrt(dayYear));
			vega = underlyingNPV * Math.sqrt(dayYear) * pdfd1 / 100;
       
	   
			switch(optionStructure.getOptionType()){
       
       			case OptionElements.CALL:
    	   
       				prima = underlyingNPV * cndfd1 - z * strike * cndfd2;
       				delta = ww * cndfd1;
       				theta = (-(underlyingNPV * modelVlt * pdfd1 / (2 * Math.sqrt(dayYear))) - strike * rate * z * cndfd2 + q * underlyingNPV * cndfd1)/365 ;
       				rho = strike * dayYear * Math.exp(-(rate - q) * dayYear) * cndfd2 / 100;
       				break;
           
       			case OptionElements.PUT:
           
       				double cndf_d1= new NormalDistribution().cumulativeProbability(-d1);
       				double cndf_d2= new NormalDistribution().cumulativeProbability(-d2);
           
       				prima = -underlyingNPV * cndf_d1 + z * strike * cndf_d2;
       				delta = ww * (cndfd1 - 1);
       				theta = (-(underlyingNPV * modelVlt * pdfd1 / (2 * Math.sqrt(dayYear))) + strike * rate * z * cndf_d2 - q * underlyingNPV * cndf_d1) / 365;
       				rho = -strike * dayYear * Math.exp(-(rate - q) * dayYear) * cndf_d2 / 100;
                           
       				break;
       
		}
  }
	
	
	public double getImpliedVlt(double mktPrice) {
		optionStructure.setOptionMktPrice(mktPrice);
		return getImpliedVlt();
		
	}
	public double getImpliedVlt() {
		UnivariateFunction f = xVlt -> optionStructure.getOptionMktPrice()- optionWithThisVlt(xVlt).getPrima();
		ImpliedVolSolver solver=new ImpliedVolSolver(underlying,optionStructure,prima);
		return (solver.bisection(f));
	}
		
	private BSmodel optionWithThisVlt(double xVlt) {
		optionStructure.setImpliedVlt(xVlt);
		BSmodel bsOption = new BSmodel(underlying, optionStructure);
		bsOption.run();
        return bsOption;
	}

	public static String getModelName() {
		return modelName;
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
