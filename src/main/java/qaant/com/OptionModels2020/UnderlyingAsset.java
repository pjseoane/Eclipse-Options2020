package qaant.com.OptionModels2020;

public class UnderlyingAsset {
	
    public static final  char STOCK='S';
    public static final  char FUTURES='F';

   
    private String underlyingName;
    private String underlyingTicker;
    private String underlyingISIN;
    private double underlyingValue;
    private double underlyingHistVolatility;
    private double underlyingDividendYield;
    private char tipoContrato;
    
    public UnderlyingAsset(double underlyingValue, double underlyingHistVolatility, double underlyingDividendYield,
			char tipoContrato) {
    		this("Name","Ticker","ISIN",underlyingValue, underlyingHistVolatility, underlyingDividendYield,tipoContrato);
    }
    
	public UnderlyingAsset(String underlyingName, String underlyingTicker, String underlyingISIN,
			double underlyingValue, double underlyingHistVolatility, double underlyingDividendYield,
			char tipoContrato) {
		super();
		this.underlyingName = underlyingName;
		this.underlyingTicker = underlyingTicker;
		this.underlyingISIN = underlyingISIN;
		this.underlyingValue = underlyingValue;
		this.underlyingHistVolatility = underlyingHistVolatility;
		this.underlyingDividendYield = underlyingDividendYield;
		this.tipoContrato = tipoContrato;
	}


	public String getUnderlyingName() {
		return underlyingName;
	}


	public void setUnderlyingName(String underlyingName) {
		this.underlyingName = underlyingName;
	}


	public String getUnderlyingTicker() {
		return underlyingTicker;
	}


	public void setUnderlyingTicker(String underlyingTicker) {
		this.underlyingTicker = underlyingTicker;
	}


	public String getUnderlyingISIN() {
		return underlyingISIN;
	}


	public void setUnderlyingISIN(String underlyingISIN) {
		this.underlyingISIN = underlyingISIN;
	}


	public double getUnderlyingValue() {
		return underlyingValue;
	}


	public void setUnderlyingValue(double underlyingValue) {
		this.underlyingValue = underlyingValue;
	}


	public double getUnderlyingHistVolatility() {
		return underlyingHistVolatility;
	}


	public void setUnderlyingHistVolatility(double underlyingHistVolatility) {
		this.underlyingHistVolatility = underlyingHistVolatility;
	}


	public double getUnderlyingDividendYield() {
		return underlyingDividendYield;
	}


	public void setUnderlyingDividendYield(double underlyingDividendYield) {
		this.underlyingDividendYield = underlyingDividendYield;
	}


	public char getTipoContrato() {
		return tipoContrato;
	}


	public void setTipoContrato(char tipoContrato) {
		this.tipoContrato = tipoContrato;
	}
	
	
}
