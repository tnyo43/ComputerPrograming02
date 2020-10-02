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

            // 自分が書いたテキストをサーバに送るためのPrintWriter
            this.pwToServer = new PrintWriter(this.socket.getOutputStream(), true);

            // サーバから送られたテキストを扱うスレッド
            BufferedReader brFromServer = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            this.thFromServer = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        String s;
                        while((s = brFromServer.readLine()) != null) {
                            try {
                                String strs[] = nameTextOfStr(s);
                                String name = strs[0];
                                String text = strs[1];
                                receiver.received(name, text);
                            } catch (InvalidMessageException e) {
                                System.err.println(e.getMessage());
                                System.err.println(e);
                            }
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
    }

    public void send(String text, String username) {
        this.pwToServer.println(nameTextToStr(username, text));
    }

    private String nameTextToStr(String name, String text) {
        return String.format("%s;%s", name, text);
    }

    private String[] nameTextOfStr(String original) throws InvalidMessageException {
        String strs[] = original.split(";", 2);
        if (strs.length != 2) {
            throw new InvalidMessageException("cannot find username");
        }
        return strs;
    }
}
