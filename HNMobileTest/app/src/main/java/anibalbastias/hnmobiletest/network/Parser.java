package anibalbastias.hnmobiletest.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import anibalbastias.hnmobiletest.model.response.HNNewsResponse;
import anibalbastias.hnmobiletest.network.eventbus.events.HNMobileEvents;

public class Parser {

    private static Gson gson = new GsonBuilder().create();

    public static HNNewsResponse parseHNNewsResponse(JsonObject data) {
        return gson.fromJson(data, HNNewsResponse.class);
    }

    public static HNMobileEvents.HNNewsEvent parseHNNewsEvent(JsonObject data) {
        return gson.fromJson(data, HNMobileEvents.HNNewsEvent.class);
    }

    public static HNMobileEvents.HNNewsRefreshEvent parseHNNewsRefreshEvent(JsonObject data) {
        return gson.fromJson(data, HNMobileEvents.HNNewsRefreshEvent.class);
    }

    public static HNMobileEvents.HNNewsLoadMoreEvent parseHNNewsLoadMoreEvent(JsonObject data) {
        return gson.fromJson(data, HNMobileEvents.HNNewsLoadMoreEvent.class);
    }
}
