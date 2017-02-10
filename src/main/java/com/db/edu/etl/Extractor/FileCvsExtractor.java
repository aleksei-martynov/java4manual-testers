package com.db.edu.etl.Extractor;

import com.db.edu.etl.Exception.DataExtractException;
import com.db.edu.etl.Exception.ParseException;
import com.db.edu.etl.ExtractedUser;

import java.util.HashSet;

public class FileCvsExtractor implements EtlExtractor {

    @Override
    public HashSet<ExtractedUser> extract() throws ParseException, DataExtractException {
        // Stub data generation
        ExtractedUser userData = new ExtractedUser("001", "tester001");
        HashSet<ExtractedUser> stubReturn = new HashSet<>();
        stubReturn.add(userData);

        // Choose which Exception you want to throw
        if (stubReturn.isEmpty()) {
            throw new ParseException("Bad stub data :(");
        }
        if (stubReturn.size() == 1 && stubReturn.contains(userData)) {
            throw new DataExtractException("Ooops! Only dummy user is in the set", userData);
        }
        return stubReturn;
    }
}
