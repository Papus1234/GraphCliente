/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cliente;

import java.io.BufferedReader;
import java.util.ArrayList;
import javax.swing.JTextArea;

/**
 *
 * @author Roberto
 */
public class IncomingReader implements Runnable
    {
    BufferedReader reader;
    JTextArea ta_chat;
    ArrayList<String>users;
        public IncomingReader(JTextArea ta_chat){
        this.ta_chat=ta_chat;
        
        }
        @Override
        public void run() 
        {
            String[] data;
            String stream, done = "Done", connect = "Connect", disconnect = "Disconnect", chat = "Chat";

            try 
            {
                while ((stream = reader.readLine()) != null) 
                {
                     data = stream.split(":");

                     if (data[2].equals(chat)) 
                     {
                         
                        ta_chat.append(data[0] + ": " + data[1] + "\n");
                        ta_chat.setCaretPosition(ta_chat.getDocument().getLength());
                     } 
                     else if (data[2].equals(connect))
                     {
                        ta_chat.removeAll();
                     //   userAdd(data[0]);
                     } 
                     else if (data[2].equals(disconnect)) 
                     {
                      //   userRemove(data[0]);
                     } 
                     else if (data[2].equals(done)) 
                     {
                        //users.setText("");
                       // writeUsers();
                       // users.clear();
                     }
                }
           }catch(Exception ex) { }
        }
        public ArrayList<String>agregarLista(String msj,ArrayList<String>user){
          return null;  
        }
    }
