package com.temperature.commons.data;

import java.util.Date;
import java.util.HashMap;
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
    public HashMap<String, Object> toMap() {
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put(this.temperatureTitle, this.temperature);
        map.put(this.datetimeTitle, this.datetime.toString());

        return map;
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
            if (datetime == null) throw new MyNullPointerException(datetimeTitle + isEmptyOrNull);

            this.datetime = datetime;

            return this;
        }

        /**
         *
         * @return
         * @throws MyNullPointerException
         */
        public TemperaturePayload build() throws MyNullPointerException {
            if (this.datetime == null) throw new MyNullPointerException(datetimeTitle + isEmptyOrNull);

            return new TemperaturePayload(this);
        }
    }
}
