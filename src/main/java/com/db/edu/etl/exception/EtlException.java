package com.db.edu.etl.exception;

public class EtlException extends RuntimeException {
    public EtlException() {
    }

    public EtlException(String message) {
        super(message);
    }

    public EtlException(String message, Throwable cause) {
        super(message, cause);
    }

    public EtlException(Throwable cause) {
        super(cause);
    }

    public EtlException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
