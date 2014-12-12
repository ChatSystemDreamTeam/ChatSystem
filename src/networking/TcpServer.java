package networking;

import data.FileDescription;
import utils.Conf;
import utils.Logger;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TcpServer extends Thread{
    private final ChatNI chatNI;
    private InetSocketAddress localSockAddr;
    private ServerSocket socket;
    private boolean shouldRun;
    private Map<String, TcpReceiver> receivers;
    public TcpServer(ChatNI chatNI) {
        this.chatNI = chatNI;
        this.localSockAddr = new InetSocketAddress(Conf.PORT);
        this.shouldRun=true;
        receivers = new HashMap<String, TcpReceiver>();
        Logger.log("Starting SocketServer");
        try {
            socket = new ServerSocket(Conf.PORT);
        } catch (IOException e) {
            //TODO
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while(shouldRun) {
            if (this.socket.isBound()) {
                try {
                    Logger.log("Waiting for a connection");
                    Socket sock = this.socket.accept();
                    Logger.log("Connection accepted!");
                    receivers.put(sock.getRemoteSocketAddress().toString(), new TcpReceiver(this,sock));

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void shutdown() {
        this.shouldRun=false;
        for(TcpReceiver receiver : receivers.values()) {
            receiver.shutdown();
        }
    }

    public void receivingFile(String ipFrom, FileDescription file) {
        this.chatNI.doNotifyReceivingFile(ipFrom, file);
    }

    public void fileReceived(String ipFrom, FileDescription file) {
        this.chatNI.doNotifyFileReceived(ipFrom, file);
        this.receivers.remove(ipFrom);
    }
}