package com.db.edu.etl.Exception;

import com.db.edu.etl.ExtractedUser;

public class DataExtractException extends Exception {

    private ExtractedUser user;

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

    public DataExtractException(String message, ExtractedUser extractedUser) {
        super(message);
        user = extractedUser;
    }

    public ExtractedUser getExtractedUser() {
        return user;
    }
}
