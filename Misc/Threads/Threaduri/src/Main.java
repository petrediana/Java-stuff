import java.util.ArrayList;

public class Main {
	public static int x = 0;

	public static synchronized void myPrint(String name) {
		for(int i = 1; i <= 5; i++) {
			System.out.println("Hello, I'm " + name + ", print count: " + i);
		}
	}

	public static void main(String[] args) {
		System.out.println(":*");

		ArrayList<Thread> myThreads = new ArrayList<>();
		for (int i = 0; i < 3; i++) {
			Thread myThread = new Thread(new MyThread("Thread #" + (i + 1)));
			myThreads.add(myThread);
			myThread.start();

		}

		for (Thread t : myThreads) {
			try {
				t.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		System.out.println("X = " + x);


		class MySecondThread extends Thread {
			private Object reference;
			private String name;

			MySecondThread(Object reference, String name) {
				this.reference = reference;
				this.name = name;
			}

			private void myPrint() {
				for (int i = 1; i <= 5; i++) {
					System.out.println("Hello, I'm " + name + ", print count: " + i);
				}
			}

			@Override
			public void run() {
				synchronized (reference) {
					myPrint();
				}
			}
		}

		System.out.println("\n\n\n\n");

		Object mainReference = new Object();
		Thread t1 = new MySecondThread(mainReference, "Thread #1");
		Thread t2 = new MySecondThread(mainReference, "Thread #2");
		t1.start();
		t2.start();
		try {
			t1.join();
			t2.join();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}



}
