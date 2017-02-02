package com.db.edu.etl.Extractor;

import com.db.edu.etl.Exception.DataExtractException;
import com.db.edu.etl.Exception.ParseException;
import com.db.edu.etl.ExtractedUsers;

public interface EtlExtractor {
    ExtractedUsers[] extract() throws DataExtractException, ParseException;
}
