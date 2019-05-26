package it.polimi.ingsw.se2019.Adrenaline.server;
import java.io.*;
import java.nio.*;
import java.nio.channels.*;
import java.nio.charset.*;
import java.net.*;
import java.util.*;

// a selectable channel for stream-oriented listening sockets, unblocked
// the channel support concurrent connection between clients and server in one thread

public class EchoServer {
    private Selector selector = null;
    private ServerSocketChannel serverSocketChannel = null;
    private int port = 8000;
    private Charset charset = Charset.forName("GBK");

    public EchoServer() throws IOException{
        selector = Selector.open();
        serverSocketChannel = serverSocketChannel.open();
        serverSocketChannel.socket().setReuseAddress(true);
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.socket().bind(new InetSocketAddress(port));
        System.out.println("START SERVER...");
    }

    public void service() throws IOException{
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        while (selector.select()>0){
            Set readyKeys = selector.selectedKeys();
            Iterator it = readyKeys.iterator();
            while (it.hasNext()){
                SelectionKey key = null;
                try{
                    key = (SelectionKey)it.next();
                    it.remove();

                    if(key.isAcceptable()){
                        ServerSocketChannel ssc = (ServerSocketChannel)key.channel();
                        SocketChannel socketChannel = (SocketChannel)ssc.accept();
                        System.out.println("CLIENT CONNECTION, FROM: " + socketChannel.socket().getInetAddress() + ", PORT: " + socketChannel.socket().getPort());
                        socketChannel.configureBlocking(false);
                        ByteBuffer buffer = ByteBuffer.allocate(1024);
                        socketChannel.register(selector, SelectionKey.OP_READ|SelectionKey.OP_WRITE, buffer);
                    }

                    if(key.isReadable()){
                        receive(key);
                    }
                    if(key.isWritable()) {
                        send(key);
                    }
                }catch(IOException e){
                    e.printStackTrace();
                    try{
                        if (key != null){
                            key.cancel();
                            key.channel().close();
                        }
                    }catch (Exception ex){
                        e.printStackTrace();
                    }
                }
            }
        }
    }
    public void send(SelectionKey key) throws IOException{
        ByteBuffer buffer = (ByteBuffer)key.attachment();
        SocketChannel socketChannel = (SocketChannel)key.channel();
        buffer.flip();
        String data = decode(buffer);
        if (data.indexOf("\r\n") == -1)
            return;
        String outputData = data.substring(0, data.indexOf("\n")+1);
        System.out.print(outputData);
        ByteBuffer outputBuffer = encode("echo: "+ outputData);
        while(outputBuffer.hasRemaining())
            socketChannel.write(outputBuffer);

        ByteBuffer temp = encode(outputData);
        buffer.position(temp.limit());
        buffer.compact();

        if (outputData.equals("bye\r\n")){
            key.cancel();
            socketChannel.close();
            System.out.println("CLOSE CONNTECTION...");
        }
    }

    public void receive(SelectionKey key) throws IOException{
        ByteBuffer buffer = (ByteBuffer)key.attachment();

        SocketChannel socketChannel = (SocketChannel)key.channel();
        ByteBuffer readBuff = ByteBuffer.allocate(32);
        socketChannel.read(readBuff);
        readBuff.flip();

        buffer.limit(buffer.capacity());
        buffer.put(readBuff);
    }

    public String decode(ByteBuffer buffer){
        CharBuffer charBuffer = charset.decode(buffer);
        return charBuffer.toString();
    }
    public ByteBuffer encode(String str){
        return charset.encode(str);
    }
/*
    public static void main(String args[]) throws Exception{
        EchoServer server = new EchoServer();
        server.service();
    }
*/
}
