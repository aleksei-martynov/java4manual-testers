package com.db.edu.etl.Loader;

import com.db.edu.etl.ExtractedUser;

public class StubLoader1 implements EtlLoader {
    @Override
    public void load(ExtractedUser[] users) {
        return;
    }
}
