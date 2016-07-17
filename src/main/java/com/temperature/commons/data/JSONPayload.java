package com.temperature.commons.data;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;

/**
 * @author Chantal Lewis
 * @author Jabari Dash
 *
 * Created by Jabari && Chantal on 07/14/2016.
 */
@SuppressWarnings("unused")
public final class JSONPayload extends AbstractPayload {
    static Logger log = Logger.getLogger(JSONPayload.class);

    private String deviceID;
    private String location;
    private Date startDatetime;
    private Date stopDatetime;
    private List<Map<String, Object>> temperatureData;

    /**
     * Builds a {@code JSONPayload} from a {@code JSONPayload.Builder} class
     * @param builder
     */
    @SuppressWarnings("unused")
    public JSONPayload(JSONPayload.Builder builder){
        this.deviceID        = builder.deviceID;
        this.location        = builder.location;
        this.startDatetime   = builder.startDatetime;
        this.stopDatetime    = builder.stopDatetime;
        this.temperatureData = builder.temperatureData;
    }

    // Accessor methods
    @SuppressWarnings("unused") public String getDeviceID() { return this.deviceID; }
    @SuppressWarnings("unused") public String getLocation() { return this.location; }
    @SuppressWarnings("unused") public Date getStartDatetime() { return this.startDatetime; }
    @SuppressWarnings("unused") public Date getStopDatetime() {return this.stopDatetime; }
    @SuppressWarnings("unused") public List<Map<String, Object>> getTemperatureData() { return this.temperatureData; }

    /**
     *
     * @return Returns the {@code JSONPayload} object as a {@code HashMap<String, Object>}.
     */
    public HashMap<String, Object> toMap(){
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put(deviceIDTitle, this.deviceID);
        map.put(locationTitle, this.location);
        map.put(startDatetimeTitle, this.startDatetime.toString());
        map.put(stopDatetimeTitle, this.stopDatetime.toString());
        map.put(temperatureDataTitle, this.temperatureData);

        return map;
    }

    /**
     *
     * @param jsonPayloadMap
     * @return
     * @throws MyNullPointerException
     */
    public static JSONPayload fromMap(Map<String, Object> jsonPayloadMap)throws MyNullPointerException {
        // TODO - Implement SimpleDateFormat to convert String to Dates

        JSONPayload jsonPayload = new JSONPayload.Builder()
                .deviceID((String) jsonPayloadMap.get(AbstractPayload.deviceIDTitle))
                .location((String) jsonPayloadMap.get(AbstractPayload.locationTitle))
                .startDatetime(null)
                .stopDatetime(null)
                .temperatureData((List<Map<String, Object>>) jsonPayloadMap.get(AbstractPayload.temperatureDataTitle))
                .build();

        return jsonPayload;
    }

    /**
     * Used to build {@code JSONPayload} object with chained calls for setting attributes
     * Also guarantees that all necessary fields will be initialized
     */
    public static class Builder {

        private String deviceID;
        private String location;
        private Date startDatetime;
        private Date stopDatetime;
        private List<Map<String, Object>> temperatureData;

        /**
         *
         * @param deviceID
         * @return
         * @throws MyNullPointerException
         */
        public Builder deviceID(String deviceID) throws MyNullPointerException {
            if (deviceID == null || deviceID.equals("")) throw new MyNullPointerException(deviceIDTitle + isEmptyOrNull);

            this.deviceID = deviceID;
            return this;
        }

        /**
         *
         * @param location
         * @return
         * @throws MyNullPointerException
         */
        public Builder location(String location) throws MyNullPointerException{
            if (location == null || location.equals("")) throw new MyNullPointerException(locationTitle + isEmptyOrNull);

            this.location = location;
            return this;
        }

        /**
         *
         * @param startDatetime
         * @return
         * @throws MyNullPointerException
         */
        public Builder startDatetime(Date startDatetime) throws MyNullPointerException {
            if (startDatetime == null || startDatetime.toString().equals(""))throw new MyNullPointerException(startDatetimeTitle + isEmptyOrNull);

            this.startDatetime = startDatetime;
            return this;
        }

        /**
         *
         * @param stopDatetime
         * @return
         * @throws MyNullPointerException
         */
        public Builder stopDatetime(Date stopDatetime) throws MyNullPointerException {
            if (stopDatetime == null || stopDatetime.toString().equals("") )throw new MyNullPointerException(startDatetimeTitle + isEmptyOrNull);

            this.stopDatetime = stopDatetime;
            return this;
        }

        /**
         *
         * @param temperatureData
         * @return
         * @throws MyNullPointerException
         */
        public Builder temperatureData(List<Map<String, Object>> temperatureData) throws MyNullPointerException {
            if (temperatureData == null)throw new MyNullPointerException(temperatureDataTitle + isEmptyOrNull);

            this.temperatureData = temperatureData;
            return this;
        }

        /**
         * Builds a new {@code JSONPayload} object
         * @return
         * @throws MyNullPointerException
         */
        public JSONPayload build() throws MyNullPointerException {

            // Check that all required fields have been initialized
            if (this.deviceID == null || this.deviceID.equals("")) throw new MyNullPointerException(deviceIDTitle + isEmptyOrNull);
            if (this.location == null || this.location.equals("")) throw new MyNullPointerException(locationTitle + isEmptyOrNull);
            if (this.startDatetime == null || this.startDatetime.toString().equals("") )throw new MyNullPointerException(startDatetimeTitle + isEmptyOrNull);
            if (this.stopDatetime == null || this.stopDatetime.toString().equals("") )throw new MyNullPointerException(startDatetimeTitle + isEmptyOrNull);
            if (this.temperatureData == null)throw new MyNullPointerException(temperatureDataTitle + isEmptyOrNull);

            return new JSONPayload(this);
        }
    }
}

