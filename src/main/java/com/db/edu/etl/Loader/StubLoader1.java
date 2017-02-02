package com.db.edu.etl.Loader;

import com.db.edu.etl.ExtractedUsers;

public class StubLoader1 implements EtlLoader {
    @Override
    public void load(ExtractedUsers[] users) {
        return;
    }
}
