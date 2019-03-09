package it.mandeep.clientserver.client;

import it.mandeep.clientserver.server.Server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {

        Socket server = null;
        ObjectOutputStream out = null;
        ObjectInputStream in  = null;
        Scanner scanner = new Scanner(System.in);
        String message = null;
        String responce = null;

        System.out.println("Inserire il messaggio da inviare al server: ");
        message = scanner.nextLine();

        try {
            server = new Socket(Server.SERVER_IP, Server.SERVER_PORT);

            out = new ObjectOutputStream(server.getOutputStream());
            in = new ObjectInputStream(server.getInputStream());

            out.writeObject(message);
            responce = (String) in.readObject();

        } catch (IOException ex) {
            System.err.println("Errore durante la connessione al server.. " + ex.getMessage());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println("Risposta del server: " + responce);
    }
}
