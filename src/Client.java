import java.io.*;
import java.net.*;

class Client {
    private static final int PORT = 10001;
    private static final String HOST = "localhost";

    private class Connection implements Runnable {
        BufferedReader in;
        PrintWriter out;
    
        private Connection(BufferedReader in, PrintWriter out) {
            this.in = in;
            this.out = out;
        }
    
        public void run() {
            try {
                String s;
                while((s = this.in.readLine()) != null) {
                    this.out.println(s);
                }
            }
            catch(Exception e) {
                System.err.println("sender error");
                System.err.println(e);
            }
        }
    }

    public Client() {
        try (Socket socket = new Socket(HOST,PORT)) {
            // 自分が書いたテキストをサーバに送るスレッド
            BufferedReader brToServer = new BufferedReader(new InputStreamReader(System.in));
            PrintWriter pwToServer = new PrintWriter(socket.getOutputStream(), true);
            Thread thToServer = new Thread(new Connection(brToServer, pwToServer));

            // サーバから送られたテキストを扱うスレッド
            BufferedReader brFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter pwFromServer = new PrintWriter(System.out, true);
            Thread thFromServer = new Thread(new Connection(brFromServer, pwFromServer));

            thToServer.start();
            thFromServer.start();

            while (true);

        } catch (IOException e) {
            System.err.println("client error");
            System.err.println(e);
        }
    }

    public static void main(String args[]) {
        new Client();
    }
}
