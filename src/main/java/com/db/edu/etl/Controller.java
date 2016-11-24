package com.db.edu.etl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Controller {

    private static final Logger logger = LoggerFactory.getLogger(Controller.class);

    public static String[] transform(RecordType recordType, String rawDataRecord) {
        switch (recordType) {
            case EIS1_DATA_FILE:
                logger.debug("Start transformation for RecordType: " + recordType.name());
                logger.debug("Start transformation for RecordType: " + recordType.name());
                return null;
            case EIS2_DATA_FILE:
                logger.debug("Start transformation for RecordType: " + recordType.name());
                logger.debug("Start transformation for RecordType: " + recordType.name());
                return null;
            case EIS3_DATA_FILE:
                logger.debug("Start transformation for RecordType: " + recordType.name());
                logger.debug("Start transformation for RecordType: " + recordType.name());
                return null;
            case EIS4_DATA_FILE:
                logger.debug("Start transformation for RecordType: " + recordType.name());
                logger.debug("Start transformation for RecordType: " + recordType.name());
                return null;
            default:
                return null;
        }
    }

    public static String extract(RecordType recordType) {
        logger.debug("Start extracting for RecordType: " + recordType.name());
        logger.debug("End extracting for RecordType: " + recordType.name());
        return null;
    }

    public static void load(String[] transformedData) {
        logger.debug("Start loading transformed data");
        logger.debug("End loading transformed data");
        logger.info("---------- Loading has ended ----------");
    }
}

