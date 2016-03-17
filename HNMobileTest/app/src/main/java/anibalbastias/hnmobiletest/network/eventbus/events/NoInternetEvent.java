package anibalbastias.hnmobiletest.network.eventbus.events;

/**
 * Created by anibalbastias on 26-02-16.
 */

/**
 * Reports Internet connectivity status.
 *
 * @author kthakur
 * @version 0.1
 */
public class NoInternetEvent {

    boolean value;

    public NoInternetEvent(boolean value) {
        this.value = value;
    }

    public boolean getException() {
        return value;
    }
}