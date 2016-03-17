package anibalbastias.hnmobiletest.network.eventbus.events;

/**
 * Created by anibalbastias on 26-02-16.
 */

/**
 * Reports exception during API calls.
 *
 * @author kthakur
 * @version 0.1
 */
public class ExceptionEvent {

    Exception exception;

    public ExceptionEvent(Exception exception) {
        this.exception = exception;
    }

    public Exception getException() {
        return exception;
    }

}
