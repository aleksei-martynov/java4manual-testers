package com.db.edu;

import com.db.edu.etl.RecordType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.db.edu.etl.Controller.*;

public class EtlApp {
    private static final Logger logger = LoggerFactory.getLogger(EtlApp.class);

    private EtlApp() {
    }

    ;

    public static void main(String... args) {

        testPrintUsingLogger(args);

        for (RecordType enumSwitcher : RecordType.values()) {
            load(transform(
                    enumSwitcher,
                    extract(enumSwitcher)));
        }
    }

    private static void testPrintUsingLogger(String[] args) {
        logger.debug("Entering main method with args {}", args);
        logger.debug("Hello World: " + args[0]);
        logger.debug("Hello World: " + args[1]);
        logger.info("Test print");
        logger.debug("Hello World: " + args[2]);
        logger.debug("Hello World: " + args[4]);
    }
}
