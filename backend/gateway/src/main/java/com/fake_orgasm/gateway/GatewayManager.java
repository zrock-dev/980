package com.fake_orgasm.gateway;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Class to manage a client request to some server.
 */
public final class GatewayManager {

    /**
     * Variable to store server ip address.
     */
    private static final String SERVER_HOST = "127.0.0.1";

    /**
     * Variable to establish connection between client and server.
     */
    private static final int PORT = 5000;

    /**
     * Singleton variable.
     */
    private static GatewayManager instance;

    /**
     * Socket variable to manage a single request.
     */
    private final Socket socket;

    /**
     * Class constructor to init gateway socket and launch it.
     */
    private GatewayManager() {
        try {
            this.socket = new Socket(SERVER_HOST, PORT);
            launchGateway();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Method to manage a request to server passed from serverHost variable.
     */
    private void launchGateway() {
        try {
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            out.writeUTF("Hello from Gateway");

            String message = in.readUTF();
            System.out.println(message);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Method to manage singleton variable.
     *
     * @return Singleton variable.
     */
    public static GatewayManager getInstance() {
        if (instance == null) {
            instance = new GatewayManager();
        }
        return instance;
    }
}
