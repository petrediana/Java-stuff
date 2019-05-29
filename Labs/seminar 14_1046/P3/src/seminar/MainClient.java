package seminar;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class MainClient {

    public static void main(String[] args) {
        MainClient app = new MainClient();
        app.rezervareCarti();
    }

    public void rezervareCarti () {
        try (Socket socket = new Socket("localhost", 2013);
             ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {

            out.writeObject("1322321");
            out.writeObject("232323");

            System.out.println(in.readObject());
            System.out.println(in.readObject());


        }

        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
