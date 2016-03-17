package anibalbastias.hnmobiletest.model.storage;

/**
 * Created by anibalbastias on 16-03-16.
 */

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import java.util.Date;

import anibalbastias.hnmobiletest.model.response.HNNewsResponse;

public class StorageAPI {

    private final static String HNMOBILE_SESSION_KEY = "__R_session__";
    private final static String HNMOBILE_SESSION_NEWS = "__r_news__";
    private final static String HNMOBILE_SESSION_ID = "__r_sid__";
    private final static String HNMOBILE_SESSION_TOKEN = "__r_token__";
    private final static long HNMOBILE_CACHE_TIME = 1000 * 20;

    private final SharedPreferences hnMobilePrefs;
    private Gson gson;

    public StorageAPI(Context context) {
        hnMobilePrefs = context.getSharedPreferences(HNMOBILE_SESSION_KEY, Context.MODE_PRIVATE);
        gson = new GsonBuilder().serializeNulls().create();
    }

    public void saveRequest(String url, JsonObject response) {
        StoredRequest storedRequest = new StoredRequest();
        storedRequest.data = response;
        writeSessionData(url, gson.toJson(storedRequest, StoredRequest.class));
    }

    public JsonObject getRequest(String url) {
        String requestString = getStringFromPrefs(url);

        if (null == requestString)
            return null;

        StoredRequest storedRequest = gson.fromJson(requestString, StoredRequest.class);

        if (null == storedRequest)
            return null;

        if (new Date().getTime() - storedRequest.timestamp < HNMOBILE_CACHE_TIME)
            return storedRequest.data;

        return null;
    }

    public void saveHNNews(HNNewsResponse user) {
        writeSessionData(HNMOBILE_SESSION_NEWS, gson.toJson(user));
    }

    public HNNewsResponse getHNNews() {
        return gson.fromJson(getStringFromPrefs(HNMOBILE_SESSION_NEWS), HNNewsResponse.class);
    }

    public void saveCsrftoken(String token) {
        writeSessionData(HNMOBILE_SESSION_TOKEN, token);
    }

    public String getCsrftoken() {
        return getStringFromPrefs(HNMOBILE_SESSION_TOKEN);
    }

    public void saveSessionId(String sid) {
        writeSessionData(HNMOBILE_SESSION_ID, sid);
    }

    public String getSessionId() {
        return getStringFromPrefs(HNMOBILE_SESSION_ID);
    }

    public void nukePreferences() {
        hnMobilePrefs.edit().clear().commit();
    }

    private String getStringFromPrefs(String key) {
        return hnMobilePrefs.getString(HNMOBILE_SESSION_KEY + key, null);
    }

    private void writeSessionData(String key, Object data) {
        try {
            {
                String val = data.toString();
                Log.d("REIGN DESIGN", "[STORE] [" + key + "] : " + (val != null && (val.length() > 50) ? val.substring(0, 50) : val));
            }
            key = HNMOBILE_SESSION_KEY + key;
            if (data instanceof String) hnMobilePrefs.edit().putString(key, (String) data).commit();
            else if (data instanceof Integer)
                hnMobilePrefs.edit().putInt(key, (Integer) data).commit();
            else if (data instanceof Boolean)
                hnMobilePrefs.edit().putBoolean(key, (Boolean) data).commit();
            else if (data instanceof Float)
                hnMobilePrefs.edit().putFloat(key, (Float) data).commit();
            else if (data instanceof Long) hnMobilePrefs.edit().putLong(key, (Long) data).commit();
            else throw new Exception("Unknown data type");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
