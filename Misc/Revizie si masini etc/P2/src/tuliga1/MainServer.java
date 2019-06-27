package tuliga1;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainServer {
	private List<Autovehicul> listaAutovechicule = new ArrayList<>();

	@XmlElementWrapper(name = "ListaAutovehicule")
	@XmlElement(name = "Autovehicul")
	public List<Autovehicul> getListaAutovechicule() {
		return listaAutovechicule;
	}

	public void setListaAutovechicule(List<Autovehicul> listaAutovechicule) {
		this.listaAutovechicule = listaAutovechicule;
	}

	public static void main(String[] args) {
		MainServer app = new MainServer();

		try {
			// apelezi metodele...
		}

		catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public List<Autovehicul> citireAutovehicul() {
		List<Autovehicul> listaCitita = new ArrayList<>();
		String connString = "jdbc:derby:db/examen";

		try (Connection connection = DriverManager.getConnection(connString, "app", "app");
			 Statement selectStatement = connection.createStatement()) {

			String comandaSQL = "SELECT * FROM AUTOVEHICULE";
			ResultSet selectResultSet = selectStatement.executeQuery(comandaSQL);

			while(selectResultSet.next()) {
				//pasezi elementele...
				Autovehicul autovehicul = new Autovehicul();
				autovehicul.setNumarInmatriculare(selectResultSet.getString(1));
				autovehicul.setAn(selectResultSet.getInt(2));
				autovehicul.setTipAutovechicul(TipAutovechicul.
						valueOf(selectResultSet.getString(3).toUpperCase()));

				listaCitita.add(autovehicul);
			}

		}

		catch (Exception ex) {
			ex.printStackTrace();
		}

		return listaCitita;
	}

	public void citireRevizii() {
		final String denumireFisier = "Revizii.csv";

		try (BufferedReader bufferedReader = new BufferedReader(new FileReader(denumireFisier))) {
			bufferedReader.lines().forEach(linie->{
				String[] atribute = linie.split(",");

				try {
					String numarInmatriculare = atribute[0];

					ITP revizie = new ITP();
					revizie.setKm(Integer.parseInt(atribute[1]));
					revizie.setTaxa(Double.parseDouble(atribute[2]));
					revizie.setFirma(atribute[3]);

					String dataCitita = atribute[4];
					SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
					Date dataFinal = format.parse(dataCitita);
					revizie.setDatal(dataFinal);

					revizie.setRevenire(Boolean.parseBoolean(atribute[5]));

					for (Autovehicul a : listaAutovechicule) {
						if (a.getNumarInmatriculare().equalsIgnoreCase(numarInmatriculare)) {
							a.inregistrare(revizie);
						}
					}
				}

				catch (Exception ex) {
					ex.printStackTrace();
				}
			});

		}

		catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	public void inregistrare() {
		try (ServerSocket serverSocket = new ServerSocket(2013)) {
			serverSocket.setSoTimeout(10000);

			Socket socket = serverSocket.accept();
			Thread thread = new Thread(() -> procesare(socket));
			thread.start();
		}

		catch (Exception ex) {
			System.err.println(ex.getMessage());
		}
	}

	public synchronized void procesare(Socket socket) {
		try (ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
			 ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {

			/*
			aici faci procesarea de la client
			in -> ce primeste de la CLIENT
			out -> ce trimite la CLIENT
			 */

		}

		catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	void salvareXML() {
		try {
			JAXBContext context = JAXBContext.newInstance(MainServer.class);
			Marshaller mash = context.createMarshaller();
			mash.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			mash.marshal(this, new File("Main.xml"));

		}

		catch (Exception ex) {
			ex.printStackTrace();
		}

	}
}
