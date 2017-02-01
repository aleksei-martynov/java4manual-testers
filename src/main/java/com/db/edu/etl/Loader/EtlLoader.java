package com.db.edu.etl.Loader;

import com.db.edu.etl.Exception.DataLoadException;
import com.db.edu.etl.Exception.TransformException;
import com.db.edu.etl.ExtractedUser;

public interface EtlLoader {
    void load(ExtractedUser[] users) throws DataLoadException, TransformException;
}
