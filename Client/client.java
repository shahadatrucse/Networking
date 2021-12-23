import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class client {
    public static void main(String[] args) throws IOException {
        String message, clientSms;
        System.out.println("Client Started");
        Socket socket = new Socket("127.0.0.1", 22222);

        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        Scanner sc = new Scanner(System.in);

        while (true) {
            // write message to Server
            message = sc.nextLine();
            oos.writeObject(message);
            if (message.equalsIgnoreCase("LS")) {
                
                System.out.println("\tList:");
                while (true) {
                    String st = ois.readUTF();
                    if (st.equals("End")) {
                        break;
                    }
                    System.out.println("\t" + st);
                }
            }

            // read message from client
            try {
                Object inputSms = ois.readObject();
                clientSms = (String) inputSms;
                System.out.println("Server : " + clientSms);
                if (clientSms.equals("bye") || message.equals("bye")) {
                    break;
                } 
            } catch (Exception e) {
                //TODO: handle exception
            }
            
        }

    }
}