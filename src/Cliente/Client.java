/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cliente;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTextArea;

/**
 *
 * @author Roberto
 */
public class Client {
    String name;
    String mensaje;
    PrintWriter printWtriWriter;

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public ArrayList<String> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<String> users) {
        this.users = users;
    }
    Socket socket;
    String estado="";
    ArrayList<String>users;
    JTextArea txt;
    BufferedReader reader;
    JComboBox combo;
    public Client(String name,JTextArea j,JComboBox combo){
        this.estado=":has connected.:Connect";
        this.name=name;   
        users=new ArrayList<>();
        this.txt=j;
        this.combo=combo;
    }
    public void conectar() throws IOException{
        socket=new Socket("localhost",4444);
        printWtriWriter=new PrintWriter(socket.getOutputStream(),true);
        
        InputStreamReader streamreader = new InputStreamReader(socket.getInputStream());
        reader = new BufferedReader(streamreader);
                
        printWtriWriter.println(this.name+ estado);
        this.estado=":" + "Chat";
    }
    
    public void enviar(String mensaje){
        estado=":" + "Chat";
        printWtriWriter.println(this.name+":"+mensaje+estado);
        printWtriWriter.flush();
     }
    
    
    public class IncomingReader implements Runnable
    {
    public IncomingReader(){
    
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
                    
                    System.err.println(stream);
                     data = stream.split(":");

                     if (data[2].equals(chat)) 
                     {
                         
                         System.out.println(data[0] + ": " + data[1] + "\n");
                         txt.append(data[0] + ": " + data[1] + "\n");
                         
                        
                     } 
                     else if (data[2].equals(connect))
                     {
                         System.out.println("Esta añadiendo");
                        txt.removeAll();
                        userAdd(data[0]);
                        
                        addCombo(users);
                     } 
                     else if (data[2].equals(disconnect)) 
                     {
                      //   userRemove(data[0]);
                     } 
                     else if (data[2].equals(done)) 
                     {
                        //users.setText("");
                        writeUsers();
                        users.clear();
                     }
                }
           }catch(Exception ex) { }
        }
        
    }
    public void addCombo(List<String>users){
        
        if (users.isEmpty()){
            String[] a=new String[1];
        }
        
        String []a=new String[users.size()];
        
       // System.out.println("este es un item :"+a[0]);
        
         
         DefaultComboBoxModel model = new DefaultComboBoxModel(users.toArray(a));
         combo.setModel(model);
        
    }
     public void userAdd(String data) 
    {
         users.add(data);
    }
     
     public void writeUsers() 
    {
         String[] tempList = new String[(users.size())];
         users.toArray(tempList);
         for (String token:tempList) 
         {
             //users.append(token + "\n");
         }
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public PrintWriter getPrintWtriWriter() {
        return printWtriWriter;
    }

    public void setPrintWtriWriter(PrintWriter printWtriWriter) {
        this.printWtriWriter = printWtriWriter;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }
    
}

