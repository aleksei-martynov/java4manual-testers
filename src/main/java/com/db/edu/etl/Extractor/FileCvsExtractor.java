package com.db.edu.etl.Extractor;

import com.db.edu.etl.Exception.DataExtractException;
import com.db.edu.etl.Exception.ParseException;
import com.db.edu.etl.ExtractedUser;

public class FileCvsExtractor implements EtlExtractor {

    @Override
    public ExtractedUser[] extract() throws ParseException, DataExtractException {
        // Stub data generation
        ExtractedUser userData = new ExtractedUser("001", "tester001");
        ExtractedUser[] stubReturn = {userData};

        // Choose which Exception you want to throw
        if (stubReturn.length < 1) {
            throw new ParseException("Bad stub data :(");
        }
        if (stubReturn.length == 1) {
            throw new DataExtractException("Ooops! Extracted list is too small", stubReturn[0]);
        }
        return stubReturn;
    }
}
