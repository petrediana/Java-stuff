package p01;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main {
	public static void main(String[] args) {
		List<Depozit> depozite = new ArrayList<>();


		Date data = new Date(2000, 10, 14);
		try {
			depozite.add(new Depozit("Iban", 1000, data, 0.03, 15));


			Client c1 = new Client(100, "numeC");
			c1.setListaDepozite(depozite);

			Client c2 = (Client) c1.clone();

			System.out.println(c1);
			System.out.println(c2 + "\n\n");

			c1.setNume("aaaa");
			c1.setListaDepozite(new ArrayList<>());
			depozite.add(new Depozit());

			c1.setListaDepozite(depozite);

			System.out.println(c1);
			System.out.println(c2);


		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
