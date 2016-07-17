package com.temperature.send_data.commons;

import com.temperature.send_data.commons.data.model.JSONPayload;
import com.temperature.send_data.commons.data.model.TemperaturePayload;
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
    /**
     *  Generates a mock Response Entity object in the way that they will be generated
     * @return
     */
    public static ResponseEntity<HashMap<String, Object>> getMockResponse() {
        ArrayList<Map<String, Object>> temp_data = new ArrayList<Map<String, Object>>();

        TemperaturePayload temperaturePayload = new TemperaturePayload.Builder()
                .temperature(71.43)
                .datetime(new Date())
                .build();

        HashMap<String, Object> temperatureMap = temperaturePayload.toMap();

        temp_data.add(temperatureMap);
        temp_data.add(temperatureMap);
        temp_data.add(temperatureMap);

        JSONPayload jsonResponsePayload = new JSONPayload.Builder()
                .deviceID("12345")
                .location("rooms")
                .startDatetime(new Date())
                .stopDatetime(new Date())
                .temperatureData(temp_data)
                .build();

        HashMap<String,Object> json = jsonResponsePayload.toMap();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=UTF-8");

        // Return JSON and a 200 (OK) response code
        ResponseEntity<HashMap<String, Object>> responseEntity = new ResponseEntity<HashMap<String, Object>>(json, headers, HttpStatus.OK);

        return responseEntity;
    }
}
