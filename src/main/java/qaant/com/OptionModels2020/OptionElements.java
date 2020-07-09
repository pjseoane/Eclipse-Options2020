package qaant.com.OptionModels2020;

public class OptionElements {
	
	public final static char CALL='C';
    public final static char PUT='P';
    
    public final static char EUROPEAN='E';
    public final static char AMERICAN='A';
		
   	private char exerciseType; //American, European
    private char optionType; //Call Put
    private double strike;
    private double daysToExpiration;
    private double optionMktPrice;
    private double interestRate;
    private double impliedVlt;
    private int callPutMultiplier;
    
	
    public OptionElements(char exerciseType, char optionType, double strike, double daysToExpiration,
			double optionMktPrice, double impliedVlt, double interestRate) {
		super();
		
		this.exerciseType = exerciseType;
		this.optionType = optionType;
		this.strike = strike;
		this.daysToExpiration = daysToExpiration;
		this.optionMktPrice = optionMktPrice;
		this.impliedVlt=impliedVlt;
		this.interestRate = interestRate;
		
	}
    
   
	public char getExerciseType() {
		return exerciseType;
	}

	public void setExerciseType(char exerciseType) {
		this.exerciseType = exerciseType;
	}

	public char getOptionType() {
		return optionType;
	}

	public void setOptionType(char optionType) {
		this.optionType = optionType;
	}

	public double getStrike() {
		return strike;
	}

	public void setStrike(double strike) {
		this.strike = strike;
	}

	public double getDaysToExpiration() {
		return daysToExpiration;
	}

	public void setDaysToExpiration(double daysToExpiration) {
		this.daysToExpiration = daysToExpiration;
	}

	public double getOptionMktPrice() {
		return optionMktPrice;
	}

	public void setOptionMktPrice(double optionMktPrice) {
		this.optionMktPrice = optionMktPrice;
	}

	public double getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}

	
	public double getImpliedVlt() {
		return impliedVlt;
	}


	public void setImpliedVlt(double impliedVlt) {
		this.impliedVlt = impliedVlt;
	}
    
    
       
}
