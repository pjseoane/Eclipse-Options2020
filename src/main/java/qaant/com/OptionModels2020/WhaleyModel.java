package qaant.com.OptionModels2020;


import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.distribution.NormalDistribution;

public class WhaleyModel extends BSmodel {
	private final static String modelName = "Whaley-Barone-Adesi model";

	
	public WhaleyModel(UnderlyingAsset underlying, OptionElements optionStructure) {
		super(underlying, optionStructure);
		// TODO Auto-generated constructor stub
	}

	public void run() {// the black scholes model
		double startTime = System.currentTimeMillis();

		BSmodel bsOption = new BSmodel(underlying, optionStructure);

		bsOption.run();
		// Stock y Call sale por Black Scholes
		// Futuros o Puts de cualquier especie vale por reallyWhaleyModel.

		prima = bsOption.getPrima();
		delta = bsOption.getDelta();
		gamma = bsOption.getGamma();
		vega = bsOption.getVega();
		theta = bsOption.getTheta();
		rho = bsOption.getRho();
		//
		if (underlying.getTipoContrato() == UnderlyingAsset.FUTURES
				|| optionStructure.getOptionType() == OptionElements.PUT) {
			reallyWhaleyModel();
		}

	}

	private void reallyWhaleyModel() {

		// OptionStructure fields
		optionStructure.setExerciseType('A');
		double rate = optionStructure.getInterestRate();
		double daysToExpiration = optionStructure.getDaysToExpiration();
		double dayYear = daysToExpiration / 365;
		
		double callPutFlag = (optionStructure.getOptionType()==OptionElements.CALL)? 1:-1; 
		
		double strike = optionStructure.getStrike();
		char exerciseType = optionStructure.getExerciseType();
		char optionType = optionStructure.getOptionType();
		double modelVlt = optionStructure.getImpliedVlt();
		
		
		// underlying Fields
		char tipoContrato = underlying.getTipoContrato();
		double underlyingValue= underlying.getUnderlyingValue();
		double divYield = underlying.getUnderlyingDividendYield();
		double underlyingNPV=underlyingValue*Math.exp(-divYield*dayYear);

		double q = 0;
		switch (tipoContrato) {
		case UnderlyingAsset.STOCK:
			q = divYield;
			break;
		case UnderlyingAsset.FUTURES:
			q = rate;
			break;
		}

		
		double h = 1 - Math.exp(-rate * dayYear);
		double alfa = 2 * rate / (modelVlt * modelVlt);
		double beta = 2 * (rate - q) * modelVlt;

		double lambda = (-(beta - 1) + callPutFlag * Math.sqrt((beta - 1) * (beta - 1) + 4 * alfa / h)) / 2;
		double eex = Math.exp(-q * dayYear);

		double s1 = strike;
		double zz = 1 / Math.sqrt(2 * Math.PI);
		double zerror = 1;
		double d1;
		double xx;
		double corr;

		double mBSprima;
		double rhs, lhs,nd1,slope;
		
		OptionElements opt = new OptionElements(exerciseType, optionType, strike, daysToExpiration, 0, modelVlt,rate); 
		
		do {
			d1 = (Math.log(s1 / strike) + ((rate - q) + modelVlt / 2) * dayYear) / (modelVlt * Math.sqrt(dayYear));
			xx = (1 - eex * new NormalDistribution().cumulativeProbability(callPutFlag * d1));
			corr = s1 / lambda * xx;

			UnderlyingAsset und = new UnderlyingAsset(s1, modelVlt, divYield, tipoContrato);
			BSmodel bsOpt = new BSmodel(und, opt);
			bsOpt.run();
			
			mBSprima = bsOpt.getPrima();
			
			rhs = mBSprima + callPutFlag * corr;
			lhs = callPutFlag * (s1 - strike);
			zerror= lhs-rhs;
			nd1= new NormalDistribution().density(d1);
			//nd1=zz*Math.exp(-0.5*d1*d1); //standard Normal prob
			slope=callPutFlag*(1-1/lambda)*xx+1/lambda*(eex*nd1)*1/(modelVlt * Math.sqrt(dayYear));
			s1=s1-zerror/slope;

		} while (Math.abs(zerror) > 0.000001);
		
		double a= callPutFlag*s1/lambda*xx;
		
		switch(optionType){
	       
			case OptionElements.CALL:
				
				if (underlyingNPV >= s1) {
					prima=underlyingNPV-strike;
				}else {
					prima += a*Math.pow((underlyingNPV / s1), lambda);
				}
				break;
			
			case OptionElements.PUT:
				if (underlyingNPV < s1) {
					prima=strike-underlyingNPV;
				}else{
					if (s1==0) {
						prima=-2;
							
					}else {
						
                	prima += a*Math.pow((underlyingNPV / s1), lambda);   
                }
				}
				break;
				
			
			default:
				prima=-1;
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
	
	private WhaleyModel optionWithThisVlt(double xVlt) {
		optionStructure.setImpliedVlt(xVlt);
		WhaleyModel wOption = new WhaleyModel(underlying, optionStructure);
		wOption.run();
        return wOption;
	}
	
	
		
	
}
