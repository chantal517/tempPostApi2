package com.temperature.commons;

import com.temperature.commons.data.JSONPayload;
import com.temperature.commons.exception.InvalidDataException;
import com.temperature.commons.exception.MyNullPointerException;
import com.temperature.commons.data.TemperaturePayload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.*;

/**
 * Created by Jabari && Chantal on 07/16/2016.
 */
public class Util {
    static Logger log = LoggerFactory.getLogger(Util.class);

    public static final String jsonContentType  = "application/json; charset=UTF-8";
    public static final String contentType      = "Content-Type";

    /**
     *  Generates a mock Response Entity object in the way that they will be generated
     * @return
     */
    public ResponseEntity<HashMap<String, Object>> getMockResponse() {
        ArrayList<TemperaturePayload> temperature_data = new ArrayList<TemperaturePayload>();

        TemperaturePayload temperaturePayload = null;

        try {
            temperaturePayload = new TemperaturePayload.Builder()
                    .temperature(71)
                    .datetime(new Date())
                    .build();
        } catch (MyNullPointerException e) {
            log.error("Failed to construct TemperaturePayload", e);
        } catch (InvalidDataException f) {
            log.error("Invalid temperature value", f);
        }

        temperature_data.add(temperaturePayload);
        temperature_data.add(temperaturePayload);
        temperature_data.add(temperaturePayload);

        JSONPayload jsonResponsePayload = null;

        try {
            jsonResponsePayload = new JSONPayload.Builder()
                    .deviceID("12345")
                    .location("rooms")
                    .startDatetime(new Date())
                    .stopDatetime(new Date())
                    .temperatureData(temperature_data)
                    .build();
        } catch (MyNullPointerException e) {
            log.error("Failed to construct JSONPayload", e);
        }

        HashMap<String, Object> json = jsonResponsePayload.toMap();

        HttpHeaders headers = new HttpHeaders();
        headers.add(contentType, jsonContentType);

        // Return JSON and a 200 (OK) response code
        ResponseEntity<HashMap<String, Object>> responseEntity = new ResponseEntity<HashMap<String, Object>>(json, headers, HttpStatus.OK);

        return responseEntity;
    }
}
