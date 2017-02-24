package com.db.edu;

import com.db.edu.etl.EtlController;
import com.db.edu.etl.exception.DataExtractException;
import com.db.edu.etl.exception.EtlException;
import com.db.edu.etl.extractor.FileCSVExtractor;
import com.db.edu.etl.loader.EtlLoader;
import com.db.edu.etl.loader.StubLoader1;
import com.db.edu.etl.loader.StubLoader2;

import java.io.File;

//TODO: Move all checks to Tests
public class Main {
    private static final String DEFAULT_EXTRACT_FILE = "testData\\csvPosTest.csv";
    public static void main(String[] args) {
        try {
            new EtlController(
                    new FileCSVExtractor(),
                    new EtlLoader[]{new StubLoader1(), new StubLoader2()},
                    new File(DEFAULT_EXTRACT_FILE)
            ).fullEtlProcess();
        } catch (EtlException e) {
            try {
                DataExtractException cause = (DataExtractException) e.getCause();
                throw new RuntimeException("Caught DataExtractException! — "
                        + "stub user data: ["
                        + cause.getExtractedUser().getUserID() + ", "
                        + cause.getExtractedUser().getUserName() + "]"
                        , e);
            } catch (ClassCastException cce) {
                throw new RuntimeException("It is not a DataExtractException!!", e);
            }
        }
    }
}