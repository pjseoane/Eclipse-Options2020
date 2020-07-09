package qaant.com.OptionModels2020;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        
        
        UnderlyingAsset underlying= new UnderlyingAsset("name","ticker","ISIN", 64000.0, 0.40, 0.00, 'F'); //Stock or Future
        OptionElements option = new OptionElements('A', 'P', 64000.0,84.0, 0, .40,.35);
        
        BSmodel bsOption = new BSmodel(underlying,option);
        bsOption.run();
        
        System.out.println( "Hello World!" );
        System.out.println( "Model Name : "+BSmodel.getModelName());
        System.out.println( "Class Canonical Name: "+BSmodel.class.getCanonicalName());
        System.out.println( "Class Name: "+BSmodel.class.getName());
        System.out.println( "Class Simple Name: "+BSmodel.class.getSimpleName());
        System.out.println( "Class hash code: "+BSmodel.class.hashCode());
        System.out.println( "Class toString: "+BSmodel.class.toString());
        
        System.out.println( "Div Yield	: "+underlying.getUnderlyingDividendYield());
        System.out.println( "Tipo Contrato: "+underlying.getTipoContrato());
       
        System.out.println( "Prima		: "+bsOption.getPrima());
        System.out.println( "Delta		: "+bsOption.getDelta());
        System.out.println( "Gamma		: "+bsOption.getGamma());
        System.out.println( "Vega		: "+bsOption.getVega());
        System.out.println( "Theta		: "+bsOption.getTheta());
        System.out.println( "Rho		: "+bsOption.getRho());
        
        System.out.println( "Implied Vlt: "+bsOption.getImpliedVlt());
       // System.out.println( "Implied Vlt iv2: "+bsOption.iv2());
        
        double iVltCalculada;
        double optMktPrice=4700;
        iVltCalculada=bsOption.getImpliedVlt(optMktPrice);
        System.out.println( "Implied Vlt for mkt: "+optMktPrice+"--> "+ iVltCalculada);
        
     
        bsOption.run();
        System.out.println( "Prima con nueva Vlt: "+bsOption.getPrima());
        
        
        System.out.println("***** Whaley *****");
        option.setImpliedVlt(.40);
        WhaleyModel Woption= new WhaleyModel(underlying, option);
        Woption.run();
        System.out.println( "Exercise Type: "+option.getExerciseType());
        
        System.out.println( "Prima Call		: "+Woption.getPrima());
        System.out.println( "Delta		: "+Woption.getDelta());
        System.out.println( "Gamma		: "+Woption.getGamma());
        System.out.println( "Vega		: "+Woption.getVega());
        System.out.println( "Theta		: "+Woption.getTheta());
        System.out.println( "Rho		: "+Woption.getRho());
              	
        option.setOptionType('P');	
        Woption.run();
       
        System.out.println("Prima Put2		: "+Woption.getPrima());
        System.out.println( "Delta		: "+Woption.getDelta());
        System.out.println( "Gamma		: "+Woption.getGamma());
        System.out.println( "Vega		: "+Woption.getVega());
        System.out.println( "Theta		: "+Woption.getTheta());
        System.out.println( "Rho		: "+Woption.getRho());
        
        optMktPrice=4500.0;
        iVltCalculada=Woption.getImpliedVlt(optMktPrice);
              
        System.out.println( "Implied Vlt for mkt: "+optMktPrice+"--> "+ iVltCalculada);
                
        Woption.run();
        System.out.println( "Prima con nueva Vlt: "+Woption.getPrima());
        
        System.out.println("***** Any Model *****");
        AnyModel any = new AnyModel(underlying,option);
        any.runBS();
        
        double anyPrimaBS= any.getPrima();
        any.runW();
        double anyPrimaW=any.getPrima();
        
        System.out.println( "Prima BS y W: "+anyPrimaBS+" "+anyPrimaW);
        
    }
}
