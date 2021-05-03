package musichub.util;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.*;

public class Client extends Serveur {

    public Client() {
       
       final Socket socketClient;
       final BufferedReader input;
       final PrintWriter output;
       final Scanner sc = new Scanner(System.in);

       try {
         
        /*
        * les informations du serveur ( port et adresse IP ou nom d'hote
        * 127.0.0.1 est l'adresse local de la machine
        */
        socketClient = new Socket("127.0.0.1",5009);
  
        //flux pour envoyer
        output = new PrintWriter(socketClient.getOutputStream());
        //flux pour recevoir
        input = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
  
        Thread envoi = new Thread(new Runnable() {
            String message;
             @Override
             public void run() {
               while(true){
                 message = sc.nextLine();
                 output.println(message);
                 output.flush();
               }
            }
        });
        envoi.start(); 
        
        Thread reception = new Thread(new Runnable() {
            String message;
            @Override
            public void run() {
               try {
                 message = input.readLine();
                 while(message!=null){
                    System.out.println("Serveur : "+message);
                    message = input.readLine();
                 }
                 System.out.println("Serveur déconecté");
                 output.close();
                 socketClient.close();
               } catch (IOException e) {
                   e.printStackTrace();
               }
            }
        });
        reception.start();
        
      } catch (IOException e) {
           e.printStackTrace();
      }
  }
}