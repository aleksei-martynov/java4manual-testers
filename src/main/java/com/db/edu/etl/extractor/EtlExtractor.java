package com.db.edu.etl.extractor;

import com.db.edu.etl.ExtractedUser;
import com.db.edu.etl.exception.DataExtractException;
import com.db.edu.etl.exception.ParseException;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;

public interface EtlExtractor {
    HashSet<ExtractedUser> extract(File filePath) throws DataExtractException, ParseException, IOException;
}
