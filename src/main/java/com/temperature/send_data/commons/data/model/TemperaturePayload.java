package com.temperature.send_data.commons.data.model;

import java.util.Date;
import java.util.HashMap;

/**
 * Created by Jabari on 07/16/2016.
 */
@SuppressWarnings("unused")
public class TemperaturePayload extends AbstractPayload {
    private double temperature;
    private Date datetime;

    public TemperaturePayload(TemperaturePayload.Builder builder) {
        this.temperature = builder.temperature;
        this.datetime    = builder.datetime;
    }

    // Accessor methods
    @SuppressWarnings("unused") public double getTemperature() { return this.temperature; }
    @SuppressWarnings("unused") public Date getDatetime() { return this.datetime; }

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
         * @throws NullPointerException If the Date object passed is null
         */
        public Builder datetime(Date datetime) throws NullPointerException {
            if (datetime == null) throw new NullPointerException(datetimeTitle + isEmptyOrNull);

            this.datetime = datetime;

            return this;
        }

        public TemperaturePayload build() throws NullPointerException {
            if (this.datetime == null) throw new NullPointerException(datetimeTitle + isEmptyOrNull);

            return new TemperaturePayload(this);
        }
    }
}
