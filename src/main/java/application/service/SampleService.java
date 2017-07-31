package application.service;

import application.client.DownClient;
import application.client.SampleClient;
import application.client.UpClient;

import javax.ejb.EJB;

/**
 * Created by david on 2017/07/30.
 */
public class SampleService {
    public String hello(String caller) {
        switch (caller) {
            case "dave" : return new SampleClient().helloWorld(caller);
            case "jack" : return new UpClient().hello(caller);
            case "jill" : return new DownClient().hello(caller);
            default : return "i don't know you";
        }
    }
}
