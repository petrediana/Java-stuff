package TEMA4;

public enum Categorii {
	TERENURI (211, 100),
	CONSTRUCTII (212, 100),
	ECHIPAMENTE (2131, 10),
	MIJLOACE_TRANSPORT (2133, 6),
	MOBILIER (214, 10);

	private int simbolContabil;
	private int durataFunctionare;

	Categorii(int simbolContabil, int durataFunctionare) {
		this.simbolContabil = simbolContabil;
		this.durataFunctionare = durataFunctionare;
	}

	public int getDurataFunctionare() {
		return durataFunctionare;
	}
}
