package p01;

import java.util.Date;

public class Depozit extends Cont implements Cloneable {
	private double dobanda;
	private int perioada;

	public Depozit() {
	}

	public Depozit(String iban, double disponibil, Date data, double dobanda, int perioada) throws Exception {
		super(iban, disponibil, data);
		this.dobanda = dobanda;
		this.perioada = perioada;
	}

	public double getDobanda() {
		return dobanda;
	}

	public void setDobanda(double dobanda) {
		this.dobanda = dobanda;
	}

	public int getPerioada() {
		return perioada;
	}

	public void setPerioada(int perioada) {
		this.perioada = perioada;
	}

	@Override
	public String toString() {
		return "Depozit{" +
				"dobanda=" + dobanda +
				", perioada=" + perioada +
				'}' + super.toString();
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return (Depozit) super.clone();
	}
}
