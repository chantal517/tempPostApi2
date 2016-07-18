package com.temperature.controller.validator;

import com.temperature.commons.data.*;
import com.temperature.commons.exception.MyNullPointerException;
import com.temperature.commons.exception.PayloadNotRecognizedException;
import org.apache.log4j.Logger;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Jabari Dash
 * @author Chantal Lewis
 *
 * Created by Jabari on 07/14/2016.
 */
@SuppressWarnings("unused")
public class PayloadValidator {
    static Logger log = Logger.getLogger(PayloadValidator.class);
    private AbstractPayload payload;

    /**
     *
     * @param payloadMap
     * @param payloadClass
     * @throws MyNullPointerException
     * @throws PayloadNotRecognizedException
     */
    @SuppressWarnings("unused")
    public PayloadValidator(Map<String, Object> payloadMap, Class payloadClass) throws MyNullPointerException, PayloadNotRecognizedException, ParseException {

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

    public boolean temperatureDataValid() throws PayloadNotRecognizedException {
        boolean isValid = false;

        if (this.payload.getClass() == JSONPayload.class) {
            // TODO - Check that all datetime fields are non-empty / non-null
            // TODO - Check that all datetime fields fall within the start_datetime and stop_datetime fields (chronologically)
            // TODO - Check that all temperature fields are non-empty / non-null
            // TODO - Return false if any of the above cases are false

            isValid = true;

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
