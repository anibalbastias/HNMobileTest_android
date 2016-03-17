package anibalbastias.hnmobiletest.network;

/**
 * Created by anibalbastias on 09-06-15.
 */

import android.content.Context;

import java.io.IOException;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.URI;
import java.util.List;
import java.util.Map;

import anibalbastias.hnmobiletest.model.storage.StorageAPI;

public class CustomCookieManager extends CookieManager {

    private final String SESSION_ID = "sessionid";
    private final String TOKEN = "csrftoken";

    private Context context;

    public CustomCookieManager(Context context) {
        super.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
        this.context = context;
    }

    @Override
    public void put(URI uri, Map<String, List<String>> responseHeaders) throws IOException {

        super.put(uri, responseHeaders);

        if (responseHeaders == null || responseHeaders.get("Set-Cookie") == null)
            return;

        for (String possibleSessionCookieValues : responseHeaders.get("Set-Cookie")) {
            if (possibleSessionCookieValues != null) {
                for (String possibleSessionCookie : possibleSessionCookieValues.split(";")) {
                    if (possibleSessionCookie.startsWith(SESSION_ID) && possibleSessionCookie.contains("=")) {
                        String session = possibleSessionCookie.split("=")[1];
                        new StorageAPI(context).saveSessionId(session);
                    } else if (possibleSessionCookie.startsWith(TOKEN) && possibleSessionCookie.contains("=")) {
                        String token = possibleSessionCookie.split("=")[1];
                        new StorageAPI(context).saveCsrftoken(token);
                    }
                }
            }
        }
    }
}
