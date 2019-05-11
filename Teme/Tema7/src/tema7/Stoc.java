package tema7;

import java.io.Serializable;

public class Stoc implements Comparable<Stoc>, Serializable {
	private int cod_stoc;
	private String denumire;
	private double total_intrari;
	private double total_iesiri;
	private String um;

	public Stoc() {}

	public Stoc(int cod_stoc, String denumire, double total_intrari, double total_iesiri, String um) {
		this.cod_stoc = cod_stoc;
		this.denumire = denumire;
		this.total_intrari = total_intrari;
		this.total_iesiri = total_iesiri;
		this.um = um;
	}

	public int getCod_stoc() {
		return cod_stoc;
	}

	public void setCod_stoc(int cod_stoc) {
		this.cod_stoc = cod_stoc;
	}

	public String getDenumire() {
		return denumire;
	}

	public void setDenumire(String denumire) {
		this.denumire = denumire;
	}

	public double getTotal_intrari() {
		return total_intrari;
	}

	public void setTotal_intrari(double total_intrari) {
		this.total_intrari = total_intrari;
	}

	public double getTotal_iesiri() {
		return total_iesiri;
	}

	public void setTotal_iesiri(double total_iesiri) {
		this.total_iesiri = total_iesiri;
	}

	public String getUm() {
		return um;
	}

	public void setUm(String um) {
		this.um = um;
	}

	@Override
	public String toString() {
		return "Stoc{" +
				"cod_stoc=" + cod_stoc +
				", denumire='" + denumire + '\'' +
				", total_intrari=" + total_intrari +
				", total_iesiri=" + total_iesiri +
				", um='" + um + '\'' +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Stoc stoc = (Stoc) o;

		return cod_stoc == stoc.cod_stoc;
	}

	@Override
	public int hashCode() {
		return cod_stoc;
	}

	@Override
	public int compareTo(Stoc o) {
		return this.cod_stoc < o.cod_stoc ? -1 : 1;
	}
}
