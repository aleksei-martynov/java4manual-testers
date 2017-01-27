package com.db.edu.etl.Extractor;

import com.db.edu.etl.ExtractedUsers;

public interface EtlExtractor {
    ExtractedUsers[] extract();
}
