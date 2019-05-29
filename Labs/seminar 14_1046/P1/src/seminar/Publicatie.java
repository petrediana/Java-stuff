package seminar;

public abstract class Publicatie {
    public String cota, titlu;
    public int anAparitie;

    public Publicatie(String cota, String titlu, int anAparitie) throws  Exception {
        this.cota = cota;
        this.titlu = titlu;

        if (this.anAparitie > 2019)
            throw  new Exception("Nu se poate");
        else

        this.anAparitie = anAparitie;
    }

    public Publicatie () {}

    public String getCota() {
        return cota;
    }

    public void setCota(String cota) {
        this.cota = cota;
    }

    public String getTitlu() {
        return titlu;
    }

    public void setTitlu(String titlu) {
        this.titlu = titlu;
    }

    public int getAnAparitie() {
        return anAparitie;
    }

    public void setAnAparitie(int anAparitie) {
        this.anAparitie = anAparitie;
    }

    @Override
    public String toString() {
        return "Publicatie{" +
                "cota='" + cota + '\'' +
                ", titlu='" + titlu + '\'' +
                ", anAparitie=" + anAparitie +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Publicatie that = (Publicatie) o;

        return cota.equals(that.cota);
    }

    @Override
    public int hashCode() {
        return cota.hashCode();
    }
}
