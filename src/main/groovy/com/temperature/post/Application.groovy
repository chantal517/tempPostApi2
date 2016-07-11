package com.temperature.post

import org.apache.log4j.Logger

/**
 * Created by Chantal on 7/10/16.
 */
class Application {

    // Instantiate a new logger for every class (passing the class to bind any instance of the class to the logger)
    def static log = Logger.getLogger(Application.class)

    /**
     * Below are examples of how we will use the logger. The log will go to the console, and to a file
     * @param args
     */
    def static void main(String[] args) {
        log.info("Test Message, Every major step in the program flow, we will log like this")
        log.warn("We will write warnings to console using the log.warn() function")
        log.error("Errors will be log using the error function")
        log.error(new Exception("You can also pass the log.error() function Exceptions"))
        log.fatal("Fatal messages will go here, in case something goes terribly wrong")
        otherFunction()
    }

    def static void otherFunction() {
        log.info("Doing something")
    }
}
