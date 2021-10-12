package HW17;

public class Name implements Comparable {
	
	String first;
	String last;
	
	//no-arg constructor
	public Name(){
		this.first = "John";
		this.last = "Doe";
	}
	
	//parameterized constructor
	public Name(String first, String last){
		this.first = first;
		this.last = last;
	}
	
	//copy constructor
	public Name(Name name) {
		this.first = name.getFirst();
		this.last = name.getLast();
	}
	
	//returns first name
	public String getFirst() {
		return first;
	}
	
	//returns last name
	public String getLast() {
		return last;
	}
	
	//Overloads equals() method
	//returns true if first and last name are equal to given Name object
	public boolean equals(Name name) {
		if(name.getFirst().equals(this.first) && name.getLast().equals(this.last)) {
			return true;
		}
		return false;
	}
	
	@Override
	public int compareTo(Object o) {
		if(this.getLast().compareTo(((Name) o).getLast()) == 0) {
			return this.getFirst().compareTo(((Name) o).getFirst());
		}
		return getLast().compareTo(((Name) o).getLast());
	}
	@Override 
	public String toString() {
		return String.format("%10s %10s", first, last);
	}

}
