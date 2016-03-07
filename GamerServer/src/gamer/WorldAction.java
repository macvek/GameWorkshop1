package gamer;

import java.util.Random;

public class WorldAction implements Runnable{

	private Gred gred;
	
	@Override
	public void run() {
		for(;;) {
			try {
				logic();
				Thread.sleep(1000);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	private void logic() throws Exception {
		updateApples();
	}

	private void updateApples() {
		synchronized(gred) {
			if (gred.getApples().size() < 2) {
				createApple();
			}
			else {
				gred.getApples().clear();
			}
		}
		
	}

	private void createApple() {
		Random random = new Random();
		
		int x = random.nextInt(5)+1;
		int y = random.nextInt(5)+1;
		
		Apple apple = new Apple(x,y);
		
		gred.getApples().add(apple);
		
	}

	public void setGred(Gred gred) {
		this.gred = gred;
	}
	
	
	
}
