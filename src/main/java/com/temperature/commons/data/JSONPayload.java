package com.temperature.commons.data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import com.temperature.commons.exception.InvalidDataException;
import com.temperature.commons.exception.MyNullPointerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Chantal Lewis
 * @author Jabari Dash
 *
 * Created by Jabari && Chantal on 07/14/2016.
 */
@SuppressWarnings("unused")
public final class JSONPayload extends AbstractPayload {
    static Logger log = LoggerFactory.getLogger(JSONPayload.class);

    private String device_id;
    private String location;
    private Date start_datetime;
    private Date stop_datetime;
    private List<TemperaturePayload> temperature_data;

    public JSONPayload() {
        ;
    }

    /**
     * Builds a {@code JSONPayload} from a {@code JSONPayload.Builder} class
     * @param builder
     */
    @SuppressWarnings("unused")
    public JSONPayload(JSONPayload.Builder builder){
        log.info("Building JSONPayload from JSONPayload.Builder");
        this.device_id        = builder.device_id;
        this.location        = builder.location;
        this.start_datetime   = builder.start_datetime;
        this.stop_datetime    = builder.stop_datetime;
        this.temperature_data = builder.temperature_data;
    }

    // Accessor methods
    @SuppressWarnings("unused") public String getDeviceID() { return this.device_id; }
    @SuppressWarnings("unused") public String getLocation() { return this.location; }
    @SuppressWarnings("unused") public Date getStartDatetime() { return this.start_datetime; }
    @SuppressWarnings("unused") public Date getStopDatetime() { return this.stop_datetime; }
    @SuppressWarnings("unused") public List<TemperaturePayload> getTemperatureData() { return this.temperature_data; }

    @SuppressWarnings("unused") public void setDeviceID(String device_id) { this.device_id = device_id; }
    @SuppressWarnings("unused") public void setLocation(String location) { this.location = location; }
    @SuppressWarnings("unused") public void setStartDatetime(Date start_datetime) { this.start_datetime = start_datetime; }
    @SuppressWarnings("unused") public void setStopDatetime(Date stopDatetime) { this.stop_datetime = stopDatetime; }
    @SuppressWarnings("unused") public void setTemperatureData(List<TemperaturePayload> temperature_data) { this.temperature_data = temperature_data; }

    /**
     *
     * @return Returns the {@code JSONPayload} object as a {@code HashMap<String, Object>}.
     */
    public HashMap<String, Object> toMap(){
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put(deviceIDTitle, this.device_id);
        map.put(locationTitle, this.location);
        map.put(startDatetimeTitle, this.start_datetime.toString());
        map.put(stopDatetimeTitle, this.stop_datetime.toString());
        map.put(temperatureDataTitle, TemperaturePayload.toMapList(this.temperature_data));

        return map;
    }

    /**
     *
     * @param jsonPayloadMap
     * @return
     * @throws MyNullPointerException
     * @throws ParseException
     * @throws ClassCastException
     * @throws InvalidDataException
     */
    public static JSONPayload fromMap(Map<String, Object> jsonPayloadMap)
            throws MyNullPointerException, ParseException, ClassCastException, InvalidDataException {

        // Convert dates from Strings to Date objects
        SimpleDateFormat formatter = new SimpleDateFormat(AbstractPayload.datetimeFormat);
        Date start_datetime = formatter.parse((String) jsonPayloadMap.get(AbstractPayload.startDatetimeTitle));
        Date stop_datetime = formatter.parse((String) jsonPayloadMap.get(AbstractPayload.stopDatetimeTitle));

        // Build JSONPayload
        JSONPayload jsonPayload = new JSONPayload.Builder()
                .deviceID((String) jsonPayloadMap.get(AbstractPayload.deviceIDTitle))
                .location((String) jsonPayloadMap.get(AbstractPayload.locationTitle))
                .startDatetime(start_datetime)
                .stopDatetime(stop_datetime)
                .temperatureData(TemperaturePayload.toTemperaturePayloadList((List<Map<String, Object>>) jsonPayloadMap.get(AbstractPayload.temperatureDataTitle)))
                .build();

        return jsonPayload;
    }

    /**
     * Used to build {@code JSONPayload} object with chained calls for setting attributes
     * Also guarantees that all necessary fields will be initialized
     */
    public static class Builder {
        private String device_id;
        private String location;
        private Date start_datetime;
        private Date stop_datetime;
        private List<TemperaturePayload> temperature_data;

        /**
         *
         * @param device_id
         * @return
         * @throws MyNullPointerException
         */
        public Builder deviceID(String device_id) throws MyNullPointerException {
            if (device_id == null || device_id.equals("")) throw new MyNullPointerException(deviceIDTitle + isEmptyOrNull);

            this.device_id = device_id;
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
         * @param start_datetime
         * @return
         * @throws MyNullPointerException
         */
        public Builder startDatetime(Date start_datetime) throws MyNullPointerException {
            if (start_datetime == null || start_datetime.toString().equals(""))throw new MyNullPointerException(startDatetimeTitle + isEmptyOrNull);

            this.start_datetime = start_datetime;
            return this;
        }

        /**
         *
         * @param stop_datetime
         * @return
         * @throws MyNullPointerException
         */
        public Builder stopDatetime(Date stop_datetime) throws MyNullPointerException {
            if (stop_datetime == null || stop_datetime.toString().equals("") )throw new MyNullPointerException(startDatetimeTitle + isEmptyOrNull);

            this.stop_datetime = stop_datetime;
            return this;
        }

        /**
         *
         * @param temperature_data
         * @return
         * @throws MyNullPointerException
         */
        public Builder temperatureData(List<TemperaturePayload> temperature_data) throws MyNullPointerException {
            if (temperature_data == null || temperature_data.isEmpty()) throw new MyNullPointerException(temperatureDataTitle + isEmptyOrNull);

            this.temperature_data = temperature_data;
            return this;
        }

        /**
         * Builds a new {@code JSONPayload} object
         * @return
         * @throws MyNullPointerException
         */
        public JSONPayload build() throws MyNullPointerException {

            // Check that all required fields have been initialized
            if (this.device_id == null || this.device_id.equals("")) throw new MyNullPointerException(deviceIDTitle + isEmptyOrNull);
            if (this.location == null || this.location.equals("")) throw new MyNullPointerException(locationTitle + isEmptyOrNull);
            if (this.start_datetime == null || this.start_datetime.toString().equals(""))throw new MyNullPointerException(startDatetimeTitle + isEmptyOrNull);
            if (this.stop_datetime == null || this.stop_datetime.toString().equals(""))throw new MyNullPointerException(stopDatetimeTitle + isEmptyOrNull);
            if (this.temperature_data == null || temperature_data.isEmpty())throw new MyNullPointerException(temperatureDataTitle + isEmptyOrNull);

            log.info("All fields are non-null, returning valid JSONPayload object");
            return new JSONPayload(this);
        }
    }
}

