package com.db.edu;

import com.db.edu.etl.EtlController;
import com.db.edu.etl.Exception.DataExtractException;
import com.db.edu.etl.Exception.EtlException;
import com.db.edu.etl.Loader.EtlLoader;
import com.db.edu.etl.Extractor.FileCvsExtractor;
import com.db.edu.etl.Loader.StubLoader1;
import com.db.edu.etl.Loader.StubLoader2;

public class Main {
    public static void main(String[] args) {
        try {
            new EtlController(
                    new FileCvsExtractor(),
                    new EtlLoader[]{new StubLoader1(), new StubLoader2()}
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