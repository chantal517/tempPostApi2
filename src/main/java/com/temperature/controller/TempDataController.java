package com.temperature.controller;

import com.temperature.commons.Util;
import com.temperature.commons.data.JSONPayload;
import com.temperature.commons.exception.MyNullPointerException;
import com.temperature.controller.validator.PayloadValidator;
import org.apache.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Jabari Dash
 * @author Chantal Lewis
 *
 * Created by Jabari on 07/14/2016.
 */
@RestController
public class TempDataController {
    static Logger log = Logger.getLogger(TempDataController.class);

    final String path = "temp_data";

    /**
     * 
     * @param jsonPayload
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unused")
    @RequestMapping(value = path, method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> storeTemperatureData(@RequestBody Map<String, Object> jsonPayload) throws Exception {
        log.info("POST request received");
        log.info("Creating Payload Validator");

        // Create a new PayloadValidator passing it the request's JSON body
        PayloadValidator payloadValidator = new PayloadValidator(jsonPayload, JSONPayload.class);

        log.info("Validator successfully created");

        // If the object constructs successfully, all required fields are non-empty or non-null
        // Get the JSONPayload back as a Map, and send it back to the client
        Map<String, Object> responseBody = payloadValidator.getPayload().toMap();

        HttpHeaders headers = new HttpHeaders();
        headers.add(Util.contentType, Util.jsonContentType);

        // Return the original body , and a 202 (ACCEPTED) response code
        ResponseEntity<Map<String, Object>> responseEntity = new ResponseEntity<Map<String, Object>>(jsonPayload, headers, HttpStatus.ACCEPTED);

        // TODO - Implement logic for sending to data validator

        // TODO - Implement logic for sending to data processor

        return responseEntity;
    }

    /**
     *
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unused")
    @RequestMapping(value = path, method = RequestMethod.GET)
    public ResponseEntity<HashMap<String, Object>> getTemperatureData() throws Exception {
        // TODO - Implement logic for getting data from database

        return new Util().getMockResponse();
    }

    @SuppressWarnings("unused")
    @ExceptionHandler(Throwable.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public void errorResponse(Throwable throwable) {
        log.error("Internal Server Error", throwable);
        // TODO - Implement error handling logic

        return;
    }

    @SuppressWarnings("unused")
    @ExceptionHandler(MyNullPointerException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public void emptyField(MyNullPointerException e) {
        log.error("Empty Field", e);
        // TODO - Implement error handling logic

        return;
    }
}
