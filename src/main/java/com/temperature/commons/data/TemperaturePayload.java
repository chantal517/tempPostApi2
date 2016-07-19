package com.temperature.commons.data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import com.temperature.commons.exception.InvalidDataException;
import com.temperature.commons.exception.MyNullPointerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Jabari Dash
 *
 * Created by Jabari on 07/16/2016.
 */
@SuppressWarnings("unused")
public class TemperaturePayload extends AbstractPayload {
    static Logger log = LoggerFactory.getLogger(TemperaturePayload.class);

    private double temperature;
    private Date datetime;

    /**
     *
     * @param builder
     */
    public TemperaturePayload(TemperaturePayload.Builder builder) {
        log.info("Building TemperaturePayload from TemperaturePayload.Builder");
        this.temperature = builder.temperature;
        this.datetime    = builder.datetime;
    }

    // Accessor methods
    @SuppressWarnings("unused") public double getTemperature() { return this.temperature; }
    @SuppressWarnings("unused") public Date getDatetime() { return this.datetime; }

    /**
     *
     * @return
     */
    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(this.temperatureTitle, this.temperature);
        map.put(this.datetimeTitle, this.datetime.toString());

        return map;
    }

    public static TemperaturePayload fromMap(Map<String, Object> map)
            throws MyNullPointerException, ParseException, ClassCastException, InvalidDataException {

        SimpleDateFormat formatter = new SimpleDateFormat(datetimeFormat);
        Date datetime = formatter.parse((String) map.get(datetimeTitle));

        // Make sure that the temperature is a a number, and not a string
        if (map.get(temperatureTitle).getClass() == String.class) {
             throw new InvalidDataException("Temperature was a String");
        }

        TemperaturePayload temperaturePayload = new TemperaturePayload.Builder()
                .datetime(datetime)
                .temperature(Double.parseDouble(map.get(temperatureTitle).toString())) // Compensate for type casting issues
                .build();

        return temperaturePayload;
    }

    /**
     *
     * @param mapList
     * @return
     * @throws MyNullPointerException
     */
    @SuppressWarnings("unused")
    public static List<TemperaturePayload> toTemperaturePayloadList(List<Map<String, Object>> mapList)
            throws MyNullPointerException, ParseException, InvalidDataException {
        List<TemperaturePayload> temperaturePayloadList = new ArrayList<TemperaturePayload>();

        for (Map<String, Object> map : mapList) {
            temperaturePayloadList.add(fromMap(map));
        }

        return temperaturePayloadList;
    }

    /**
     *
     * @param temperaturePayloadList
     * @return
     */
    public static List<Map<String, Object>> toMapList(List<TemperaturePayload> temperaturePayloadList) {
        List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();

        for (TemperaturePayload temperaturePayload : temperaturePayloadList) {
            mapList.add(temperaturePayload.toMap());
        }
        return mapList;
    }

    /**
     * Builds a TemperaturePayload object
     */
    public static class Builder {
        private double temperature;
        private Date datetime;

        /**
         * Sets the temperature attribute in the Builder
         * @param temperature
         * @return
         */
        public Builder temperature(double temperature) throws InvalidDataException {
            if (temperature > Double.MAX_VALUE
//                    || temperature < Double.MIN_VALUE     // Causing condition to be true, and exception thrown when 0 is passed
                    || temperature == Double.NaN
                    || temperature == Double.POSITIVE_INFINITY
                    || temperature == Double.NEGATIVE_INFINITY
            ) throw new InvalidDataException(temperatureTitle + isNotAValidNumber);

            this.temperature = temperature;
            return this;
        }

        /**
         * Sets the datetime attribute in the Builder
         * @param datetime
         * @return
         * @throws MyNullPointerException If the Date object passed is null
         */
        public Builder datetime(Date datetime) throws MyNullPointerException {
            if (datetime == null || datetime.toString().equals("")) throw new MyNullPointerException(datetimeTitle + isEmptyOrNull);

            this.datetime = datetime;
            return this;
        }

        /**
         *
         * @return
         * @throws MyNullPointerException
         */
        public TemperaturePayload build() throws MyNullPointerException {
            if (this.datetime == null || datetime.toString().equals("")) throw new MyNullPointerException(datetimeTitle + isEmptyOrNull);

            return new TemperaturePayload(this);
        }
    }
}
