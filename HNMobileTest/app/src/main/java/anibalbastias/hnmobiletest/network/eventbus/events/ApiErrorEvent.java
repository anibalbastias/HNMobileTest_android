package anibalbastias.hnmobiletest.network.eventbus.events;

/**
 * Created by anibalbastias on 26-02-16.
 */

import retrofit.RetrofitError;

/**
 * Reports error during API call.
 *
 * @author kthakur
 * @version 0.1
 */
public class ApiErrorEvent {

    RetrofitError error;

    public ApiErrorEvent(RetrofitError error) {
        this.error = error;
    }

    public RetrofitError getError() {
        return error;
    }
}
