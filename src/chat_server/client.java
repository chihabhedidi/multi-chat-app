/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chat_server;

import java.io.Serializable;

/**
 *
 * @author LENOVO
 */
public class client implements Serializable{
    String name;
    String message_received=null;

    public client(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String get_received_message(){
    return message_received;
    }
    public void set_message_null(){
    message_received=null;
    }
     public void receive_message(String message,String name){
    message_received=name +":"+message;
    
    }
}
