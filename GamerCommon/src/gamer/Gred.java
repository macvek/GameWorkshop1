package gamer;

import java.util.ArrayList;
import java.util.List;


public class Gred {
	
	private String gredStr = "";
	String[][] array = new String[7][7];
	private List<Client> clients;		
	private List<Apple> apples = new ArrayList<>();
	
	

	public Gred(int i5) {
		
		
	}

	private void updateApples() {
		for (Apple apple : apples) {
			array[apple.getY()][apple.getX()] = "J";
		}
		
	}

	private void cleanMap() {
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
			array[0][i] = "x";
			array[6][i] = "x";
		}
	}

	public String getMap() {
		cleanMap();
		updateApples();
		
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
//		String cord = client.getCord();
		return " ";
	}
	
	
	public String create() {
		Client client = new Client();
		clients.add(client);
		return Messages.OK;
	}
	
	public String apple() {
		return Messages.NOTIMPLEMENTED;
	}
	
	public String list() {
		return Messages.NOTIMPLEMENTED;
	}
	
	public String move(String what, String where) {
		return Messages.NOTIMPLEMENTED;
	}

	public List<Apple> getApples() {
		return apples;
	}
	

	
	
}
