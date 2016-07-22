package com.temperature.controller;

import com.temperature.commons.Util;
import com.temperature.commons.data.JSONPayload;
import com.temperature.commons.exception.InvalidDataException;
import com.temperature.commons.exception.MyNullPointerException;
import com.temperature.controller.validator.PayloadValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;
import java.text.ParseException;
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
    static Logger log = LoggerFactory.getLogger(TempDataController.class);

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

        if (!payloadValidator.temperatureDataValid()) {
            throw new InvalidDataException("Invalid temperature data");
        }

        HttpHeaders headers = new HttpHeaders();
        headers.add(Util.contentType, Util.jsonContentType);

        // Return the original body , and a 202 (ACCEPTED) response code
        ResponseEntity<Map<String, Object>> responseEntity = new ResponseEntity<Map<String, Object>>(responseBody, headers, HttpStatus.ACCEPTED);
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
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler({
            MyNullPointerException.class,
            NullPointerException.class,
            ParseException.class,
            InvalidDataException.class,
            HttpMessageNotReadableException.class})
    public ResponseEntity<String> badRequest(Exception e) {
        String message = e.getMessage();
        log.error("", e);

        if (e.getClass() == HttpMessageNotReadableException.class) {
            message = "Error parsing JSON, please make sure that the JSON body is valid JSON";
        }

        ResponseEntity<String> responseEntity = new ResponseEntity<String>(message, HttpStatus.BAD_REQUEST);

        return responseEntity;
    }

    @SuppressWarnings("unused")
    @ExceptionHandler({Throwable.class})
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<String> internalServerError(Exception e) {

        log.error("", e);
        ResponseEntity<String> responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

        return responseEntity;
    }
}
