package com.db.edu;

import com.db.edu.etl.Controller;
import com.db.edu.etl.UpstreamType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.db.edu.etl.Controller.*;

public class EtlApp {
    private static final Logger logger = LoggerFactory.getLogger(EtlApp.class);

    private EtlApp() {
    }

    public static void main(String... args) {
        Controller.transform(UpstreamType.EIS4_DATA_FILE, new int[]{1, 0, 3, 100, 50, -3});
        fullEtlProcess();
    }

    private static void fullEtlProcess() {
        for (UpstreamType recordType : UpstreamType.values()) {
            load(transform(
                    recordType,
                    extract(recordType)));
        }
    }
}
