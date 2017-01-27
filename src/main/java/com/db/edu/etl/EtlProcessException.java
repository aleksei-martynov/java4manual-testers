package com.db.edu.etl;

public class EtlProcessException extends RuntimeException {
    public EtlProcessException() {
    }

    public EtlProcessException(String message) {
        super(message);
    }

    public EtlProcessException(String message, Throwable cause) {
        super(message, cause);
    }

    public EtlProcessException(Throwable cause) {
        super(cause);
    }

    public EtlProcessException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
