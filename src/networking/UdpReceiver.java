package networking;

import utils.Conf;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * Created by MagicMicky on 28/11/2014.
 */
public class UdpReceiver extends Thread {
    private boolean shouldRun = true;
    private final ChatNI chatNi;
    private final DatagramPacket datagramPacket;
    private final byte[] buffer;
    private DatagramSocket receiveSocket;
    public UdpReceiver(ChatNI chatNi) throws SocketException {
        this.chatNi = chatNi;
        receiveSocket=new DatagramSocket(Conf.PORT);
        this.buffer = new byte[2048];
        this.datagramPacket = new DatagramPacket(buffer, buffer.length);
    }
    @Override
    public void run() {
        while(shouldRun) {
            try {
                receiveSocket.receive(datagramPacket);
                String message = new String(buffer, 0, datagramPacket.getLength());
                chatNi.doReceive(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        receiveSocket.close();
    }
    public void setShouldRun(boolean shouldRun){
        this.shouldRun = shouldRun;
    }
}
