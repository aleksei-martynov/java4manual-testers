package com.db.edu.etl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Controller {

    private static final Logger logger = LoggerFactory.getLogger(Controller.class);

    public static int[] transform(UpstreamType recordType, int[] rawDataRecords) {
        float sum = 0;
        if (rawDataRecords.length == 0) {
            logger.error("Input data set is empty");
            return rawDataRecords;
        } else {
            for (int current : rawDataRecords) {
                sum += current;
            }
        }
        logger.info("Average value in extracted list: " + sum/rawDataRecords.length);
        return rawDataRecords;
    }

    public static int[] extract(UpstreamType recordType) {
        logger.debug("Start extracting for UpstreamType: " + recordType.name());
        logger.debug("End extracting for UpstreamType: " + recordType.name());
        return new int[] {1,2,recordType.ordinal()};
    }

    public static void load(int[] transformedData) {
        logger.debug("Start loading transformed data");
        logger.debug("End loading transformed data");
        logger.info("---------- Loading has ended ----------");
    }
}

