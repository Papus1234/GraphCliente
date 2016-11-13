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

/**
 *
 * @author Roberto
 */
public class Client {
    String name;
    String mensaje;
    PrintWriter printWtriWriter;
    Socket socket;
    String estado="";
    
    public Client(String name){
        this.estado=":has connected.:Connect";
        this.name=name;        
    }
    public void conectar() throws IOException{
        socket=new Socket("localhost",4444);
        printWtriWriter=new PrintWriter(socket.getOutputStream(),true);
                
        printWtriWriter.println(this.name+ estado);
        this.estado=":" + "Chat";
    }
    
    public void enviar(String mensaje){
        estado=":" + "Chat";
        printWtriWriter.println(this.name+":"+mensaje+estado);
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

