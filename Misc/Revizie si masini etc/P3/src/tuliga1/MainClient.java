package tuliga1;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class MainClient {
	public static void main(String[] args) {

	}

	public void inregistrare() {
		try (Socket socket = new Socket("localhost", 2013);
			 ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
			 ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream())) {

			String numarInmatriculare = "...";
			ITP revizie = new ITP();

			/*
			aici doar trimiti, e destul de simplut
			in -> ce primeste de la SERVER
			out -> ce trimite catre SERVER

			ca sa poti trimite un obiect de tip ITP trebuie sa il faci serializabil
			eu am uitat sa fac asta cand am facut jar-ul :D
			 */


		}

		catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
