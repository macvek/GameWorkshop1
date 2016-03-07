package gamer;

import java.util.ArrayList;

public class Gred {
	
	private String gredStr = "";
	String[][] array = new String[7][7];
	private Client client;
	
	

	public Gred(int i5) {
		client = new Client(1,1);
		for (int i = 0; i < 7; i++) {
			for (int j = 0; j < 7; j++) {
				array[i][j] = " ";
			}
		}
		for (int i = 0; i < 7; i++) {
			array[i][0] = "x";
			array[i][6] = "x";
		}
		for (int i = 0; i < 7; i++) {
			array[1][i] = "x";
			array[5][i] = "x";
		}
	}

	public String getMap() {
		for (int i = 0; i < 7; i++) {
			for (int j = 0; j < 7; j++) {
				gredStr += array[j][i];
			}
			gredStr += "\n";
		}
		return gredStr;
	}

	public String getClientCord() {
		return "(x,y)";
	}

	public String moveClient(String where) {
		String cord = client.getCord();
		return " ";
	}
}
