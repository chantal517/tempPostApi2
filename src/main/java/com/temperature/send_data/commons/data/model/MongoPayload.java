package com.temperature.send_data.commons.data.model;

import java.util.Date;
import java.util.HashMap;

/**
 * @author Jabari Dash
 * @author Chantal Lewis
 */
@SuppressWarnings("unused")
public class MongoPayload extends AbstractPayload {
    private String deviceID;
    private String location;
    private float temperature;
    private Date datetime;

    /**
     *
     * @param builder
     */
    public MongoPayload(MongoPayload.Builder builder) {
        this.deviceID       = builder.deviceID;
        this.location       = builder.location;
        this.temperature    = builder.temperature;
        this.datetime       = builder.datetime;
    }

    /**
     *
     * @return
     */
    public HashMap<String, Object> toMap() {
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put(this.deviceIDTitle, this.deviceID);
        map.put(this.locationTitle, this.location);
        map.put(this.temperatureTitle, this.temperature);
        map.put(this.datetimeTitle, this.datetime.toString());

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

        /**
         *
         * @param deviceID
         * @return
         */
        public Builder deviceID(String deviceID)throws NullPointerException {
            if(deviceID == null || deviceID.equals("")) throw new NullPointerException(deviceIDTitle + isEmptyOrNull);
            this.deviceID = deviceID;

            return this;
        }

        /**
         *
         * @param location
         * @return
         */
        public Builder location(String location) throws NullPointerException {
            if(location == null || location.equals("")) throw new NullPointerException(locationTitle + isEmptyOrNull);
            this.location = location;

            return this;
        }

        /**
         *
         * @param temperature
         * @return
         */
        public Builder temperature(float temperature){
            this.temperature = temperature;

            return this;
        }
    }
}


