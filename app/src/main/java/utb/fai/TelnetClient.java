package utb.fai;

import java.io.*;
import java.net.*;

public class TelnetClient {

    private String serverIp;
    private int port;

    public TelnetClient(String serverIp, int port) {
        if(serverIp.equals("localhost")){
            this.serverIp = "127.0.0.1";
        }else{
            this.serverIp = serverIp;
        }
        this.port = port;
    }

    public void run() {
        try {
            Socket socket = new Socket(serverIp, port);
            // Implementation of receiving and sending data
            // Implement processing of input from the user and sending data to the server
            // Implement response processing from the server and output to the console
            Thread readerThread = new Thread(()->this.readFromServer(socket));
            Thread writerThread = new Thread(()->this.writeToServer(socket));

            readerThread.start();
            writerThread.start();

            try{
                writerThread.join();
                socket.close();
                readerThread.join();
            }catch(InterruptedException e){
                e.printStackTrace();
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

   private void readFromServer(Socket socket){
    try{
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null){
                 System.out.println(line);
                 System.out.flush();
            }
    }catch(Exception e){
        if(!socket.isClosed()){
            e.printStackTrace();
        }
       
    }

    }

    private void writeToServer(Socket socket){
        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

             String line;
             while ((line = reader.readLine()) != null){
                 writer.println(line);
                 writer.flush();
                 if(line.equals( "/QUIT")){
                    socket.close();
                    break;
                 }
             }
        }catch(Exception e){
           // e.printStackTrace();
        }
        
    }
}
