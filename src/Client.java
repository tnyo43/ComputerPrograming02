import java.io.*;
import java.net.*;

class Client {
    private static final int PORT = 10001;
    private static final String HOST = "localhost";

    private Socket socket;
    private PrintWriter pwToServer;
    private Thread thFromServer;

    public Client(MessageReceiver receiver) {
        try {
            this.socket = new Socket(HOST,PORT);

            // 自分が書いたテキストをサーバに送る
            this.pwToServer = new PrintWriter(this.socket.getOutputStream(), true);

            // サーバから送られたテキストを扱うスレッド
            BufferedReader brFromServer = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            this.thFromServer = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        String s;
                        while((s = brFromServer.readLine()) != null) {
                            receiver.received(s);
                        }
                    }
                    catch(Exception e) {
                        System.err.println("error: brFromServer");
                        System.err.println(e);
                    }
                }
            });
        } catch (IOException e) {
            System.err.println("client error");
            System.err.println(e);
        }
    }

    public void start() {
        this.thFromServer.start();
        while (true);
    }

    public void send(String text) {
        this.pwToServer.println(text);
    }
}
