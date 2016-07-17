package com.temperature.controller.validator;

import com.temperature.commons.data.AbstractPayload;
import com.temperature.commons.data.JSONPayload;
import com.temperature.commons.data.MyNullPointerException;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Jabari Dash
 * @author Chantal Lewis
 *
 * Created by Jabari on 07/14/2016.
 */
@SuppressWarnings("unused")
public class PayloadValidator {
    JSONPayload jsonPayload;

    @SuppressWarnings("unused")
    public PayloadValidator(HashMap<String, Object> jsonPayloadMap) throws MyNullPointerException {
        this.jsonPayload = JSONPayload.fromMap(jsonPayloadMap);
    }


    @SuppressWarnings("unused")
    public boolean hasXSS(HashMap<String, Object> jsonPayload) {
        // TODO - Implement XSS check

        return true;
    }

    @SuppressWarnings("unused")
    public JSONPayload getJSONPayLoad() { return this.jsonPayload; }
}
