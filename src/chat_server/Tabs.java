/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chat_server;

import java.io.Serializable;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;

/**
 *
 * @author LENOVO
 */
public class Tabs implements Serializable{
    public TextArea tab;
    public String name;

    public Tabs(TextArea tab, String name) {
        this.tab = tab;
        this.name = name;
    }

    public TextArea getTab() {
        return tab;
    }

    public String getName() {
        return name;
    }

    public void setTab(TextArea tab) {
        this.tab = tab;
    }

    public void setName(String name) {
        this.name = name;
    }
    
   
}
