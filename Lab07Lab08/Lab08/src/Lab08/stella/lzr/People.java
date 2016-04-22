package Lab08.stella.lzr;

import java.io.Serializable;

public class People implements Serializable {
	private static final long seriaVersion = 7865096419340919344L;
	String name, number;
	
	public String getName() {
		return name;
	}
	
	public String setName(String n) {
		return name = n;
	}
	
	public String getNumber() {
		return number;
	}
	
	public String setNumber(String n) {
		return number = n;
	}
	
}
