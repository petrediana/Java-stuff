import java.util.Random;

import static java.lang.Thread.sleep;

public class MyThread implements Runnable {
	private String name;
	private Random random = new Random();

	public static synchronized void myPrint(String name) {
		for(int i = 1; i <= 5; i++) {
			System.out.println("Hello, I'm " + name + ", print count: " + i);
		}
	}

	public MyThread(String name) {
		this.name = name;
	}

	@Override
	public void run() {
//		Main.myPrint(this.name);
		myPrint(this.name);
	}

}
