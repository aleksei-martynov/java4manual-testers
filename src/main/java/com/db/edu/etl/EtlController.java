package com.db.edu.etl;

import com.db.edu.etl.Extractor.EtlExtractor;
import com.db.edu.etl.Loader.EtlLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EtlController {
    private EtlExtractor extractor;
    private EtlLoader[] loaders;
    private static final Logger logger = LoggerFactory.getLogger(EtlController.class);

    public EtlController(EtlExtractor extractor, EtlLoader[] loaders) {
        this.extractor = extractor;
        this.loaders = loaders;
    }

    public void fullEtlProcess() {
        try {
            ExtractedUsers[] users = extractor.extract();
            for (EtlLoader current : loaders) {
                current.load(users);
            }

        } catch (RuntimeException e){
            throw new EtlProcessException("Please stop do this!", e);
        } finally {
            logger.debug("finally always executes!");
        }

    }

}

