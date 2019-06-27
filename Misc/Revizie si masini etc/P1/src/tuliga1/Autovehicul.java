package tuliga1;

import java.util.Arrays;

public class Autovehicul implements Cloneable, Operatiuni {
	private String numarInmatriculare;
	private int an;
	private TipAutovechicul tipAutovechicul;
	private ITP[] revizii;

	public Autovehicul() {
	}

	public Autovehicul(String numarInmatriculare, int an, TipAutovechicul tipAutovechicul) {
		this.numarInmatriculare = numarInmatriculare;
		this.an = an;
		this.tipAutovechicul = tipAutovechicul;
	}

	public String getNumarInmatriculare() {
		return numarInmatriculare;
	}

	public void setNumarInmatriculare(String numarInmatriculare) {
		this.numarInmatriculare = numarInmatriculare;
	}

	public int getAn() {
		return an;
	}

	public void setAn(int an) {
		this.an = an;
	}

	public TipAutovechicul getTipAutovechicul() {
		return tipAutovechicul;
	}

	public void setTipAutovechicul(TipAutovechicul tipAutovechicul) {
		this.tipAutovechicul = tipAutovechicul;
	}

	public ITP[] getRevizii() {
		return revizii;
	}

	public void setRevizii(ITP[] revizii) {
		this.revizii = revizii;
	}

	@Override
	public String toString() {
		return "Autovehicul{" +
				"numarInmatriculare='" + numarInmatriculare + '\'' +
				", an=" + an +
				", tipAutovechicul=" + tipAutovechicul +
				", revizii=" + Arrays.toString(revizii) +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Autovehicul that = (Autovehicul) o;

		return numarInmatriculare != null ? numarInmatriculare.equals(that.numarInmatriculare) : that.numarInmatriculare == null;
	}

	@Override
	public int hashCode() {
		return numarInmatriculare != null ? numarInmatriculare.hashCode() : 0;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		Autovehicul clona = (Autovehicul)super.clone();

		ITP[] reviziiNoi = new ITP[revizii.length];
		for (int i = 0; i < revizii.length; i++) {
			reviziiNoi[i] = (ITP)revizii[i].clone();
		}

		return clona;
	}

	@Override
	public int inregistrare(ITP obj) {
		ITP[] reviziiNoi = new ITP[revizii.length + 1];
		int lungimeNoua = reviziiNoi.length + 1;

		try {
			for (int i = 0; i < revizii.length; i++)
				reviziiNoi[i] = (ITP) revizii[i].clone();

			reviziiNoi[lungimeNoua] = (ITP)obj.clone();
			this.setRevizii(reviziiNoi);
		}

		catch (Exception ex) {
			ex.printStackTrace();
		}

		return lungimeNoua;
	}
}
