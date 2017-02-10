package com.db.edu.etl.Extractor;

import com.db.edu.etl.Exception.DataExtractException;
import com.db.edu.etl.Exception.ParseException;
import com.db.edu.etl.ExtractedUser;

import java.util.HashSet;

public interface EtlExtractor {
    HashSet<ExtractedUser> extract() throws DataExtractException, ParseException;
}
