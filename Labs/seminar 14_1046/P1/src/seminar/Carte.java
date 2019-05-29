package seminar;

import java.util.Arrays;

public class Carte extends Publicatie implements Comparable<Carte>, Cloneable, Operatiuni {
    private String autor[];
    private double valoareInventar;
    private boolean rezervare;

    public Carte () {}

    public Carte(String cota, String titlu, int anAparitie, String[] autor, double valoareInventar) throws Exception {
        super(cota, titlu, anAparitie);
        this.autor = autor;
        this.valoareInventar = valoareInventar;
    }

    public String[] getAutor() {
        return autor;
    }

    public void setAutor(String[] autor) {
        this.autor = autor;
    }

    public double getValoareInventar() {
        return valoareInventar;
    }

    public void setValoareInventar(double valoareInventar) {
        this.valoareInventar = valoareInventar;
    }

    public boolean isRezervare() {
        return rezervare;
    }

    public void setRezervare(boolean rezervare) {
        this.rezervare = rezervare;
    }

    @Override
    public int compareTo(Carte carte) {
        return cota.compareTo(carte.cota);
    }

    @Override
    public String toString() {
        return "Carte{" +
                "autor=" + Arrays.toString(autor) +
                ", valoareInventar=" + valoareInventar +
                ", rezervare=" + rezervare +
                '}';
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Carte clona = (Carte) super.clone();
        Carte carteNoua = new Carte();

        String[] copieAutori = Arrays.copyOf(autor, autor.length);
        carteNoua.setAutor(copieAutori);

        carteNoua.setValoareInventar(clona.getValoareInventar());
        carteNoua.setRezervare(clona.isRezervare());

        return carteNoua;

    }

    @Override
    public boolean rezervare() throws Exception {
        if (this.rezervare)
            throw new Exception("Cartea este deja rezervata!");
        else
            this.rezervare = true;
        return true;
    }
}
