package rezervare;

public class Rezervare {
	private Sala sala;
	private String numeRezervare;
	private int nrLocuriRezervate;

	public Rezervare(Sala sala, String numeRezervare, int nrLocuriRezervate) {
		this.sala = sala;
		this.numeRezervare = numeRezervare;
		this.nrLocuriRezervate = nrLocuriRezervate;
	}

	public void executaRezervare() {
		int copie = this.nrLocuriRezervate;
		for (int i = 0; i < sala.getNrLocuri(); i++){
			for (int j = 0; j < sala.getNrLocuri(); j++){
				if (sala.getLoc(i, j) == null && copie > 0){
					sala.setLoc(i, j, numeRezervare);
					copie --;
				}
			}
		}
	}
}
