package p01;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Client implements Cloneable, Operatiuni{
	private long codClient;
	private String nume;
	private List<Depozit> listaDepozite = new ArrayList<>();

	public Client() {
	}

	public Client(long codClient, String nume) {
		this.codClient = codClient;
		this.nume = nume;
	}

	public long getCodClient() {
		return codClient;
	}

	public void setCodClient(long codClient) {
		this.codClient = codClient;
	}

	public String getNume() {
		return nume;
	}

	public void setNume(String nume) {
		this.nume = nume;
	}

	public List<Depozit> getListaDepozite() {
		return listaDepozite;
	}

	public void setListaDepozite(List<Depozit> listaDepozite) {
		this.listaDepozite = listaDepozite;
	}

	@Override
	public String toString() {
		return "Client{" +
				"codClient=" + codClient +
				", nume='" + nume + '\'' +
				", listaDepozite=" + listaDepozite +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Client client = (Client) o;

		return codClient == client.codClient;
	}

	@Override
	public int hashCode() {
		return (int) (codClient ^ (codClient >>> 32));
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		Client clona = (Client) super.clone();

		List<Depozit> listaNoua = new ArrayList<>();
		for (Depozit dep : listaDepozite) {
			listaNoua.add((Depozit) dep.clone());
		}

		clona.setListaDepozite(listaNoua);
		return clona;
	}

	@Override
	public double calculDobanda(String iban) {

		Depozit dep = new Depozit();
		dep.setIban(iban);

		int gasit = listaDepozite.indexOf(dep);

		if (gasit == -1) {
			return  -1;
		}
		else {
			dep = listaDepozite.get(gasit);
			Date dataCurenta = new Date();

			long nrZile = (dataCurenta.getTime() - dep.getData().getTime()) / (1000 * 60 * 60 * 24);
			double adaos_calculat = dep.getDisponibil() * dep.getDobanda() * nrZile / (360 * 100);

			return (dep.getDisponibil() + adaos_calculat);
		}
	}
}
