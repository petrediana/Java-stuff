package tema6;

import java.util.ArrayList;
import java.util.List;

public class NotaContabila {
	private int numar_nota;
	private List<Operatiune> operatiuni = new ArrayList<>();

	public NotaContabila() {}

	public NotaContabila(int numar_nota, List<Operatiune> operatiuni) {
		this.numar_nota = numar_nota;
		this.operatiuni = operatiuni;
	}

	public int getNumar_nota() {
		return numar_nota;
	}

	public void setNumar_nota(int numar_nota) {
		this.numar_nota = numar_nota;
	}

	public List<Operatiune> getOperatiuni() {
		return operatiuni;
	}

	public void setOperatiuni(List<Operatiune> operatiuni) {
		this.operatiuni = operatiuni;
	}

	@Override
	public String toString() {
		return "NotaContabila{" +
				"numar_nota=" + numar_nota +
				", operatiuni=" + operatiuni +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		NotaContabila that = (NotaContabila) o;

		return numar_nota == that.numar_nota;
	}

	@Override
	public int hashCode() {
		return numar_nota;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		NotaContabila clona = (NotaContabila)super.clone();
		List<Operatiune> listaNoua = new ArrayList<>();

		for(Operatiune o: operatiuni){
			listaNoua.add((Operatiune)o.clone());
		}

		clona.setOperatiuni(listaNoua);
		return clona;
	}
}
