/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package chat_server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

/**
 *
 * @author LENOVO
 */
public class Chat_server {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)  {
        try{
       i_chat obj1= new chat();
       
       
    Registry r= LocateRegistry.createRegistry(1099);
    r.rebind("a", obj1);
    System.out.println("Le server est pret");
//    client c=stub.get_client();
    /*while(true){
        Scanner s = new Scanner(System.in);
          String msg = s.nextLine().trim();
          if (obj1.get_client() != null){
              
           i_chat c=obj1.get_client();
          msg = "[Server] "+":"+msg;
          
           c.send_message(msg);
          }
        }*/
    }catch(Exception e){e.printStackTrace();}
        
    }
}
