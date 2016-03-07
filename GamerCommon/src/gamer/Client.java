package gamer;

import java.util.Random;

public class Client {
	
	private int name;
	int i,j;

	public Client(int i, int j) {
		
	}

	public Client() {
		i=1+new Random().nextInt(5);
		j=1+new Random().nextInt(5);		
		setName(new Random().nextInt(10));
	}

	public String getCord() {
		return "(1,1)";
	}

	public int getName() {
		return name;
	}

	public void setName(int k) {
		this.name = k;
	}

}
