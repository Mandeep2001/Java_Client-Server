package it.mandeep.clientserver.server;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * Server class.
 * Questa classe rappresenta il server che viene avviato su un computer e
 * lascaito gestire le richieste dei client che si connettono.
 *
 * Questa classe consente una volta avviato si mette in ascolto dei client
 * che intendono connettersi, per ogni client istanzia un oggetto della classe
 * ClientHandler che gestisce la richiesta.
 */
public class Server {

    public static final short SERVER_PORT = 8080;
    public static final String SERVER_IP = "localhost";

    private ServerSocket server;

    public Server() {
        try {
            server = new ServerSocket(SERVER_PORT);
            System.out.println("Server avviato con successo.");
        } catch (IOException ex) {
            System.err.println("Errore durante l'avvio del server.. " + ex.getMessage());
        }

        while (server != null) {
            try {
                new ClientHandler(server.accept()).start();
                System.out.println("Server connesso con successo.");
            } catch (IOException ex) {
                System.err.println("Errore durante alla connessione al client.. " + ex.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Server();
    }
}
