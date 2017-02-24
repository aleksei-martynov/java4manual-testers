package com.db.edu.etl.loader;

import com.db.edu.etl.ExtractedUser;

import java.util.HashSet;

public class StubLoader1 implements EtlLoader {
    @Override
    public boolean load(HashSet<ExtractedUser> users) {
        return true;
    }
}
