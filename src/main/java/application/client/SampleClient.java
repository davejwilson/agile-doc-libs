package application.client;

import javax.ejb.Stateless;

/**
 * Created by david on 2017/07/30.
 */
@Stateless
public class SampleClient {
    public String helloWorld(String callerService) {
        return "Hi " + callerService + " from the world";
    }
}
