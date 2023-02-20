
package chat_client;

import chat_server.Tabs;
import chat_server.chat;
import chat_server.i_chat;
import java.net.URL;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import chat_client.client;
import javafx.collections.ObservableList;

/**
 *
 * @author LENOVO
 */
public class FXMLDocumentController implements Initializable {

  private i_chat server;
  private String ClientName = null;
  @FXML
  public TextField input;
  @FXML
  public Button envoyer;
  @FXML
  public TextArea chat_area;
  public Boolean havename = false;
  public Label client_label;
  @FXML
  public ListView<String> clientlist;
  @FXML
  public TabPane chats;
  public ArrayList < Tabs > tabs;
  public chat client;
@FXML
public Label nom;
Stage primStage;
  @FXML
  public void chatter(ActionEvent event) {

    try {

      Registry re = LocateRegistry.getRegistry(1099);
      i_chat server1 = (i_chat) re.lookup("a");

      server = server1;

      ArrayList < i_chat > clients;

      if (ClientName == null) {
        tabs = new ArrayList < Tabs > ();
        client_label = new Label(ClientName);

        //client_label.getStyleClass().add("label_client");
        ClientName = input.getText();
        client = new chat(ClientName, chat_area, clientlist, chats, tabs);

        //bank.set_client(client);
        server.add_client(client);

        clients = server.get_client();
        input.clear();
        client.send_message("[SERVEUR] :"+" Vous avez rejoint le chat");
        //   server.send_message(ClientName+" Entre le chat");
        client.clientlist.getItems().add("All");
        for (i_chat c: clients) {
          if (!c.getname().equals(ClientName)) {
            c.send_message("[SERVEUR] :"+ClientName + " Entre le chat");

            c.add_to_list(ClientName);
            client.clientlist.getItems().add(c.getname());

          }
        }
        primStage=(Stage) input.getScene().getWindow();
        primStage.setTitle("Welcome "+ ClientName +"!");
nom.setText(ClientName);
input.setPromptText("Message!!!");
      } else {
/*ObservableList<String> data=clientlist.getItems();
for(String e:data){
e="done";
}
for(String e:data){
    data.set(data.indexOf(e), "hello");
}
*/
//clientlist.setItems(data);
        String messagelocal = input.getText();
        clients = server.get_client();
        tabs = server.get_tabs();
        String message = ClientName + " : " + messagelocal;
        input.clear();

        if ("All".equals(chats.getSelectionModel().getSelectedItem().getText())) {
          for (i_chat c: clients) {
            if (!c.getname().equals(ClientName)) {

              c.send_message(message);
            } else {
              c.send_message(messagelocal);
            }

          }
        } else {
          String dest = chats.getSelectionModel().getSelectedItem().getText();
          TextArea ct;
          ArrayList < Tabs > t = null;
          for (i_chat c: clients) {
            if (c.getname().equals(dest)) {
              c.send_private_message(message, ClientName);

            } else if (c.getname().equals(ClientName)) {
              Tab s = chats.getSelectionModel().getSelectedItem();
              TextArea a = (TextArea) s.getContent();
              a.appendText("\n" + messagelocal);
            }

          }
        }
        
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public void initialize(URL url, ResourceBundle rb) {
      
     Platform.runLater(()->primStage=(Stage) input.getScene().getWindow());
     
      Platform.runLater(() ->  
              
              primStage.setOnCloseRequest((WindowEvent e) -> {
                 
           try {
                ArrayList < i_chat > clients;
                  clients = server.get_client();
           for (i_chat c: clients) {
          if (!c.getname().equals(ClientName)) {
            c.send_message("[SERVEUR] : "+ClientName + " Quitter le chat");
            c.delete_from_list(ClientName);

            

          }
        }
               Platform.exit();
           }
           catch (Exception e1) {
               e1.printStackTrace();
           }
       })

);

     

    clientlist.getSelectionModel().selectedItemProperty().addListener(new ChangeListener < String > () {

      @Override
      public void changed(ObservableValue < ? extends String > observable, String oldValue, String name) {

        Tab cl = new Tab();

        int opened = 0;
        cl.setText(name);
        TextArea cl_chat = new TextArea();
        cl_chat.setPrefHeight(269);
        cl_chat.setPrefWidth(330);
        cl_chat.setEditable(false);
        cl.setContent(cl_chat);

        for (Tab e: chats.getTabs()) {
          if (e.getText().equals(name)) {
            opened = 1;
            chats.getSelectionModel().select(e);
            break;
          }
        }
        if (opened == 0) {
          int already_created = 0;
          for (Tabs e: client.get_tabs()) {
            if (e.getName().equals(name)) {
              cl.setContent(e.getTab());
              chats.getTabs().add(cl);
              already_created = 1;
            }

          }
          if (already_created == 0) {
             
            Tabs t = new Tabs(cl_chat, name);

            client.add_tab(t);
            chats.getTabs().add(cl);
            chats.getSelectionModel().select(cl);
          }

        }

      }
    });

  }

}