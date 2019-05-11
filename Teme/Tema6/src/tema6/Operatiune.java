package tema6;

public class Operatiune {
	private int simbol_cont_debitor;
	private int simbol_cont_creditor;
	private double suma;

	public Operatiune() {}

	public Operatiune(int simbol_cont_debitor, int simbol_cont_creditor, double suma) {
		this.simbol_cont_debitor = simbol_cont_debitor;
		this.simbol_cont_creditor = simbol_cont_creditor;
		this.suma = suma;
	}

	public int getSimbol_cont_debitor() {
		return simbol_cont_debitor;
	}

	public int getSimbol_cont_creditor() {
		return simbol_cont_creditor;
	}

	public double getSuma() {
		return suma;
	}

	public void setSimbol_cont_debitor(int simbol_cont_debitor) {
		this.simbol_cont_debitor = simbol_cont_debitor;
	}

	public void setSimbol_cont_creditor(int simbol_cont_creditor) {
		this.simbol_cont_creditor = simbol_cont_creditor;
	}

	public void setSuma(double suma) {
		this.suma = suma;
	}

	@Override
	public String toString() {
		return "Operatiune{" +
				"simbol_cont_debitor=" + simbol_cont_debitor +
				", simbol_cont_creditor=" + simbol_cont_creditor +
				", suma=" + suma +
				'}';
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}
