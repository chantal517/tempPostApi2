package com.temperature.send_data.commons.data.model;

import java.util.Date;
import java.util.HashMap;

/**
 * Created by Jabari on 07/16/2016.
 */
@SuppressWarnings("unused")
public class MongoPayload extends AbstractPayload {
    private String deviceID;
    private String location;
    private float temperature;
    private Date datetime;

    public MongoPayload(Builder builder) {
        this.deviceID       = builder.deviceID;
        this.location       = builder. location;
        this.temperature    = builder.temperature;
        this.datetime       = builder.datetime;
    }

    public HashMap<String, Object> toMap() {
        HashMap<String, Object> map = new HashMap<String, Object>();

        map.put(deviceIDTitle, deviceID);
        map.put(locationTitle, location);
        map.put(temperatureTitle, temperature);
        map.put(datetimeTitle, datetime.toString());

        return map;
    }

    /**
     * Builds a MongoPayload object
     */
    public static class Builder {
        private String deviceID;
        private String location;
        private float temperature;
        private Date datetime;

        public Builder deviceID(String deviceID){
            if(deviceID == null || deviceID.equals("")) throw new NullPointerException(deviceIDTitle + isEmptyOrNull);
            this.deviceID = deviceID;
            return this;
        }
        public Builder location(String location){
            if(location == null || location.equals("")) throw new NullPointerException(locationTitle + isEmptyOrNull);
            this.location = location;
            return this;
        }
        public Builder temperature(float temperature){
            this.temperature = temperature;
            return this;
        }
    }
}


