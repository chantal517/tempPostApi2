package com.temperature.send_data.util;

import com.google.gson.Gson;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Jabari on 07/14/2016.
 */
@SuppressWarnings("unused")
public class Payload {
    String deviceID;
    String location;
    Date startDatetime;
    Date endDatetime;
    List<Map<String, String>> temperatureData;

    @SuppressWarnings("unused")
    public Payload() {

    }

    @SuppressWarnings("unused")
    public Payload(
            String deviceID,
            String location,
            Date startDatetime,
            Date endDatetime,
            List<Map<String, String>> temperatureData) {
        this.deviceID = deviceID;
        this.location = location;
        this.startDatetime = startDatetime;
        this.endDatetime = endDatetime;
        this.temperatureData = temperatureData;
    }

    // Get methods
    @SuppressWarnings("unused") public String getDeviceID() { return this.deviceID; }
    @SuppressWarnings("unused") public String getLocation() { return this.location; }
    @SuppressWarnings("unused") public Date getStartDatetime() { return this.startDatetime; }
    @SuppressWarnings("unused") public Date getEndDatetime() {return this.endDatetime; }
    @SuppressWarnings("unused") public List<Map<String, String>> getTemperatureData() { return this.temperatureData; }

    // Set methods
    @SuppressWarnings("unused") public void setDeviceID(String deviceID) { this.deviceID = deviceID; }
    @SuppressWarnings("unused") public void setLocation(String location) { this.location = location; }
    @SuppressWarnings("unused") public void setStartDatetime(Date startDatetime) { this.startDatetime = startDatetime; }
    @SuppressWarnings("unused") public void setEndDatetime(Date endDatetime) { this.endDatetime = endDatetime; }
    @SuppressWarnings("unused") public void setTemperatureData(List<Map<String, String>> temperatureData) { this.temperatureData = temperatureData; }

    /**
     * Returns this object as a JSON String
     * @return
     */
    public String toJSON() {
        return new Gson().toJson(this);
    }
}