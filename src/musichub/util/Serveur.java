package musichub.util;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.beans.XMLEncoder;
import java.io.FileOutputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import javax.sound.sampled.*;
import musichub.business.*;
import java.util.*;

public class Serveur  {
 
    public Serveur () {
   
      final ServerSocket socketServeur  ;
      final Socket socketClient ;
      final BufferedReader input;
      final PrintWriter output;
      final Scanner sc=new Scanner(System.in);
      //File file = new File("F:/the-weeknd.wav");
      //Client client;
   
      try {
        socketServeur = new ServerSocket(5009);
        socketClient = socketServeur.accept();
        output = new PrintWriter(socketClient.getOutputStream());
        input = new BufferedReader (new InputStreamReader (socketClient.getInputStream()));
        Thread envoi= new Thread(new Runnable() {
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
        sc.close();
        envoi.start();
    
        Thread reception= new Thread(new Runnable() {
           String message ;
           @Override
           public void run() {
              try  { 
                 message = input.readLine();
                 //tant que le client est connecté
                 while(message!=null){
                    System.out.println("Client : "+message);
                    message = input.readLine();
  
                 }
                 //sortir de la boucle si le client a déconecté
                 System.out.println("Client déconecté");
                 //fermer le flux et la session socket
                 output.close();
                 socketClient.close();
                 socketServeur.close();
              } catch (IOException e  ) {
                   e.printStackTrace();
              }
          }
       });
       reception.start();
       }catch (IOException e) {
          e.printStackTrace();
   
       }
    }


    
 }