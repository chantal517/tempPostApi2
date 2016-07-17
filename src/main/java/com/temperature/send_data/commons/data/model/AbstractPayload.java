package com.temperature.send_data.commons.data.model;

import com.google.gson.Gson;

import java.util.Map;

/**
 * Created by Jabari on 07/16/2016.
 */
public abstract class AbstractPayload {

    // Titles for use with JSON construction
    @SuppressWarnings("unused") public final static String deviceIDTitle        = "device_id";
    @SuppressWarnings("unused") public final static String locationTitle        = "location";
    @SuppressWarnings("unused") public final static String startDatetimeTitle   = "start_datetime";
    @SuppressWarnings("unused") public final static String stopDatetimeTitle    = "stop_datetime";
    @SuppressWarnings("unused") public final static String temperatureDataTitle = "temperature_data";
    @SuppressWarnings("unused") public final static String temperatureTitle     = "temperature";
    @SuppressWarnings("unused") public final static String datetimeTitle        = "datetime";

    // Other reused Strings
    @SuppressWarnings("unused") public final static String isEmptyOrNull               = " is empty or null";

    // Abstract Methods that must be implemented in all classes that implement this interface
    public abstract Map<String, Object> toMap();

    @SuppressWarnings("unused")
    public String toJSON() {
        return new Gson().toJson(this);
    }
}
