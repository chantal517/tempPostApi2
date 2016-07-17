package com.temperature.commons.data;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

/**
 * @author Jabari Dash
 * @author Chantal Lewis
 */
@SuppressWarnings("unused")
public class MongoPayload extends AbstractPayload {
    static Logger log = Logger.getLogger(MongoPayload.class);

    private String deviceID;
    private String location;
    private double temperature;
    private Date datetime;

    public MongoPayload() {
        // TODO - PLACEHOLDER
    }

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

    public static MongoPayload fromMap(Map<String, Object> map) throws MyNullPointerException {
        // TODO - Implement SimpleDateFormat to convert String to Dates

        MongoPayload mongoPayload = new Builder()
                .deviceID((String) map.get(AbstractPayload.deviceIDTitle))
                .location((String) map.get(AbstractPayload.locationTitle))
                .datetime(null)
                .temperature((Double) map.get(AbstractPayload.temperatureTitle))
                .build();

        return mongoPayload;
    }

    /**
     * Builds a MongoPayload object
     */
    public static class Builder {
        private String deviceID;
        private String location;
        private double temperature;
        private Date datetime;

        /**
         *
         * @param deviceID
         * @return
         * @throws MyNullPointerException
         */
        public Builder deviceID(String deviceID)throws MyNullPointerException {
            if(deviceID == null || deviceID.equals("")) throw new MyNullPointerException(deviceIDTitle + isEmptyOrNull);
            this.deviceID = deviceID;

            return this;
        }

        /**
         *
         * @param location
         * @return
         * @throws MyNullPointerException
         */
        public Builder location(String location) throws MyNullPointerException {
            if(location == null || location.equals("")) throw new MyNullPointerException(locationTitle + isEmptyOrNull);
            this.location = location;

            return this;
        }

        public Builder datetime(Date datetime) throws MyNullPointerException {
            if(datetime == null || datetime.toString().equals("")) throw new MyNullPointerException(datetimeTitle + isEmptyOrNull);

            this.datetime = datetime;
            return this;
        }

        /**
         *
         * @param temperature
         * @return
         */
        public Builder temperature(double temperature){
            this.temperature = temperature;

            return this;
        }

        public MongoPayload build() {
            // TODO - Implement null pointer checks

            return new MongoPayload(this);
        }
    }
}


