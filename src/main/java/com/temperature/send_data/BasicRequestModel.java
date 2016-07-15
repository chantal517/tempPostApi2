package com.temperature.send_data;

/**
 * Created by Jabari on 07/14/2016.
 */
public class BasicRequestModel {
    String key;
    String value;

    BasicRequestModel(String key) {
        this.key = key;
        //this.value = value;
    }
    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}
