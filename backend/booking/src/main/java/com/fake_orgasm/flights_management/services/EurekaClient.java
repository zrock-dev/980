package com.fake_orgasm.flights_management.services;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.util.List;

/**
 * Client for interacting with the Eureka service registry.
 */
@Component("EurekaClient")
public class EurekaClient {
    private final DiscoveryClient discoveryClient;

    /**
     * Constructs a new EurekaClient with the given DiscoveryClient.
     *
     * @param discoveryClient the DiscoveryClient to use for interacting with the service registry
     */
    public EurekaClient(DiscoveryClient discoveryClient) {
        this.discoveryClient = discoveryClient;
    }

    /**
     * Retrieves the URI for the specified service.
     *
     * @param serviceName the name of the service
     * @return the URI of the first instance of the service, or null if no instances are available
     */
    public URI getUri(String serviceName) {
        List<ServiceInstance> instances = discoveryClient.getInstances(serviceName);
        if (instances != null && instances.size() > 0) {
            return instances.get(0).getUri();
        }
        return null;
    }
}
