package TEMA4;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MainMF implements IO, Operatiuni{
	private List<MijlocFix> lista = new ArrayList<>();
	private final static String numeFisierBinar = "Temp.dat";

	public static void main(String[] args) {
		MainMF app = new MainMF();

		try {
			System.out.println("\n---Citire fisier txt---\n");
			app.citire("Mijfix.csv");
			app.printList();

			System.out.println("\n---Salvare + Restaurare binar---\n");
			app.salvare(numeFisierBinar);
			app.restaurare(numeFisierBinar);
			app.printList();

			System.out.println("\n---Sortare dupa COD---\n");
			List<MijlocFix> listaCOD = app.sort1();
			app.printListStaticContext(listaCOD);

			System.out.println("\n---Sortare dupa VALOARE---\n");
			List<MijlocFix> listaVAL = app.sort2();
			app.printListStaticContext(listaVAL);

			System.out.println("\n---Filtrare dupa CATEGORIE---\n");
			List<MijlocFix> listaCATEG = app.filtrare(Categorii.CONSTRUCTII);
			app.printListStaticContext(listaCATEG);

			System.out.println("\n---Map<Categorii, Double>---\n");
			Map<Categorii, Double> mapa = app.valoareMijloaceFixe();
			mapa.forEach((key, value)-> System.out.println(key + " " + value));

		}

		catch (Exception ex){
			ex.printStackTrace();
		}
	}

	@Override
	public void citire(String numeFisier) throws Exception {
		lista.clear();
		try(BufferedReader bufferedReader = new BufferedReader(new FileReader(numeFisier))) {
			bufferedReader.lines().forEach(linie->{
				String[] t = linie.split(",");
				MijlocFix mijlocFix = new MijlocFix();

				mijlocFix.setCod(Integer.parseInt(t[0].trim()));
				mijlocFix.setDenumire(t[1].trim());
				mijlocFix.setValoare(Double.parseDouble(t[2].trim()));
				mijlocFix.setCategorie(Categorii.valueOf(t[3].trim().toUpperCase()));

				lista.add(mijlocFix);
			});
		}

		catch(Exception ex){
			ex.printStackTrace();
		}
	}

	@Override
	public void salvare(String numeFisier) throws Exception {
		try(ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(numeFisier))) {
			for(MijlocFix mf : lista){
				objectOutputStream.writeObject(mf);
			}
		}

		catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void restaurare(String numeFisier) throws Exception {
		lista.clear();
		try(FileInputStream fileInputStream = new FileInputStream(numeFisier);
			ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)){

			while(fileInputStream.available() != 0){
				lista.add((MijlocFix) objectInputStream.readObject());
			}
		}

		catch (Exception ex){
			ex.printStackTrace();
		}
	}

	public void printList(){
		lista.forEach(System.out::println);
	}

	public void printListStaticContext(List<MijlocFix> list) {
		list.forEach(System.out::println);
	}

	@Override
	public List<MijlocFix> sort1() {
		return lista.stream().sorted().collect(Collectors.toList());
	}

	@Override
	public List<MijlocFix> sort2() {
		return lista.stream().sorted((mf1, mf2)->
				mf1.getValoare() < mf2.getValoare() ? -1 : 1).collect(Collectors.toList());
	}

	@Override
	public List<MijlocFix> filtrare(Categorii categorie) {
		return lista.stream().filter(mf->
				mf.getCategorie() == categorie).collect(Collectors.toList());
	}

	@Override
	public Map<Categorii, Double> valoareMijloaceFixe() {
		Map<Categorii, Double> mapa = lista.stream().collect(Collectors.groupingBy((MijlocFix mf) -> mf.getCategorie(),
				Collectors.summingDouble((MijlocFix mf2) -> mf2.getValoare())));

		return mapa;

	}
}
