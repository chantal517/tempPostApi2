package com.temperature.send_data.controller;

import org.apache.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jabari on 07/14/2016.
 */

// This class is a REST controller that will setup a specific endpoint (temp_data)
// We could theoretically set up different endpoints within this class by changing the
// request mapping path, but let's avoid that for design purposes
@RestController
public class TempDataController {
    static Logger log = Logger.getLogger(TempDataController.class);

    // This represents the REST path, it will be read in at run time from the application.properties file
    // We do this so that if we want to change the path, we only change it in one place
    final String path = "${path}";

    // This annotation MAPS (associates) the HTTP POST method with this Java Method
    // Think of it as a binding between HTTP methods and pure Java methods
    @SuppressWarnings("unused")
    @RequestMapping(path = path, method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> storeTemperatureData(@RequestBody Map<String, Object> payload) throws Exception {

        Map<String, Object> responseBody = payload;

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=UTF-8");

        // Return the original body , and a 202 (ACCEPTED) response code
        ResponseEntity<Map<String, Object>> responseEntity = new ResponseEntity<Map<String, Object>>(payload, headers, HttpStatus.ACCEPTED);

        // TODO - Implement logic for sending to data validator

        // TODO - Implement logic for sending to data processor

        return responseEntity;
    }

    // This annotation MAPS (associates) the HTTP GET method with this Java Method
    // Think of it as a binding between HTTP methods and pure Java methods
    @SuppressWarnings("unused")
    @RequestMapping(path = path, method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> getTemperatureData() throws Exception {
        // TODO - Implement logic for getting data from database

        return getMockResponse();
    }

    // This function is bound (associated with) any Throwable (Exception) that is thrown
    // within this class. This we will associate with errors that happen on the server side
    @SuppressWarnings("unused")
    @ExceptionHandler(Throwable.class)
    @ResponseStatus(value = HttpStatus.METHOD_NOT_ALLOWED)
    public void errorResponse(Throwable throwable) {

        // TODO - Implement error handling logic

        return;
    }

    /**
     *  Generates a mock Response Entity object in the way that they will be generated
     * @return
     */
    private ResponseEntity<Map<String, Object>> getMockResponse() {
        Map<String, Object> json = new HashMap<String, Object>();
        json.put("device_id", "123");
        json.put("location", "room");
        json.put("start_datetime", new Date().toString());
        json.put("stop_datetime", new Date().toString());

        ArrayList<Map<String, String>> temp_data = new ArrayList<Map<String, String>>();
        HashMap<String, String> x = new HashMap<String, String>();
        x.put(new Date().toString(), "71");
        temp_data.add(x);
        temp_data.add(x);
        temp_data.add(x);

        json.put("temperature_data", temp_data);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=UTF-8");

        // TODO - Implement logic for sending data back to user
        // Return JSON and a 200 (OK) response code
        ResponseEntity<Map<String, Object>> responseEntity = new ResponseEntity<Map<String, Object>>(json, headers, HttpStatus.OK);

        return responseEntity;
    }
}
