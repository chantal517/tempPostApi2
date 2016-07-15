package com.temperature.send_data.controller;

import com.temperature.send_data.BasicRequestModel;
import org.apache.log4j.Logger;
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

    @RequestMapping(path = "temp_data", method = RequestMethod.POST)
    public ResponseEntity<String> storeTempData(@RequestBody String s) throws Exception {
        ResponseEntity<String> responseEntity = new ResponseEntity<String>(s, HttpStatus.ACCEPTED);

        log.info(s);

        return responseEntity;
    }

    @RequestMapping(path = "temp_data", method = RequestMethod.GET)
    public ResponseEntity<String> returnTempData() throws Exception {
        ResponseEntity<String> responseEntity = new ResponseEntity<String>("Hi", HttpStatus.OK);

        return responseEntity;
    }

    @ExceptionHandler(Throwable.class)
    public Map<String, String> errorResponse(Throwable throwable) {

        return new HashMap<String, String>();
    }
}
