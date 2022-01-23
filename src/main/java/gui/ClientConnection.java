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
    InputStream is;
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
        System.out.println(data);
        socket.getOutputStream().write(data.getBytes());
    }

    public String getData() throws IOException {
//        InputStreamReader streamReader= new InputStreamReader(this.socket.getInputStream());
//        BufferedReader reader= new BufferedReader(streamReader);
//
//        String value= reader.readLine();
//        reader.close();
//        System.out.println("Odebrano: " + value);
        this.is = socket.getInputStream();
        byte[] bytearr = new byte[512];
        int len = is.read(bytearr);
        if(len == -1){
            System.out.println("Błąd przesyłu danych");
        }
        return new String(bytearr);
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
