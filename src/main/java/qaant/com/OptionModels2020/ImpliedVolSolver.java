package qaant.com.OptionModels2020;


import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.analysis.solvers.BisectionSolver;

public class ImpliedVolSolver {

		private UnderlyingAsset und;
		private OptionElements opt;
		private double precision=0.000001;
		private double modelValue;
		
		public ImpliedVolSolver(UnderlyingAsset und, OptionElements opt, double modelValue) {
			super();
			this.und=und;
			this.opt=opt;
			this.modelValue=modelValue;
		}
		
		public double bisection(UnivariateFunction f) {
			double impliedVol=opt.getImpliedVlt();
			double minVol=Double.MIN_VALUE;
			double maxVol=impliedVol*5;
			
			double optMktPrice=opt.getOptionMktPrice();
			if (optMktPrice==0) {
				return impliedVol;
			}else {
			
			double dif= optMktPrice-modelValue;
			// si dif es >0 hay que hacer subir la vlt, sino hay que bajarla.
			
			if (dif>0) {
				minVol=impliedVol;
				maxVol=impliedVol*5.0;
				}else{
					minVol=impliedVol/5;
					maxVol=impliedVol;
			}
						
		    BisectionSolver solver= new BisectionSolver(precision); //precision()
		    impliedVol=solver.solve(100, f, minVol,maxVol); //max iteration, funcion,min, max
		    return impliedVol;
			}
		}
		
}
