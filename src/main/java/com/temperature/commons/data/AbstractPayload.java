package com.temperature.commons.data;

import com.google.gson.Gson;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Jabari Dash
 * @author Chantal Lewis
 *
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
    @SuppressWarnings("unused") public final static String datetimeFormat       = "EEE MMM dd HH:mm:ss zzz yyyy";
    @SuppressWarnings("unused") public final static String isEmptyOrNull        = " is empty or null";
    @SuppressWarnings("unused") public final static String isNotAValidNumber    = " is not a valid temperature value";

    // Abstract Methods that must be implemented in all classes that implement this interface

    /**
     * Abstract method for returning Payloads as {@code HashMap<String, Object>} objects.
     * @return Returns the {@code HashMap} representation of the {@code AbstractPayload} object
     */
    public abstract Map<String, Object> toMap();

    /**
     *
     * @return the JSON {@code String} representation of the {@code AbstractPayload} object
     */
    @SuppressWarnings("unused")
    public String toJSON() {
        return new Gson().toJson(this);
    }
}
