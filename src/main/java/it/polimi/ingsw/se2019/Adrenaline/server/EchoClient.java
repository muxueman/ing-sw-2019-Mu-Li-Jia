package it.polimi.ingsw.se2019.Adrenaline.server;


import java.io.*;
import java.nio.*;
import java.nio.channels.*;
import java.nio.charset.*;
import java.net.*;
import java.util.*;


public class EchoClient {
    private SocketChannel socketChannel = null;
    private ByteBuffer sendBuffer = ByteBuffer.allocate(1024);
    private ByteBuffer receiveBuffer = ByteBuffer.allocate(1024);
    private  Charset charset = Charset.forName("GBK");
    private Selector selector;

    public EchoClient() throws IOException{
        socketChannel = SocketChannel.open();
        InetAddress ia = InetAddress.getLocalHost();
        InetSocketAddress isa = new InetSocketAddress(ia, 8000);
        socketChannel.connect(isa);
        socketChannel.configureBlocking(false);
        System.out.println("CONNECTION SUCCESS");
        selector = Selector.open();
    }
/*
    public static void main(String args[]) throws IOException{
        final EchoClient client = new EchoClient();
        // create a thread
        Thread receiver = new Thread(){
            public void run(){
                client.receiveFromUser();
            }
        };

        // start receiver thread
        receiver.start();
        client.talk();
    }
*/
    //receive data from what user has write in cmd, and store in sendBuffer
    public void receiveFromUser(){
        try{
            BufferedReader localReader = new BufferedReader(new InputStreamReader(System.in));
            String message = null;
            while ((message = localReader.readLine()) != null){
                synchronized (sendBuffer){
                    sendBuffer.put(encode(message + "\r\n"));
                }
                if (message.equals("bye"))
                    break;
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void talk() throws IOException{
        socketChannel.register(selector,SelectionKey.OP_READ|SelectionKey.OP_WRITE);
        while (selector.select() > 0){
            Set readyKeys = selector.selectedKeys();
            Iterator it = readyKeys.iterator();
            while (it.hasNext()){
                SelectionKey key = null;
                try{
                    key = (SelectionKey)it.next();
                    it.remove();

                    if(key.isReadable()){
                        receive(key);
                    }
                    if(key.isWritable()){
                        send(key);
                    }
                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        }
    }

    // send data from sendBuffer
    public void send (SelectionKey key) throws IOException{
        SocketChannel socketChannel = (SocketChannel)key.channel();
        synchronized (sendBuffer){
            // set the position
            sendBuffer.flip();
            // send
            socketChannel.write(sendBuffer);
            // delete data already sent
            sendBuffer.compact();
        }
    }
    // receive data form EchoServer and store into receiveBuffer
    // print data if receiveBuffer stores and delete
    public void receive(SelectionKey key) throws  IOException{
        SocketChannel socketChannel = (SocketChannel)key.channel();
        socketChannel.read(receiveBuffer);
        receiveBuffer.flip();
        String receiveData = decode(receiveBuffer);

        if (receiveData.indexOf("\n") == -1)
            return;

        String outputData = receiveData.substring(0, receiveData.indexOf("\n") + 1);
        System.out.print(outputData);
        if (outputData.equals("echo: bye\r\n")){
            key.channel();
            socketChannel.close();
            System.out.println("CLOSE CONNECTION...");
            selector.close();
            // end program
            System.exit(0);
        }

        ByteBuffer temp = encode(outputData);
        receiveBuffer.position(temp.limit());
        receiveBuffer.compact();
    }

    public String decode(ByteBuffer buffer){
        CharBuffer charBuffer = charset.decode(buffer);
        return charBuffer.toString();
    }
    public ByteBuffer encode(String str){
        return charset.encode(str);
    }
}
