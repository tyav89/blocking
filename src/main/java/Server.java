import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
    ServerSocket serverSocket;
    private int port = 52656;
    private String name = "127.0.0.1";
    private boolean status = true;

    public Server() throws IOException {
        serverSocket = new ServerSocket(port);
    }


    public void startServer() {
        System.out.println("Server on");
        while (status) {
            try (Socket socket = serverSocket.accept();
                 PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
                 BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

                String line;
                long number;

                while ((line = br.readLine()) != null) {
                    if ("end".equals(line)) {
                        status = false;
                        System.out.println("Server off");
                        break;
                    }
                    number = Long.parseLong(line);
                    if(number < 0) {
                        number *= -1;
                    }

                    pw.println(getNumber(number));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private long getNumber(long n) {
        List<Long> list = new ArrayList<>();
        list.add(0, (long) 0);
        list.add(1, (long) 1);

        for (int i = 2; i <= n; i++) {
            list.add(i, (list.get(i - 1) + list.get(i - 2)));
        }

        return list.get((int) n);
    }
}

