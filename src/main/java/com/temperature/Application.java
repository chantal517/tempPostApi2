package com.temperature;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by Jabari on 07/14/2016.
 */

@SpringBootApplication
public class Application {
    static Logger log = LoggerFactory.getLogger(Application.class);

    /**
     * START THE MAGIC
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }
}
