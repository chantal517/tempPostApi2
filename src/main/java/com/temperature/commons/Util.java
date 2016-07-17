package com.temperature.commons;

import com.temperature.commons.data.JSONPayload;
import com.temperature.commons.data.MyNullPointerException;
import com.temperature.commons.data.TemperaturePayload;
import org.apache.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jabari && Chantal on 07/16/2016.
 */
public class Util {
    static Logger log = Logger.getLogger(Util.class);

    public static final String jsonContentType  = "application/json; charset=UTF-8";
    public static final String contentType      = "Content-Type";

    /**
     *  Generates a mock Response Entity object in the way that they will be generated
     * @return
     */
    public ResponseEntity<HashMap<String, Object>> getMockResponse() {
        ArrayList<Map<String, Object>> temp_data = new ArrayList<Map<String, Object>>();

        TemperaturePayload temperaturePayload = null;

        try {
            temperaturePayload = new TemperaturePayload.Builder()
                    .temperature(71.43)
                    .datetime(new Date())
                    .build();
        } catch (MyNullPointerException e) {
            // TODO - LOG ERROR
            e.printStackTrace();
        }

        HashMap<String, Object> temperatureMap = temperaturePayload.toMap();

        temp_data.add(temperatureMap);
        temp_data.add(temperatureMap);
        temp_data.add(temperatureMap);

        JSONPayload jsonResponsePayload = null;

        try {
            jsonResponsePayload = new JSONPayload.Builder()
                    .deviceID("12345")
                    .location("rooms")
                    .startDatetime(new Date())
                    .stopDatetime(new Date())
                    .temperatureData(temp_data)
                    .build();
        } catch (MyNullPointerException e) {
            // TODO - LOG ERROR
            e.printStackTrace();
        }

        HashMap<String,Object> json = jsonResponsePayload.toMap();

        HttpHeaders headers = new HttpHeaders();
        headers.add(contentType, jsonContentType);

        // Return JSON and a 200 (OK) response code
        ResponseEntity<HashMap<String, Object>> responseEntity = new ResponseEntity<HashMap<String, Object>>(json, headers, HttpStatus.OK);

        return responseEntity;
    }
}
