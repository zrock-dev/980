package com.fake_orgasm;

import com.fake_orgasm.server_management.ServerManager;

/**
 * Main Class to set up project application.
 */
public class Main {

    /**
     * This is the main function of the application it is meant to
     * start the whole app.
     *
     * @param args It does not expect any args as parameter.
     */
    public static void main(final String[] args) {
        ServerManager.getInstance();
    }
}
