package com.temperature.processor;

import com.temperature.commons.data.AbstractPayload;
import com.temperature.commons.data.JSONPayload;
import com.temperature.commons.exception.MyNullPointerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * Created by Jabari on 07/16/2016.
 */
@SuppressWarnings("unused")
public class TemperatureDataProcessor {
    static Logger log = LoggerFactory.getLogger(TemperatureDataProcessor.class);

    public TemperatureDataProcessor() {
       ;
    }

    /**
     * Send a {@code JSONPayload} to the MongoDB database
     * @param jsonPayload
     * @return
     */
    public boolean sendToMongoDB(JSONPayload jsonPayload) {
        return false;
    }

    /**
     * Returns a {@code JSONPayload} object containing an array of {@code TemperaturePayload} objects
     * in between the {@code starDatetime} and {@code stopDatetime} (inclusive).
     *
     * @param startDatetime
     * @param stopDatetime
     * @param deviceID
     * @return
     * @throws MyNullPointerException
     */
    public JSONPayload getFromMongoDB(Date startDatetime, Date stopDatetime, String deviceID) throws MyNullPointerException {
        if (deviceID == null || deviceID.equals("")) {
            String message = AbstractPayload.deviceIDTitle + AbstractPayload.isEmptyOrNull;
            log.error(message);
            throw new MyNullPointerException(message);
        }

        if (stopDatetime == null && startDatetime != null) {
            // TODO - Get all entries after the start date

        } else if (startDatetime == null && stopDatetime != null) {
            // TODO - Get all entries up to the stop date

        } else if (startDatetime != null && stopDatetime != null) {
            // TODO - Get all entries in between the two dates

        } else if (startDatetime == null && stopDatetime == null) {
            // Throw MyNullPointerException if both the start and stop dates are null
            String message = AbstractPayload.startDatetimeTitle
                    + AbstractPayload.isEmptyOrNull
                    + " and " + AbstractPayload.stopDatetimeTitle
                    + AbstractPayload.isEmptyOrNull;

            log.error(message);
            throw new MyNullPointerException(message);
        }

        return null;
    }
}
