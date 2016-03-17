package anibalbastias.hnmobiletest.network.eventbus.events;

/**
 * Created by anibalbastias on 26-02-16.
 */

/**
 * Reports result returned in the API call.
 *
 * @author kthakur
 * @version 0.1
 */
public class ApiResultEvent {

    Object _class;

    public ApiResultEvent(Object _class) {

        this._class = _class;
    }

    public Object getResponse() {

        return _class;
    }
}