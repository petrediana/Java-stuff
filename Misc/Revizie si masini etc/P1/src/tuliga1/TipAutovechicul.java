package tuliga1;

public enum TipAutovechicul {
	AUTOTURISM_BENZINA(1),
	AUTOTURISM_DIESEL(2),
	SUV(3),
	UTILITARA(4);

	private int codAutovehicul;

	TipAutovechicul(int codAutovehicul) {
		this.codAutovehicul = codAutovehicul;
	}
}
