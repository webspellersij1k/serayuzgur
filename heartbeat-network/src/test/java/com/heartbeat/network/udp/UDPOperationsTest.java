package com.heartbeat.network.udp;


import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class UDPOperationsTest {

    @org.junit.Test
    public void test() throws Exception {
        final String data = new String("hello");

        //Start Receiver
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    DatagramSocket receiver = new DatagramSocket(9998);
                    assert new String(UDPOperations.receive(receiver)).equals(data);
                } catch (SocketException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();

        //Send datagram
        DatagramSocket sender = new DatagramSocket(9999);
        UDPOperations.send(sender, InetAddress.getLoopbackAddress(), 9998, data.getBytes("UTF-8"));


    }

}