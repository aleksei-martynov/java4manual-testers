package com.db.edu.etl.Loader;

import com.db.edu.etl.ExtractedUser;

import java.util.HashSet;

public class StubLoader2 implements EtlLoader {
    @Override
    public boolean load(HashSet<ExtractedUser> users) {
        return true;
    }
}
