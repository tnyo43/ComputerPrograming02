import java.io.*;
import java.net.*;
import java.util.*;


class Server {
    public static final int PORT = 10001;

    private class Connection implements Runnable {
        public final int id;
    
        private Socket socket;
        private PrintWriter out;
        private BufferedReader in;
        private Server server;

        private Connection(int id, Socket socket, Server server) {
            this.id = id;
            this.server = server;
            this.socket = socket;
            try {
                this.out = new PrintWriter(this.socket.getOutputStream(), true);
                this.in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            } catch (IOException e) {
                System.err.println(e);
            }
        }
    
        @Override
        public void run() {
            System.out.println("start " + Thread.currentThread());
            try {
                String text = null;
                while((text = this.in.readLine()) != null) {
                    this.server.send(this.id, text);
                }
                this.server.remove(this.id);
                this.socket.close();
            } catch (IOException e) {
                System.err.println(e);
                this.server.remove(this.id);
            }
        }

        public void send(String text){
            this.out.println(text);
        }
    }

    private List<Connection> connections;

    public Server() {
        this.connections = new LinkedList<>();
        Socket socket;
        int count = 0;

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                socket = serverSocket.accept();
                Connection c = new Connection(count++, socket, this);
                this.connections.add(c);
                new Thread(c).start();
            }
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    private synchronized void send(int senderId, String text){
        for (Connection c : this.connections) {
            if (c.id != senderId) {
                c.send(text);
            }
        }
    }

    private synchronized void remove(int id) {
        this.connections.removeIf(c -> c.id == id);
        send(id, "quiting [" + id + "]");
    }

    public static void main(String args[]) throws IOException {
        new Server();
    }
}
