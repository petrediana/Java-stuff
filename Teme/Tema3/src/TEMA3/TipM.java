package TEMA3;

public enum TipM {
	MATERIAL (3011),
	MATERIE_PRIMA (300),
	COMBUSTIBIL (3012);

	private int simbol_contabil;

	TipM(int simbol_contabil) {
		this.simbol_contabil = simbol_contabil;
	}
}
