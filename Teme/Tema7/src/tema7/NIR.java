package tema7;

import java.util.ArrayList;
import java.util.List;

public class NIR implements Cloneable{
	private int numar_nota;
	private List<Tranzactie> tranzactii = new ArrayList<>();

	public NIR(){}

	public NIR(int numar_nota, List<Tranzactie> tranzactii) {
		this.numar_nota = numar_nota;
		this.tranzactii = tranzactii;
	}

	public int getNumar_nota() {
		return numar_nota;
	}

	public void setNumar_nota(int numar_nota) {
		this.numar_nota = numar_nota;
	}

	public List<Tranzactie> getTranzactii() {
		return tranzactii;
	}

	public void setTranzactii(List<Tranzactie> tranzactii) {
		this.tranzactii = tranzactii;
	}

	@Override
	public String toString() {
		return "NIR{" +
				"numar_nota=" + numar_nota +
				", tranzactii=" + tranzactii +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		NIR nir = (NIR) o;

		return numar_nota == nir.numar_nota;
	}

	@Override
	public int hashCode() {
		return numar_nota;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		NIR clona = (NIR) super.clone();
		List<Tranzactie> listaNoua = new ArrayList<>();

		for(Tranzactie tr : tranzactii){
			listaNoua.add((Tranzactie) tr.clone());
		}

		clona.setTranzactii(listaNoua);
		return clona;
	}
}
