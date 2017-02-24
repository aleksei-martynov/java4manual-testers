package com.db.edu.etl;

import com.db.edu.etl.exception.DataExtractException;
import com.db.edu.etl.exception.DataLoadException;
import com.db.edu.etl.exception.EtlException;
import com.db.edu.etl.exception.ParseException;
import com.db.edu.etl.exception.TransformException;
import com.db.edu.etl.extractor.EtlExtractor;
import com.db.edu.etl.loader.EtlLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;

public class EtlController {
//    public static final String DEFAULT_EXTRACT_FILE = "csvPosTest.csv";
    private EtlExtractor extractor;
    private EtlLoader[] loaders;
    private File extractPath;
    private static final Logger logger = LoggerFactory.getLogger(EtlController.class);

    public EtlController(EtlExtractor extractor, EtlLoader[] loaders, File extractPath) {
        this.extractor = extractor;
        this.loaders = loaders;
        this.extractPath = extractPath;
    }

    public void fullEtlProcess() throws EtlException {
        try {
            HashSet<ExtractedUser> users = extractor.extract(this.extractPath);
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
        } catch (IOException e) {
            throw new EtlException("Wrong upStreamPath");
        }
    }
}

