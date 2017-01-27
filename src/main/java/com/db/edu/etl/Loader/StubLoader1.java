package com.db.edu.etl.Loader;

import com.db.edu.etl.ExtractedUsers;
import com.db.edu.etl.Loader.EtlLoader;

public class StubLoader1 implements EtlLoader {
    @Override
    public void load(ExtractedUsers[] users) {
        return;
    }
}
