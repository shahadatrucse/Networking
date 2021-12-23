

import java.io.File;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class server {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(22222);
            System.out.println("Server Started");
            Socket socket = serverSocket.accept();
            System.out.println("Client Accepted");

            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            String clientSms;
            Scanner sc = new Scanner(System.in);
            while (true) {
                Object inputSms = ois.readObject();
                clientSms = (String) inputSms;
                System.out.println("Client : " + clientSms);
                

                //terminating conversation
                if(clientSms.equals("bye")){
                    break;
                }
                //file block added
                if (clientSms.equalsIgnoreCase("LS")) {
                    File fl = new File("../Server");
                    File[] files = fl.listFiles();
                    for (int i = 0; i < files.length; i++) {
                        oos.writeUTF(files[i].getName());
                    }
                    oos.writeUTF("End");
                }

                // Write object to client from console
                String serverSms = sc.nextLine();
                oos.writeObject(serverSms);
            }

        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}