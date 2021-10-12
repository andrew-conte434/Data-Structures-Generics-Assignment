package HW17;

public class Depositor {
	String ssn;
	Name name;
	
	//no-arg constructor
	public Depositor(){
		this.name = new Name();
		this.ssn = "000000000";
	}
	
	//parameterized constructor
	public Depositor(String first, String last, String ssn){
		this.name = new Name(first, last);
		this.ssn = ssn;
	}
	
	//copy constructor
	public Depositor(Depositor depositor) {
		this.name = depositor.getName();
		this.ssn = depositor.getSSN();
	}
	
	//returns name
	public Name getName() {
		return name;
	}
	
	//return ssn
	public String getSSN() {
		return ssn;
	}
	
	//Overloads equals method
	public boolean equals(Depositor depositor) {
		if(depositor.name.equals(this.getName()) && depositor.getSSN().equals(this.ssn)) {
			return true;
		}
		return false;
	}
	
	@Override
	public String toString() {
		return name.toString() + String.format("%15s", ssn);
	}
}
