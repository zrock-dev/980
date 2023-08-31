package com.fake_orgasm.schedule;

import com.fake_orgasm.gateway.GatewayManager;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Class to manage scheduled tasks.
 */
@Component
public class ScheduledTasks {

    /**
     * Method to create a new socket to connect to server every second.
     */
    @Scheduled(fixedRate = 1000)
    public void requestToServer() {
        GatewayManager.getInstance();
    }
}
