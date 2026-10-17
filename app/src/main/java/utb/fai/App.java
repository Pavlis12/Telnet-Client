package utb.fai;

public class App {

    public static void main(String[] args) {
        // TODO: Implement input parameter processing
        try{
            if(args.length < 2 ){
                System.out.println("Nedostatek parametru");
                return;
            }

        TelnetClient telnetClient = new TelnetClient(args[0], Integer.parseInt(args[1]));
        telnetClient.run(); // run telnet client

        }catch (Exception e){
            e.printStackTrace();
        }
        
    }
}
