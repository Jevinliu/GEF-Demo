package wbhgef.model;

public class Enterprise extends Node {

	private String address;
	private int capital;
	public static final String PROPERTY_CAPITAL = "EnterpriseCapital";

	public void setAddress(String address) {
		this.address = address;
	}

	public void setCapital(int capital) {
		this.capital = capital;
	}

	public String getAddress() {
		return this.address;
	}

	public int getCapital() {
		return this.capital;
	}
}
