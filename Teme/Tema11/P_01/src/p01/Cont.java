package p01;

import java.util.Date;

public abstract class Cont {
	private String iban;
	private double disponibil;
	private Date data;

	public Cont() {
	}

	public Cont(String iban, double disponibil, Date data) throws Exception {
		this.iban = iban;

		if (disponibil >= 0)
			this.disponibil = disponibil;
		else
			throw new Exception("Disponibil nu poate fi < 0 !");

		this.data = data;
	}

	public String getIban() {
		return iban;
	}

	public void setIban(String iban) {
		this.iban = iban;
	}

	public double getDisponibil() {
		return disponibil;
	}

	public void setDisponibil(double disponibil) throws Exception {
		if (disponibil >= 0)
			this.disponibil = disponibil;
		else
			throw new Exception("Disponibil nu poate fi < 0 !");
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "Cont{" +
				"iban='" + iban + '\'' +
				", disponibil=" + disponibil +
				", data=" + data +
				'}';
	}
}
