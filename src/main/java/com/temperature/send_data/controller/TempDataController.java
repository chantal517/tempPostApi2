package com.temperature.send_data.controller;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<String> storeTempData(@RequestBody String s) throws Exception {

        // Return a response that says Hi, and a 202 (ACCEPTED) response code
        ResponseEntity<String> responseEntity = new ResponseEntity<String>("Post successful", HttpStatus.ACCEPTED);

        // TODO - Implement logic for sending to data validator

        // TODO - Implement logic for sending to data processor

        return responseEntity;
    }

    // This annotation MAPS (associates) the HTTP GET method with this Java Method
    // Think of it as a binding between HTTP methods and pure Java methods
    @SuppressWarnings("unused")
    @RequestMapping(path = path, method = RequestMethod.GET)
    public ResponseEntity<String> returnTempData() throws Exception {

        // Return a response that says Hi, and a 200 (OK) response code
        ResponseEntity<String> responseEntity = new ResponseEntity<String>("Get successful", HttpStatus.OK);

        // TODO - Implement logic for getting data from database

        // TODO - Implement logic for sending data back to user

        return responseEntity;
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
}
