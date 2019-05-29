package seminar;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MainServer {
    private List<Carte> listaCarti = new ArrayList<>();

    public static void main(String[] args) {

        try {
            MainServer app = new MainServer();
            app.listaCarti = app.citireCarti();
            app.afisareLista(app.listaCarti);

            app.rezervareCarti();

        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void setListaCarti(List<Carte> listaCarti) {
        this.listaCarti = listaCarti;
    }

    public List<Carte> citireCarti () {
        List<Carte> lista = new ArrayList<>();

        try (Connection c = DriverManager.getConnection("jdbc:derby:db/seminar14", "app", "app");
             Statement s = c.createStatement();
             ResultSet r = s.executeQuery("SELECT * FROM CARTI")) {

            while(r.next()) {
                Carte carte = new Carte();

                carte.setCota(r.getString(1));
                carte.setTitlu(r.getString(2));
                carte.setAnAparitie(r.getInt(3));
                carte.setAutor(r.getString(4).split(","));
                carte.setValoareInventar(r.getDouble(5));

                lista.add(carte);
            }

        }

        catch (Exception ex) {
            ex.printStackTrace();
        }

        return lista;
    }

    public void afisareLista(List<Carte> lista){
        lista.forEach(System.out::println);
    }

    public void rezervareCarti () {
        try (ServerSocket serverSocket = new ServerSocket(2013)) {
            serverSocket.setSoTimeout(10000);

            Socket socket = serverSocket.accept();
            Thread firPrelucrare = new Thread(() -> procesare(socket));
            firPrelucrare.start();
        }

        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public synchronized void procesare (Socket socket) {
        try (ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
             ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream())) {

            int nrCote = (int) in.readObject();
            List<String> cote = new ArrayList<>();
            for (int i = 0; i < nrCote; i++) {
                cote.add(in.readObject().toString());
            }

            boolean[] rezervari =  new boolean[nrCote];
            for (int i = 0; i < nrCote; i++) {
                Carte carte = new Carte();
                carte.setCota(cote.get(i));

                int k = listaCarti.indexOf(carte);
                if (k != -1)
                    try {
                        rezervari[i] = listaCarti.get(k).rezervare();
                    }

                    catch (Exception ex1){
                        ex1.printStackTrace();
                    }
            }

            for (int i = 0; i < nrCote; i++) {
                out.writeObject(rezervari[i]);
            }
        }

        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
