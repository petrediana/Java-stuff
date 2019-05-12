package TEMA4;

import java.util.List;
import java.util.Map;

public interface Operatiuni {

	List<MijlocFix> sort1();

	List<MijlocFix> sort2();

	List<MijlocFix> filtrare(Categorii categorie);

	Map<Categorii, Double> valoareMijloaceFixe();

}
