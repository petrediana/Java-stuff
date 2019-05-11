package tema6;

import java.io.Serializable;

public class Cont implements Comparable<Cont>, Serializable {
	private int simbol_cont;
	private String denumire;
	private double rulaj_debitor;
	private double rulaj_creditor;

	public Cont(){}

	public Cont(int simbol_cont, String denumire, double rulaj_debitor, double rulaj_creditor) {
		this.simbol_cont = simbol_cont;
		this.denumire = denumire;
		this.rulaj_debitor = rulaj_debitor;
		this.rulaj_creditor = rulaj_creditor;
	}

	public int getSimbol_cont() {
		return simbol_cont;
	}

	public String getDenumire() {
		return denumire;
	}

	public double getRulaj_debitor() {
		return rulaj_debitor;
	}

	public double getRulaj_creditor() {
		return rulaj_creditor;
	}

	public void setSimbol_cont(int simbol_cont) {
		this.simbol_cont = simbol_cont;
	}

	public void setDenumire(String denumire) {
		this.denumire = denumire;
	}

	public void setRulaj_debitor(double rulaj_debitor) {
		this.rulaj_debitor = rulaj_debitor;
	}

	public void setRulaj_creditor(double rulaj_creditor) {
		this.rulaj_creditor = rulaj_creditor;
	}

	@Override
	public String toString() {
		return "Cont{" +
				"simbol_cont=" + simbol_cont +
				", denumire='" + denumire + '\'' +
				", rulaj_debitor=" + rulaj_debitor +
				", rulaj_creditor=" + rulaj_creditor +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Cont cont = (Cont) o;

		return simbol_cont == cont.simbol_cont;
	}

	@Override
	public int hashCode() {
		return simbol_cont;
	}


	@Override
	public int compareTo(Cont o) {
		return simbol_cont < o.simbol_cont ? -1 : 1;
	}
}
