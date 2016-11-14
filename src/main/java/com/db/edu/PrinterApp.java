package com.db.edu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PrinterApp {
    private static final Logger logger = LoggerFactory.getLogger(PrinterApp.class);

    /**
     * @throws
     * @param args
     */
    public static void main(String... args) {
        logger.debug("Entering main method with args {}", args);
        System.out.println( "Hello World!!!!" + args[0]);
        System.out.println( "Hello World!!!" + args[1]);
        mySuperMethod();
        System.out.println( "Hello World!!!" + args[2]);
        System.out.println( "Hello World!!!" + args[0]);
    }

    public static  void mySuperMethod() {
        System.out.print("Test print");
    }
}
