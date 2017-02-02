package com.db.edu.etl.Extractor;

import com.db.edu.etl.Exception.DataExtractException;
import com.db.edu.etl.Exception.ParseException;
import com.db.edu.etl.ExtractedUsers;

public class FileCvsExtractor implements EtlExtractor {

    @Override
    public ExtractedUsers[] extract() throws ParseException, DataExtractException {
        // Stub data generation
        ExtractedUsers userData = new ExtractedUsers("001", "tester001");
        ExtractedUsers[] stubReturn = {userData};

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
