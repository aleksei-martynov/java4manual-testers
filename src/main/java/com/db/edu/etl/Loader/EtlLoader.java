package com.db.edu.etl.Loader;

import com.db.edu.etl.ExtractedUsers;

public interface EtlLoader {
    void load (ExtractedUsers[] users);
}