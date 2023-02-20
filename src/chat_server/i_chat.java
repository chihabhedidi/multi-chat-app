/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package chat_server;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;

/**
 *
 * @author LENOVO
 */
public interface i_chat extends Remote {
    
    public void send_message(String message) throws RemoteException;
    public ArrayList<i_chat> get_client()throws RemoteException;
    public ArrayList<Tabs> get_tabs()throws RemoteException;
    public void add_client(i_chat c) throws RemoteException;
    public void add_tab(Tabs tab) throws RemoteException;
    public String getname() throws RemoteException;
    public TextArea getchat() throws RemoteException;
    public void add_to_list(String name) throws RemoteException;
    public void send_private_message(String message,String name) throws RemoteException;
    public void delete_from_list(String name) throws RemoteException;
    public TabPane gettabpane() throws RemoteException;
    
}
