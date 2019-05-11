package tema6;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainCont implements  IO {
	List<Cont> lista = new ArrayList<>();
	List<Cont> listaSincrona = Collections.synchronizedList(lista);

	public List<Cont> getLista() {
		return lista;
	}

	public void setLista(List<Cont> lista) {
		this.lista = lista;
	}

	public static void main(String[] args) {
		MainCont app = new MainCont();

		try {
			String numeFisierText = "Balanta.csv";
			String numeFisierBinar = "Tmp.dat";
			app.afiseazaLista();

			app.citire(numeFisierText);
			app.afiseazaLista();

			System.out.println("\n---Salvare binara + restaurare---\n");
			app.salvare(numeFisierBinar);

			app.restaurare(numeFisierBinar);
			app.afiseazaLista();

			System.out.println("\n---Actualizare secventiala---\n");
			app.actualizareCuNotaContabila("NoteContabile1.csv");
			app.actualizareCuNotaContabila("NotaContabila2.csv");

			app.afiseazaLista();

			System.out.println("\n---Actualizare cu fire de executie---\n");
			//fire de executie
			Thread f1 = new Thread(()->app.actualizareSincrona("NoteContabile1.csv"));
			Thread f2 = new Thread(()->app.actualizareSincrona("NotaContabila2.csv"));

			f1.start(); f2.start();
			f1.join(); f2.join();
		}

		catch(Exception ex){
			System.err.println(ex);
		}

	}

	@Override
	public void citire(String numeFisier) {
		lista.clear();
		try(BufferedReader in = new BufferedReader(new FileReader(numeFisier))){
			in.lines().forEach(linie->{
				String[] t = linie.split(",");
				Cont cont = new Cont();

				cont.setSimbol_cont(Integer.parseInt(t[0].trim()));
				cont.setDenumire(t[1].trim());
				cont.setRulaj_debitor(Double.parseDouble(t[2].trim()));
				cont.setRulaj_creditor(Double.parseDouble(t[3].trim()));

				lista.add(cont);
			});
		}

		catch(Exception ex){
			System.err.println(ex);
		}
	}

	@Override
	public void salvare(String numeFisier) {
		try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(numeFisier))){
			for(Cont c: lista){
				oos.writeObject(c);
			}
		}

		catch(Exception ex){
			System.err.println(ex);
		}
	}

	@Override
	public void restaurare(String numeFisier) {
		try(FileInputStream fis = new FileInputStream(numeFisier);
			ObjectInputStream ois = new ObjectInputStream(fis)){
			lista.clear();

			while(fis.available() != 0){
				lista.add((Cont) ois.readObject());
			}
		}

		catch (Exception ex){
			System.err.println(ex);
		}
	}

	public void afiseazaLista(){
		lista.forEach(System.out::println);
	}

	public void actualizareCuNotaContabila(String numeFisier){
		try(BufferedReader in = new BufferedReader(new FileReader(numeFisier))){
			int numar_nota = Integer.parseInt(in.readLine().trim());

			in.lines().forEach(linie->{
				String[] t = linie.split(",");

				int cd = Integer.parseInt(t[0].trim());
				int cc = Integer.parseInt(t[1].trim());
				double suma = Double.parseDouble(t[2].trim());

				for (Cont cont : lista){
					if(cont.getSimbol_cont() == cd){
						cont.setRulaj_debitor(cont.getRulaj_debitor() + suma);
					}
					if(cont.getSimbol_cont() == cc){
						cont.setRulaj_creditor(cont.getRulaj_creditor() + suma);
					}
				}
			});
		}


		catch(Exception ex){
			System.err.println(ex);
		}
	}

	public void actualizareSincrona(String numeFisier){
		try(BufferedReader in = new BufferedReader(new FileReader(numeFisier))){
			int numar_nota = Integer.parseInt(in.readLine().trim());

			in.lines().forEach(linie->{

				String[] t = linie.split(",");
				int cd = Integer.parseInt(t[0].trim());
				int cc = Integer.parseInt(t[1].trim());
				double suma = Double.parseDouble(t[2].trim());

				for (Cont cont : listaSincrona){
					synchronized (cont) {
						if (cont.getSimbol_cont() == cd) {
							cont.setRulaj_debitor(cont.getRulaj_debitor() + suma);
						}
						if (cont.getSimbol_cont() == cc) {
							cont.setRulaj_creditor(cont.getRulaj_creditor() + suma);
						}
					}
				}
			});
		}
		catch (Exception ex){
			System.err.println(ex);
		}
	}

}
