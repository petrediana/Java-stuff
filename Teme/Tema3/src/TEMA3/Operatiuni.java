package TEMA3;

import java.util.List;
import java.util.Map;

public interface Operatiuni {

	List<MPMat> sort1();

	List<MPMat> sort2();

	List<MPMat> filtrare(TipM tip);

	Map<TipM, Double> valoareMateriale();
}
