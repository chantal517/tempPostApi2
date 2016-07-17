package com.temperature.send_data.controller;

import com.temperature.send_data.commons.data.model.JSONPayload;
import com.temperature.send_data.commons.Util;
import org.apache.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jabari on 07/14/2016.
 */


@RestController
public class TempDataController {
    static Logger log = Logger.getLogger(TempDataController.class);


    final String path = "${path}";


    @SuppressWarnings("unused")
    @RequestMapping(value = path, method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> storeTemperatureData(@RequestBody Map<String, Object> jsonPayload) throws Exception {

        Map<String, Object> responseBody = jsonPayload;

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=UTF-8");

        // Return the original body , and a 202 (ACCEPTED) response code
        ResponseEntity<Map<String, Object>> responseEntity = new ResponseEntity<Map<String, Object>>(jsonPayload, headers, HttpStatus.ACCEPTED);

        // TODO - Implement logic for sending to data validator

        // TODO - Implement logic for sending to data processor

        return responseEntity;
    }

    @SuppressWarnings("unused")
   // @RequestMapping(value = path, method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> storeTemperatureData2(@RequestBody JSONPayload jsonPayload) throws Exception {

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=UTF-8");

        ResponseEntity<Map<String, Object>> responseEntity = new ResponseEntity<Map<String, Object>>(jsonPayload.toMap(), headers, HttpStatus.ACCEPTED);


        return responseEntity;
    }

    @SuppressWarnings("unused")
    @RequestMapping(value = path, method = RequestMethod.GET)
    public ResponseEntity<HashMap<String, Object>> getTemperatureData() throws Exception {
        // TODO - Implement logic for getting data from database

        return Util.getMockResponse();
    }

    @SuppressWarnings("unused")
    @ExceptionHandler(Throwable.class)
    @ResponseStatus(value = HttpStatus.METHOD_NOT_ALLOWED)
    public void errorResponse(Throwable throwable) {
        log.error("failed");
        // TODO - Implement error handling logic

        return;
    }


}
