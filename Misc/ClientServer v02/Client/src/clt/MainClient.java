package clt;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class MainClient {
	public static void main(String[] args) {
		MainClient app = new MainClient();

		try {
			app.sendStuffToServer();
		}

		catch (Exception ex) {
			System.err.println(ex.getMessage());
		}
	}

	public void sendStuffToServer() {
		try (Socket socket = new Socket("localhost", 2013);
			 ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
			 ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream())) {

			out.writeObject("Message from Client");
			System.out.println("Received from server: " + in.readObject().toString());

			out.writeObject("Hello...");
			System.out.println("Received again: " + in.readObject().toString());

			List<String> names = readNamesFromTxtFile("Names.csv");
			out.writeObject(names.size());

			//pass the names to the server
			for (int i = 0; i < names.size(); i++)
				out.writeObject(names.get(i));

			//Receive the answer from the Server
			boolean[] finalResult = new boolean[names.size()];
			for (int i = 0; i < names.size(); i++) {
				finalResult[i] = (boolean) in.readObject();
				System.out.println("\n---Received: " + finalResult[i]);
			}
		}

		catch (Exception ex) {
			System.err.println(ex.getMessage());
		}
	}

	public List<String> readNamesFromTxtFile(String fileName) {
		List<String> listaNume = new ArrayList<>();

		try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
			bufferedReader.lines().forEach(line->{
				String[] content = line.split(",");

				for (int i = 0; i < content.length; i++) {
					String readString = content[i];
					listaNume.add(readString);
				}
			});
		}

		catch (Exception ex) {
			System.err.println(ex.getMessage());
		}

		listaNume.forEach(System.out::println);
		return listaNume;
	}
}
