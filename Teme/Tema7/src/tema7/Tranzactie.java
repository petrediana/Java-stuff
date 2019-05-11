package tema7;

public class Tranzactie implements Cloneable {
	private int cod_stoc;
	private char tip_tranzactie;
	private double cantitate;

	public Tranzactie() {}

	public Tranzactie(int cod_stoc, char tip_tranzactie, double cantitate) {
		this.cod_stoc = cod_stoc;
		this.tip_tranzactie = tip_tranzactie;
		this.cantitate = cantitate;
	}

	public int getCod_stoc() {
		return cod_stoc;
	}

	public void setCod_stoc(int cod_stoc) {
		this.cod_stoc = cod_stoc;
	}

	public char getTip_tranzactie() {
		return tip_tranzactie;
	}

	public void setTip_tranzactie(char tip_tranzactie) {
		this.tip_tranzactie = tip_tranzactie;
	}

	public double getCantitate() {
		return cantitate;
	}

	public void setCantitate(double cantitate) {
		this.cantitate = cantitate;
	}

	@Override
	public String toString() {
		return "Tranzactie{" +
				"cod_stoc=" + cod_stoc +
				", tip_tranzactie=" + tip_tranzactie +
				", cantitate=" + cantitate +
				'}';
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}
