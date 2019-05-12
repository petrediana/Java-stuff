package rezervare;

import java.util.Arrays;

public class Sala {
	private String[][] locuri;
	private int nrLocuri;
	public String ultimulThread;
	public int nrThreaduri;

	public Sala(int nrLocuri) {
		this.nrLocuri = nrLocuri;
		locuri = new String[nrLocuri][nrLocuri];
	}

	public String[][] getLocuri() {
		return locuri;
	}

	public String getLoc(int i, int j) {
		return this.locuri[i][j];
	}

	public void setLocuri(String[][] locuri) {
		this.locuri = locuri;
	}

	public void setLoc(int i, int j, String nume) {
		this.locuri[i][j] = nume;
	}

	public int getNrLocuri() {
		return nrLocuri;
	}

	public void setNrLocuri(int nrLocuri) {
		this.nrLocuri = nrLocuri;
	}

	@Override
	public String toString() {
		StringBuilder rezultat = new StringBuilder();

		for (int i = 0; i < nrLocuri; i++) {
			for (int j = 0; j < nrLocuri; j++) {
				rezultat.append(locuri[i][j]).append(" ");
			}
			rezultat.append("\n");
		}

		return rezultat.toString();
	}

	public void inc() {
		this.nrThreaduri++;
	}

	public synchronized void dec() {
		this.nrThreaduri--;
		notifyAll();
	}
}
