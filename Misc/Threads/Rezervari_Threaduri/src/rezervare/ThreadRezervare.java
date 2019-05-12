package rezervare;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class ThreadRezervare extends Thread {
	private List<Rezervare> rezervari = new ArrayList<>();
	private String numeFisier;
	private Sala sala;

	public ThreadRezervare(String numeFisier, Sala sala) {
		this.numeFisier = numeFisier;
		this.sala = sala;
	}

	public void citireFisierTxt(String numeFisier, Sala sala) {
		try (BufferedReader in = new BufferedReader(new FileReader(numeFisier))) {
			in.lines().forEach(linie -> {
				String[] t = linie.split(",");
				String numeRezervare = t[0].trim();
				int nrLocuriRezervate = Integer.parseInt(t[1].trim());

				// rezervare secventiala!
				Rezervare rezervare = new Rezervare(sala, numeRezervare, nrLocuriRezervate);
				//rezervare.executaRezervare();

				rezervari.add(rezervare);

			});
		} catch (Exception ex) {
			System.err.println(ex.getMessage());
		}
	}

	@Override
	public void run() {
		// execut rezervare
		String numeThread = Thread.currentThread().getName();
		this.sala.inc();
		citireFisierTxt(numeFisier, sala);
		for (Rezervare rezervare : rezervari) {
			synchronized (sala) {
				if (sala.ultimulThread != null) {
					if (sala.ultimulThread.equals(numeThread) && sala.nrThreaduri > 1) {
						try {
							sala.wait();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
				rezervare.executaRezervare();
				sala.notifyAll();
			}
		}
		this.sala.dec();
	}
}
