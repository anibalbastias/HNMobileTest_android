package anibalbastias.hnmobiletest.network;

/**
 * Created by anibalbastias on 09-06-15.
 */

import com.google.gson.JsonObject;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

public interface HNMobileRESTService {
    // HNMobile API REST Methods
    @GET("/api/v1/search_by_date")
    void getSearchNews(@Query("query") String query,
                       @Query("page") int page,
                       Callback<JsonObject> cb);
}

