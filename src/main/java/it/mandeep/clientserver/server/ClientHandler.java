package it.mandeep.clientserver.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * ClientHandler class.
 *
 * Questa classe consente di gestire le richieste di un singolo client
 * creando un nuovo thread per facilitare la gestione di più client contemporaneamente.
 *
 * Questa classe estende la classe Thread per poter utilizzare il parallelismo.
 *
 * @author Mandeep Singh
 */
public class ClientHandler extends Thread {

    private Socket client;
    private ObjectOutputStream out;
    private ObjectInputStream in;

    /**
     * Unico costruttore della classe, consentedi inizializzare il Socker del Client da gestire.
     * @param client oggetto client da gestire.
     */
    ClientHandler(Socket client) {
        this.client = client;
    }

    /**
     * Metodo run della classe Thread, gestisce l'effettiva richiesta ricevuta dal client.
     */
    @Override
    public void run() {

        // Inizializza gli stream di input ed output.
        try {

            out = new ObjectOutputStream(client.getOutputStream());
            in = new ObjectInputStream(client.getInputStream());

        } catch (IOException ex) {
            System.err.println("Errore durante l'inizializzazione degli stream.. " + ex.getMessage());
        }

        try {
            String message = (String) in.readObject();
            System.out.println("Messaggio ricevuto: " + message);

            if (message.equals("Ciao Server!")) {
                out.writeObject("Ciao Client!");
            } else {
                out.writeObject("Risposta dal server.");
            }

        } catch (IOException ex) {
            System.err.println("Errore durante la lettura del messaggio.. " + ex.getMessage());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
