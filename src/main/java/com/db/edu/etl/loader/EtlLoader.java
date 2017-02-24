package com.db.edu.etl.loader;

import com.db.edu.etl.exception.DataLoadException;
import com.db.edu.etl.exception.TransformException;
import com.db.edu.etl.ExtractedUser;

import java.util.HashSet;

/*
 * Returns true if no errors in process
 */
public interface EtlLoader {
    boolean load(HashSet<ExtractedUser> users) throws DataLoadException, TransformException;
}