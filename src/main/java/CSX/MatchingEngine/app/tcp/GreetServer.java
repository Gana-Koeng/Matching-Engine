package CSX.MatchingEngine.app.tcp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class GreetServer {
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public void start(int port) {
        try{
            serverSocket = new ServerSocket(port);
            while(true){
                clientSocket = serverSocket.accept();
                System.out.println("Ip Address: "+ clientSocket.getLocalAddress());
                System.out.println("Port: " + clientSocket.getLocalPort());
                out = new PrintWriter(clientSocket.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                String msg = in.readLine();
                System.out.println("Message : " + msg);
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void stop() throws Exception {
        in.close();
        out.close();
        clientSocket.close();
        serverSocket.close();
    }
    public static void main(String[] args) {
        GreetServer server=new GreetServer();
        server.start(5000);
    }
}
