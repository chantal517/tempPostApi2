package com.temperature.commons.data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import com.temperature.commons.exception.MyNullPointerException;
import org.apache.log4j.Logger;

/**
 * Created by Jabari on 07/16/2016.
 */
@SuppressWarnings("unused")
public class TemperaturePayload extends AbstractPayload {
    static Logger log = Logger.getLogger(TemperaturePayload.class);

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

    /**
     *
     * @param mapList
     * @return
     * @throws MyNullPointerException
     */
    @SuppressWarnings("unused")
    public static List<TemperaturePayload> toTemperaturePayloadList(List<Map<String, Object>> mapList) throws MyNullPointerException, ParseException {
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

    public static TemperaturePayload fromMap(Map<String, Object> map) throws MyNullPointerException, ParseException, ClassCastException {
        // TODO - Implement SimpleDateFormat to convert String to Dates
        SimpleDateFormat formatter = new SimpleDateFormat(AbstractPayload.datetimeFormat);
        Date datetime = formatter.parse((String) map.get(AbstractPayload.datetimeTitle));

        TemperaturePayload temperaturePayload = new TemperaturePayload.Builder()
                .datetime(datetime)
                .temperature((Double) map.get(AbstractPayload.temperatureTitle))
                .build();

        return temperaturePayload;
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
        public Builder temperature(double temperature) {
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
