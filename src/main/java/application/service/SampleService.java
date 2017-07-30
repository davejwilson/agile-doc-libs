package application.service;

import application.client.SampleClient;

import javax.ejb.EJB;

/**
 * Created by david on 2017/07/30.
 */
public class SampleService {
    SampleClient sampleClient = new SampleClient();

    public String hello(String caller) {
        return sampleClient.helloWorld(caller);
    }
}
