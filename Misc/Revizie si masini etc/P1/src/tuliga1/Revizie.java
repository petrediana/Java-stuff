package tuliga1;

public abstract class Revizie {
	private int km;
	private double taxa;
	private String firma;

	public Revizie() {
	}

	public Revizie(int km, double taxa, String firma) throws Exception {
		if (km >= 0)
			this.km = km;
		else
			throw new Exception("Km negativ!!");
		this.taxa = taxa;
		this.firma = firma;
	}

	public int getKm() {
		return km;
	}

	public void setKm(int km) throws Exception {
		if (km >= 0)
			this.km = km;
		else
			throw new Exception("Km negativ!!");
	}

	public double getTaxa() {
		return taxa;
	}

	public void setTaxa(double taxa) {
		this.taxa = taxa;
	}

	public String getFirma() {
		return firma;
	}

	public void setFirma(String firma) {
		this.firma = firma;
	}

	@Override
	public String toString() {
		return "Revizie{" +
				"km=" + km +
				", taxa=" + taxa +
				", firma='" + firma + '\'' +
				'}';
	}


}
