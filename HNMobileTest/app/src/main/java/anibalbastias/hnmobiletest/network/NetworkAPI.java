package anibalbastias.hnmobiletest.network;

import android.content.Context;
import android.util.Log;

import com.google.gson.JsonObject;
import com.squareup.okhttp.OkHttpClient;

import org.greenrobot.eventbus.EventBus;

import anibalbastias.hnmobiletest.R;
import anibalbastias.hnmobiletest.application.HNMobileApp;
import anibalbastias.hnmobiletest.model.storage.StorageAPI;
import anibalbastias.hnmobiletest.network.eventbus.events.ApiErrorEvent;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.OkClient;
import retrofit.client.Response;

public class NetworkAPI {

    private static HNMobileRESTService hnMobileRESTService;

    private Context context;
    private StorageAPI storageAPI;

    public NetworkAPI(Context context) {
        this.context = context;
        this.storageAPI = new StorageAPI(this.context);
    }

    private HNMobileRESTService getServiceInstace() {
        if (null == hnMobileRESTService) {
            OkHttpClient client = new OkHttpClient();
            client.setCookieHandler(((HNMobileApp) context.getApplicationContext()).getCustomCookieManager());

            hnMobileRESTService = new RestAdapter.Builder()
                    .setClient(new OkClient(client))
                    .setEndpoint(context.getString(R.string.endpoint_hnmobile))
                            //.setLogLevel(RestAdapter.LogLevel.FULL)
                    .build()
                    .create(HNMobileRESTService.class);
        }
        return hnMobileRESTService;
    }

    /**
     * API REST Methods
     */
    public void getHNNews(final int type, String query, int page) {
        getServiceInstace().getSearchNews(query, page, new Callback<JsonObject>() {
            @Override
            public void success(JsonObject jsonObject, Response response) {
                switch (type) {
                    case 0:
                        EventBus.getDefault().post(Parser.parseHNNewsEvent(jsonObject));
                        break;
                    case 1:
                        EventBus.getDefault().post(Parser.parseHNNewsRefreshEvent(jsonObject));
                        break;
                    case 2:
                        EventBus.getDefault().post(Parser.parseHNNewsLoadMoreEvent(jsonObject));
                        break;
                }
                Log.e("NetworkAPI HNNews", jsonObject.toString());
            }

            @Override
            public void failure(RetrofitError error) {
                EventBus.getDefault().post(new ApiErrorEvent(error));
            }
        });
    }
}
