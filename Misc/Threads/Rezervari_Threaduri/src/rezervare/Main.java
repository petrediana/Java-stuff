package rezervare;

public class Main {

	public static void main(String[] args) {
		Main app = new Main();
		Sala sala = new Sala(5);
		ThreadRezervare fir1 = new ThreadRezervare("f1", sala);
		ThreadRezervare fir2 = new ThreadRezervare("f2", sala);

		try {
			fir1.start();
			fir2.start();

			fir1.join();
			fir2.join();

			System.out.println(sala);
		}

		catch (Exception ex) {
			System.err.println(ex);
		}

	}
}
