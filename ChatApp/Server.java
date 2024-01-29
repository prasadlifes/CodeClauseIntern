import java.net.*;
import java.io.*;

public class Server {

    private ServerSocket server;
    private Socket socket;
    private BufferedReader br; // For reading from the socket
    private PrintWriter out;    // For writing to the socket

    /**
     * Constructor for Server class.
     */
    public Server() {
        try {
            server = new ServerSocket(7777);
            System.out.println("Server is ready to accept connection. Waiting...");
            socket = server.accept();

            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream());

            startReading();
            startWriting();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Starts a thread for reading messages from the client.
     */
    private void startReading() {
        Runnable readTask = () -> {
            System.out.println("Reader started.");
            try {
                while (true) {
                    String msg = br.readLine();
                    if (msg.equals("exit")) {
                        System.out.println("Client terminated the chat.");
                        socket.close();
                        break;
                    }
                    System.out.println("Client: " + msg);
                }
            } catch (IOException e) {
                System.out.println("Connection closed.");
            }
        };
        new Thread(readTask).start();
    }

    /**
     * Starts a thread for writing messages to the client.
     */
    private void startWriting() {
        Runnable writeTask = () -> {
            System.out.println("Writer started.");
            try (BufferedReader br1 = new BufferedReader(new InputStreamReader(System.in))) {
                while (!socket.isClosed()) {
                    String content = br1.readLine();
                    out.println(content);
                    out.flush();

                    if (content.equals("exit")) {
                        socket.close();
                        break;
                    }
                }
            } catch (IOException e) {
                System.out.println("Connection closed.");
            }
        };
        new Thread(writeTask).start();
    }

    public static void main(String[] args) {
        System.out.println("This is the server. Starting server...");
        new Server();
    }
}
