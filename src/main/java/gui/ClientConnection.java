package gui;

import java.io.*;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientConnection{

    Socket socket = null;
    DataInputStream input = null;
    DataOutputStream output = null;
//    BufferedReader receiver;
//    PrintWriter sender;
    String address;
    int port;
    Boolean errorFlag;

    public ClientConnection(String address, int port) throws IOException {
        this.address = address;
        this.port = port;
        this.errorFlag = false;
        try {
            socket = new Socket(address, port);


//            input = new DataInputStream(System.in);
//
//            output = new DataOutputStream(socket.getOutputStream());

//            receiver = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//
//            sender = new PrintWriter(socket.getOutputStream(), true);
        }catch (IOException e) {
            System.out.println(e);
//            errorFlag = true;
            throw new IOException();
        }
    }

    public void sendData(String data) throws IOException {
//        output.writeByte(1);
//        output.writeUTF(data);
//        output.flush();
//        sender.println(data);
        socket.getOutputStream().write(data.getBytes());
    }

    public void closeConnection() throws IOException {
        input.close();
        output.close();
        socket.close();
    }

//    public void writeData(String data){
//        input.
//    }



//    public void connectTo() throws IOException {
//        this.connect(new InetSocketAddress(address, port));
//    }

//    public String readData(){
//
//    }

//    public void sendData(){
//
//    }

}
