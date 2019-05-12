package TEMA3;

import java.io.Serializable;

public class MPMat implements Comparable<MPMat>, Serializable {
	private int cod;
	private String denumire;
	private double valoare;
	private TipM tip;

	public MPMat() {}

	public MPMat(int cod, String denumire, double valoare, TipM tip) {
		this.cod = cod;
		this.denumire = denumire;
		this.valoare = valoare;
		this.tip = tip;
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
		return valoare;
	}

	public void setValoare(double valoare) {
		this.valoare = valoare;
	}

	public TipM getTip() {
		return tip;
	}

	public void setTip(TipM tip) {
		this.tip = tip;
	}

	@Override
	public String toString() {
		return "MPMat{" +
				"cod=" + cod +
				", denumire='" + denumire + '\'' +
				", valoare=" + valoare +
				", tip=" + tip +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		MPMat mpMat = (MPMat) o;

		return cod == mpMat.cod;
	}

	@Override
	public int hashCode() {
		return cod;
	}


	@Override
	public int compareTo(MPMat o) {
		return this.cod < o.cod ? -1 : 1;
	}
}
