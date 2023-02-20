
package chat_server;
import java.io.Serializable;
import java.rmi.*;
import java.rmi.server.*;
import java.util.ArrayList;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;

/**
 *
 * @author LENOVO
 */
public class chat extends UnicastRemoteObject implements i_chat, Serializable {

  public String name;
  public TextArea chat_area;
  public ListView clientlist;
  public TabPane chattabs;
  public TextArea pvchat;
  public ArrayList < i_chat > clients;
  public ArrayList < Tabs > tabs = new ArrayList < Tabs > ();

  chat() throws RemoteException {
    super();
    this.clients = new ArrayList < i_chat > ();

  }
  public chat(String n, TextArea t, ListView l, TabPane c, ArrayList < Tabs > tabs) throws RemoteException {
    this.name = n;
    this.chat_area = t;
    this.clientlist = l;
    this.chattabs = c;
    this.tabs = tabs;
  }
  public String getname() {
    return this.name;
  }
  public void send_message(String message) {
    this.chat_area.appendText("\n" + message);

    System.out.println(message);
  }

  public ArrayList < Tabs > get_tabs() {
    return this.tabs;
  }

  public ArrayList < i_chat > get_client() {
    return clients;
  }
  public void add_client(i_chat c) {
    clients.add(c);
  }
  public void add_tab(Tabs tab) {
    this.tabs.add(tab);
  }
  public TextArea getchat() {
    return this.chat_area;
  }
  public void add_to_list(String name) {
    Platform.runLater(() -> this.clientlist.getItems().add(name));

  }
  public void send_private_message(String message, String name) {
    int found = 0;
    
   
    for (Tabs f: this.get_tabs()) {
      if (f.getName().equals(name)) {
        found = 1;
        TextArea ct = f.getTab();

        // c.send_pv_message(message, ct);
        ct.appendText("\n" + message);
     
      }
    }
    if (found == 0) {

      TextArea cl_chat = new TextArea();
      cl_chat.setPrefHeight(269);
      cl_chat.setPrefWidth(330);
      cl_chat.setEditable(false);
      cl_chat.appendText("\n" + message);
      Tabs t = new Tabs(cl_chat, name);
      this.add_tab(t);
    

  }
}
  public TabPane gettabpane() {
    return this.chattabs;
  }
  public void delete_from_list(String name){
      
       ObservableList<String> data=this.clientlist.getItems();
  
       for(String e:data){
    if(e.equals(name)){
    //data.remove(e);
    Platform.runLater(() -> data.remove(e));
    
    }
    }
   

    this.clientlist.setItems(data);
    
  
  }
  
}