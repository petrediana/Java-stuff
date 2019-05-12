package TEMA4;

import java.io.Serializable;

public class MijlocFix implements Comparable<MijlocFix>, Amortizare, Serializable {
	private int cod;
	private String denumire;
	private double Valoare;
	Categorii categorie;

	public MijlocFix() {}

	public MijlocFix(int cod, String denumire, double valoare, Categorii categorie) {
		this.cod = cod;
		this.denumire = denumire;
		Valoare = valoare;
		this.categorie = categorie;
	}

	public int getCod() {
		return cod;
	}

	public void setCod(int cod) {
		this.cod = cod;
	}

	public String getDenumire() {
		return denumire;
	}

	public void setDenumire(String denumire) {
		this.denumire = denumire;
	}

	public double getValoare() {
		return Valoare;
	}

	public void setValoare(double valoare) {
		Valoare = valoare;
	}

	public Categorii getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorii categorie) {
		this.categorie = categorie;
	}

	@Override
	public String toString() {
		return "MijlocFix{" +
				"cod=" + cod +
				", denumire='" + denumire + '\'' +
				", Valoare=" + Valoare +
				", categorie=" + categorie +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		MijlocFix mijlocFix = (MijlocFix) o;

		return cod == mijlocFix.cod;
	}

	@Override
	public int hashCode() {
		return cod;
	}

	@Override
	public int compareTo(MijlocFix o) {
		return this.cod < o.cod ? -1 : 1;
	}

	@Override
	public double calcul() {
		double amortizareLuna = this.getValoare() / (this.getCategorie().getDurataFunctionare() / 12);

		return amortizareLuna;
	}
}
