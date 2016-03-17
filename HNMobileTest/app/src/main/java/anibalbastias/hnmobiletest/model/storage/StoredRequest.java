package anibalbastias.hnmobiletest.model.storage;

/**
 * Created by anibalbastias on 16-03-16.
 */
import com.google.gson.JsonObject;

import java.util.Date;

public class StoredRequest {

    public long timestamp = new Date().getTime();
    public JsonObject data;
}