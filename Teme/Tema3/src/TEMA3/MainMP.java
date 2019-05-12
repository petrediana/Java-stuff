package TEMA3;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class MainMP implements IO, Operatiuni {
	private static List<MPMat> lista = new ArrayList<>();
	private final static String numeFisierTxt = "MateriiPrime.csv";
	private final static String numeFisierBinar = "Temp.dat";

	private static List<MPMat> listaSincron = Collections.synchronizedList(lista);

	public static void main(String[] args) {
		MainMP app = new MainMP();

		try {
			System.out.println("\n---Citire din fisier txt---\n");
			app.citire(numeFisierTxt);
			app.afisareLista(lista);

			System.out.println("\n---Scriere + citire din fisier binar---\n");
			app.salvare(numeFisierBinar);
			app.restaurare(numeFisierBinar);
			app.afisareLista(lista);

			System.out.println("\n---Afisare lista sortata dupa COD---\n");
			List<MPMat> listaSortID = app.sort1();
			app.afisareLista(listaSortID);

			System.out.println("\n---Afisare lista sortata dupa VALOARE---\n");
			List<MPMat> listaSortVal = app.sort2();
			app.afisareLista(listaSortVal);

			System.out.println("\n---Afisare lista filtrata dupa tip = COMBUSTIBIL---\n");
			List<MPMat> listaFiltrata = app.filtrare(TipM.COMBUSTIBIL);
			app.afisareLista(listaFiltrata);

			System.out.println("\n---Afisare map---\n");
			Map<TipM, Double> mapa = app.valoareMateriale();

			mapa.forEach((key, value)->{
				System.out.println(key + " " + value);
			});

			listaSincron.clear();
			Thread fir1 = new Thread(() -> app.citireFisierTxtSincron(numeFisierTxt));
			Thread fir2 = new Thread(() -> app.citireFisierTxtSincron("Fisier2.csv"));

			fir1.start(); fir1.join();
			fir2.start(); fir2.join();

			System.out.println("\n---Citire din 2 fisiere cu 2 thread-uri---\n");
			listaSincron.forEach(System.out::println);
		}

		catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void citireFisierTxtSincron(String numeFisier){
		try(BufferedReader bufferedReader = new BufferedReader(new FileReader(numeFisier))) {
			bufferedReader.lines().forEach(linie->{
				String[] t = linie.split(",");
				MPMat mpMat = new MPMat();

				mpMat.setCod(Integer.parseInt(t[0].trim()));
				mpMat.setDenumire(t[1].trim());
				mpMat.setValoare(Double.parseDouble(t[2].trim()));
				mpMat.setTip(TipM.valueOf(t[3].trim().toUpperCase()));

				synchronized (mpMat){
					listaSincron.add(mpMat);
				}
			});
		}

		catch(Exception ex){
			ex.printStackTrace();
		}
	}

	@Override
	public void citire(String numeFisiser) throws Exception {
		lista.clear();
		try(BufferedReader bufferedReader = new BufferedReader(new FileReader(numeFisiser))) {
			bufferedReader.lines().forEach(linie->{
				String[] t = linie.split(",");
				MPMat mpMat = new MPMat();

				mpMat.setCod(Integer.parseInt(t[0].trim()));
				mpMat.setDenumire(t[1].trim());
				mpMat.setValoare(Double.parseDouble(t[2].trim()));
				mpMat.setTip(TipM.valueOf(t[3].trim().toUpperCase()));

				lista.add(mpMat);

			});

		}

		catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void salvare(String numeFisier) throws Exception {
		try(ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(numeFisier))) {

			for(MPMat mpMat : lista){
				objectOutputStream.writeObject(mpMat);
			}
		}

		catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void restaurare(String numeFisiser) throws Exception {
		lista.clear();
		try(FileInputStream fileInputStream = new FileInputStream(numeFisiser);
			ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {

			while(fileInputStream.available() != 0){
				lista.add((MPMat) objectInputStream.readObject());
			}
		}

		catch (Exception ex){
			ex.printStackTrace();
		}
	}

	public void afisareLista(List<MPMat> listaMp){
		listaMp.forEach(System.out::println);
	}

	@Override
	public List<MPMat> sort1() {
		return lista.stream().sorted().collect(Collectors.toList());
	}

	@Override
	public List<MPMat> sort2() {
		return lista.stream().sorted((mat1, mat2)->
			mat1.getValoare() < mat2.getValoare() ? -1 : 1
		).collect(Collectors.toList());
	}

	@Override
	public List<MPMat> filtrare(TipM tip) {
		return lista.stream().filter(mat->
		mat.getTip() == tip).collect(Collectors.toList());
	}

	@Override
	public Map<TipM, Double> valoareMateriale() {
		Map<TipM, Double> mapa = lista.stream().collect(Collectors.groupingBy((MPMat mat)->mat.getTip(),
				Collectors.summingDouble((MPMat mat)->mat.getValoare())));

		return mapa;

	}
}
