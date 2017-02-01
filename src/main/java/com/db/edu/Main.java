package com.db.edu;

import com.db.edu.etl.EtlController;
import com.db.edu.etl.Exception.EtlException;
import com.db.edu.etl.Loader.EtlLoader;
import com.db.edu.etl.Extractor.FileCvsExtractor;
import com.db.edu.etl.Loader.StubLoader1;
import com.db.edu.etl.Loader.StubLoader2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    public static void main(String[] args) {
        final Logger logger = LoggerFactory.getLogger(Main.class);

        try {

            new EtlController(
                    new FileCvsExtractor(),
                    new EtlLoader[]{
                            new StubLoader1(),
                            new StubLoader2()}
            ).fullEtlProcess();
        }  catch (EtlException e) {
            throw new RuntimeException("Caught you!", e);
//            How to get data from 'cause'?   I want to print user data
            
//            logger.debug("test" + e.getCause());
//            logger.debug("First extracted user data: ["+e.getCause() + ", " + e.getExtractedUser().getUserName() + "]");
        }
    }
}
