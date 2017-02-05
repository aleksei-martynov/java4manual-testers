package com.db.edu.etl;

import com.db.edu.etl.Exception.*;
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

    public void fullEtlProcess() throws EtlException {
        try {
            ExtractedUser[] users = extractor.extract();
            for (EtlLoader current : loaders) {
                current.load(users);
            }
        } catch (TransformException e) {
            throw new EtlException("Please stop transforming!", e);
        } catch (ParseException e) {
            throw new EtlException("Please stop parsing!", e);
        } catch (DataLoadException e) {
            throw new EtlException("Please stop data loading process!", e);
        } catch (DataExtractException e) {
            throw new EtlException("Please stop data extracting process!", e);
        }
    }
}

