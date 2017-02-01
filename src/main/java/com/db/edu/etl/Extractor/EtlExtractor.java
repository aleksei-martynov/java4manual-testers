package com.db.edu.etl.Extractor;

import com.db.edu.etl.Exception.DataExtractException;
import com.db.edu.etl.Exception.ParseException;
import com.db.edu.etl.ExtractedUser;

public interface EtlExtractor {
    ExtractedUser[] extract() throws DataExtractException, ParseException;
}
