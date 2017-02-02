package com.db.edu.etl.Exception;

import com.db.edu.etl.ExtractedUsers;

public class DataExtractException extends Exception {

    private ExtractedUsers user;

    public DataExtractException(String message) {
        super(message);
    }

    public DataExtractException(String message, Throwable cause) {
        super(message, cause);
    }

    public DataExtractException(Throwable cause) {
        super(cause);
    }

    public DataExtractException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public DataExtractException(String message, ExtractedUsers extractedUser) {
        super(message);
        user = extractedUser;
    }

    public ExtractedUsers getExtractedUser() {
        return user;
    }
}
