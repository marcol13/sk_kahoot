package gui;

import java.io.*;
import java.math.BigInteger;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.util.List;

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
    int messageSize;
    String nowReaded;
//    List<String> messageList;

    public ClientConnection(String address, int port) throws IOException {
        this.address = address;
        this.port = port;
        this.errorFlag = false;
        this.messageSize = -1;
        try {
            socket = new Socket(address, port);

            this.nowReaded = "";

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
//        System.out.println(data);
        socket.getOutputStream().write(ByteBuffer.allocate(4).putInt(data.length()).array());
//        for(byte b: ByteBuffer.allocate(4).putInt(data.length()).array()){
//            System.out.format("0x%x ", b);
//        }
        socket.getOutputStream().write(data.getBytes());
    }

    public String getData() throws IOException {
//        InputStreamReader streamReader= new InputStreamReader(this.socket.getInputStream());
//        BufferedReader reader= new BufferedReader(streamReader);
//
//        String value= reader.readLine();
//        reader.close();
//        System.out.println("Odebrano: " + value);
        String result;

        while(true){
            if(messageSize < 0){
                if(nowReaded.length() > 4){
                    messageSize = new BigInteger(nowReaded.substring(0,4).getBytes()).intValue();
                    nowReaded = nowReaded.substring(4);
                }
            }
            if(messageSize > 0 && messageSize <= nowReaded.length()){
                result = nowReaded.substring(0,messageSize);
                nowReaded = nowReaded.substring(messageSize);
                messageSize = -1;
                break;
            }

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            this.is = socket.getInputStream();
            byte[] bytearr = new byte[512];
            int len = is.read(bytearr);
            baos.write(bytearr, 0, len);
            this.nowReaded += String.valueOf(baos);
            if(len < 1)
                throw new IOException();
        }
        return result;
//        int len = is.read(bytearr);
//        if(len == -1){
//            System.out.println("Błąd przesyłu danych");
//        }
//        return String.valueOf(baos);
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
