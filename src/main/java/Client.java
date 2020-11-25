import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    Socket socket;
    private int port = 52656;
    private String ip = "127.0.0.1";

    public Client() throws IOException {
        socket = new Socket(ip, port);
    }

    public void startClient() {
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));
             PrintWriter pw = new PrintWriter(
                     new OutputStreamWriter(socket.getOutputStream()), true);
             Scanner scanner = new Scanner(System.in)) {

            String msg;
            while (true) {
                System.out.println("Введи число, для завершения end");
                msg = scanner.nextLine();
                pw.println(msg);
                if ("end".equals(msg)) {
                    break;
                }
                System.out.println(br.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}