package CSX.MatchingEngine.app.service;

import CSX.MatchingEngine.app.tcp.GreetServer;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

@Service
public class MessageService {
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public void start(int port) {
        try{
            serverSocket = new ServerSocket(port);
            while(true){
                clientSocket = serverSocket.accept();
                System.out.println(clientSocket.getLocalAddress());
                System.out.println(clientSocket.getLocalPort());
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public String getMsg() throws Exception{

        System.out.println("Ip Address: "+ clientSocket.getLocalAddress());
        System.out.println("Port: " + clientSocket.getLocalPort());
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        String msg = in.readLine();
        System.out.println("Message : " + msg);

        return msg;
    }

    public void stop() {
        try{
            in.close();
            out.close();
            clientSocket.close();
            serverSocket.close();
        }catch (Exception e){
            System.out.println(e);;
        }
    }
}
