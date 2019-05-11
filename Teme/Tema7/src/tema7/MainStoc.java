package tema7;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainStoc implements IO {
	List<Stoc> lista = new ArrayList<>();
	List<Stoc> listaS = Collections.synchronizedList(lista);

	public static void main(String[] args) {
		MainStoc app = new MainStoc();

		try{
			String fisierBinar = "Temp.dat";

			System.out.println("\n---Citire din fisier txt---\n");
			app.citire("Stocuri.csv");
			app.afisareLista();

			System.out.println("\n---Salvare + citire din fisier binar---\n");
			app.salvare(fisierBinar);
			app.restaurare(fisierBinar);
			app.afisareLista();

			System.out.println("\n---Actualizare lista de stocuri---\n");
			app.actualizariContStoc("NIR1.csv");
			app.actualizariContStoc("NIR2.csv");
			app.afisareLista();
		}

		catch(Exception ex){
			System.err.println(ex);
		}
	}

	@Override
	public void citire(String numeFisier) throws Exception{
		lista.clear();
		try(BufferedReader in = new BufferedReader(new FileReader(numeFisier))){
			in.lines().forEach(linie->{
				String[] t = linie.split(",");
				Stoc stoc = new Stoc();

				stoc.setCod_stoc(Integer.parseInt(t[0].trim()));
				stoc.setDenumire(t[1].trim());
				stoc.setTotal_intrari(Double.parseDouble(t[2].trim()));
				stoc.setTotal_iesiri(Double.parseDouble(t[3].trim()));
				stoc.setUm(t[4].trim());

				lista.add(stoc);
			});
		}

		catch (Exception ex){
			System.err.println(ex);
		}
	}

	@Override
	public void salvare(String numeFisier) throws Exception {
		try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(numeFisier))){
			for (Stoc stoc:lista){
				out.writeObject(stoc);
			}
		}
		catch (Exception ex){
			System.err.println(ex);
		}
	}

	@Override
	public void restaurare(String numeFisier) throws Exception {
		try(FileInputStream fis = new FileInputStream(numeFisier);
			ObjectInputStream ois = new ObjectInputStream(fis)){

			lista.clear();
			while (fis.available()!=0){
				lista.add((Stoc) ois.readObject());
			}
		}
		catch (Exception ex){
			System.err.println(ex);
		}
	}

	public void actualizariContStoc(String numeFisier){
		try(BufferedReader in = new BufferedReader(new FileReader(numeFisier))){
			int numar_nota = Integer.parseInt(in.readLine().trim());

			in.lines().forEach(linie->{
				String[] t = linie.split(",");
				int cod_stoc = Integer.parseInt(t[0].trim());
				char tip_tranzactie = t[1].charAt(0);
				double cantitate = Double.parseDouble(t[2].trim());

				for(Stoc s : lista){
					if(cod_stoc == s.getCod_stoc()){
						if(tip_tranzactie == 'E'){
							s.setTotal_iesiri(s.getTotal_iesiri() + cantitate);
						}

						if(tip_tranzactie == 'I'){
							s.setTotal_intrari(s.getTotal_intrari() + cantitate);
						}
					}
				}

			});
		}

		catch (Exception ex){
			System.err.println(ex);
		}
	}


	public List<Stoc> getLista() {
		return lista;
	}

	public void setLista(List<Stoc> lista) {
		this.lista = lista;
	}

	public void afisareLista(){
		lista.forEach(System.out::println);
	}
}
