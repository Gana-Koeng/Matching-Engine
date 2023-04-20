
package CSX.MatchingEngine.app.tcp;

import CSX.MatchingEngine.app.Entity.broker.Broker;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class GreetClient {
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public void startConnection(String ip, int port) throws Exception {
        clientSocket = new Socket(ip, port);
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        System.out.println(clientSocket.getInputStream());
    }

    public String sendMessage(String msg) throws Exception {
        out.println(msg);
        String resp = in.readLine();
        return resp;
    }

    public void stopConnection()  {
        try{
            in.close();
            out.close();
            clientSocket.close();
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        GreetClient client = new GreetClient();

        try
        {
            while(true){
            client.startConnection("10.10.18.162", 5000);

            client.sendMessage("okai love okkkkk ");
            }

        }catch (Exception e){
            client.stopConnection();
        }
    }
}