package com.temperature.controller.validator;

import com.temperature.commons.data.*;
import com.temperature.commons.exception.InvalidDataException;
import com.temperature.commons.exception.MyNullPointerException;
import com.temperature.commons.exception.PayloadNotRecognizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Jabari Dash
 * @author Chantal Lewis
 *
 * Created by Jabari on 07/14/2016.
 */
@SuppressWarnings("unused")
public class PayloadValidator {
    static Logger log = LoggerFactory.getLogger(PayloadValidator.class);
    private AbstractPayload payload;

    /**
     *
     * @param payloadMap
     * @param payloadClass
     * @throws MyNullPointerException
     * @throws PayloadNotRecognizedException
     */
    @SuppressWarnings("unused")
    public PayloadValidator(Map<String, Object> payloadMap, Class payloadClass)
            throws MyNullPointerException, PayloadNotRecognizedException, ParseException, InvalidDataException {

        log.info("Checking Payload type");
        if (payloadClass == JSONPayload.class) {
            log.info("Creating JSONPayload");
            this.payload = JSONPayload.fromMap(payloadMap);

        } else if (payloadClass == MongoPayload.class) {
            log.info("Creating MongoPayload");
            this.payload = MongoPayload.fromMap(payloadMap);

        } else if (payloadClass == TemperaturePayload.class) {
            log.info("Creating TemperaturePayload");
            this.payload = TemperaturePayload.fromMap(payloadMap);

        } else {
            String message = "Payload class not supported";
            log.error(message);
            throw new PayloadNotRecognizedException(message);
        }
    }

    // TODO - Check for duplicate TemperaturePayload objects?
    public boolean temperatureDataValid()
            throws PayloadNotRecognizedException, ParseException, MyNullPointerException, InvalidDataException {
        boolean isValid = true;

        if (this.payload.getClass() == JSONPayload.class) {
            JSONPayload tempJSONPayload = (JSONPayload) this.payload;

            Date startDatetime = tempJSONPayload.getStartDatetime();
            Date stopDatetime = tempJSONPayload.getStopDatetime();

            // TODO - Make sure startDatetime and stopDateTime have not passed
            Date now = new Date();

            if (stopDatetime.after(now)) {
                String message = "stop_datetime: " + startDatetime.toString() + " occurs after now: " + now.toString();
                log.error(message);
                throw new InvalidDataException(message);

            }

            log.info("Making sure that all dates occur between " + AbstractPayload.startDatetimeTitle + " and " + AbstractPayload.stopDatetimeTitle);
            List<TemperaturePayload> temperaturePayloadList = tempJSONPayload.getTemperatureData();

            // Make sure that all dates fit within the designated range (start-stop)
            for (TemperaturePayload temperaturePayload : temperaturePayloadList) {
                if (temperaturePayload.getDatetime().before(startDatetime) || temperaturePayload.getDatetime().after(stopDatetime)) {
                    log.warn(temperaturePayload.getDatetime().toString()
                            + " does not occur between "
                            + AbstractPayload.startDatetimeTitle
                            + ": " + startDatetime.toString()
                            + " and " + AbstractPayload.stopDatetimeTitle +
                            ": " + stopDatetime.toString());
                    isValid = false;
                    break;
                }
            }
            if (isValid == true) {
                log.info("All dates occur between "
                        + AbstractPayload.startDatetimeTitle
                        + ": " + startDatetime.toString()
                        + " and " + AbstractPayload.stopDatetimeTitle +
                        ": " + stopDatetime.toString());
            }

        } else if (this.payload.getClass() == MongoPayload.class) {
            // TODO - Check that temperature is non-empty / non-null
            // TODO - Check that datetime is non-empty / non-null
            // TODO - Return false if any of the above cases are false

            isValid = true;

        } else if (this.payload.getClass() == TemperaturePayload.class) {
            // TODO - Check that temperature is non-empty / non-null
            // TODO - Check that datetime is non-empty / non-null
            // TODO - Return false if any of the above cases are false

            isValid = true;

        } else {
            String message = "Payload class not supported";
            log.error(message);
            throw new PayloadNotRecognizedException(message);
        }

        return isValid;
    }

    @SuppressWarnings("unused")
    public AbstractPayload getPayload() { return this.payload; }

    @SuppressWarnings("unused")
    public boolean hasXSS(HashMap<String, Object> jsonPayload) {
        // TODO - Implement XSS check

        return true;
    }
}
