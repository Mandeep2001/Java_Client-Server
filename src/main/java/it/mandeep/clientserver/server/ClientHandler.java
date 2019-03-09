package it.mandeep.clientserver.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientHandler extends Thread {

    private Socket client;
    private ObjectOutputStream out;
    private ObjectInputStream in;

    ClientHandler(Socket client) {
        this.client = client;
    }

    @Override
    public void run() {

        while (client.isConnected()) {

            try {

                out = new ObjectOutputStream(client.getOutputStream());
                in = new ObjectInputStream(client.getInputStream());

            } catch (IOException ex) {
                System.err.println("Errore durante l'inizializzazione degli stream.. " + ex.getMessage());
            }

            try {

                String message = in.readUTF();
                System.out.println("Messaggio ricevuto: " + message);

                if (message.equals("Ciao Server!")) {
                    out.writeUTF("Ciao Client!");
                }

            } catch (IOException ex) {
                System.err.println("Errore durante la lettura del messaggio.. " + ex.getMessage());
            }
        }
    }
}