package com.fake_orgasm.server_management;

import com.fake_orgasm.utils.FileReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Class to manage all operations in server according to different requests.
 */
public final class ServerManager {

    /**
     * Server Socket variable to init a server using some socket.
     */
    private final ServerSocket serverSocket;

    /**
     * Singleton variable.
     */
    private static ServerManager instance;

    /**
     * Class constructor to init server in port passed.
     */
    private ServerManager() {
        try {
            FileReader fileReader = new FileReader("src/main/resources/server/server_port.txt");
            int port = Integer.parseInt(fileReader.nextLine());
            this.serverSocket = new ServerSocket(port);
            launchServer();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Method to set a basic launching from server.
     */
    private void launchServer() {
        while (true) {
            Socket socket;
            try {
                socket = serverSocket.accept();

                DataInputStream in = new DataInputStream(socket.getInputStream());
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());

                String message = in.readUTF();

                System.out.println(message);

                out.writeUTF("hello from Server");
                socket.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Singleton class variable to manage only one manager.
     *
     * @return singleton variable.
     */
    public static ServerManager getInstance() {
        if (instance == null) {
            instance = new ServerManager();
        }
        return instance;
    }
}
