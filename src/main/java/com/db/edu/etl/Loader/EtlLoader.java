package com.db.edu.etl.Loader;

import com.db.edu.etl.Exception.DataLoadException;
import com.db.edu.etl.Exception.TransformException;
import com.db.edu.etl.ExtractedUser;

import java.util.HashSet;

public interface EtlLoader {
    void load(HashSet<ExtractedUser> users) throws DataLoadException, TransformException;
}
